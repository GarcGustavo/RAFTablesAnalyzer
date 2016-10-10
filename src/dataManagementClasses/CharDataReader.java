package dataManagementClasses;

import interfaces.DataReader;

import java.util.Scanner;

public class CharDataReader implements DataReader {
	private static final int CHARSIZE = Character.SIZE; 
	public static final CharDataReader INSTANCE = new CharDataReader(); 
	
	private CharDataReader() {}; 
	
	//Need to fix this, look up how to tell chars from bits
	public Character readDataFromArrayOfBytes(byte[] b, int index) {
		char c = (char)(((b[index]&0x00FF)<<8) + (b[index+1]&0x00FF));
		 return c;
	}
	
	
	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			int v = Integer.parseInt(s); 
			return new Integer(v); 
		} catch (Exception e) { 
			return null; 
		}
	}

}
