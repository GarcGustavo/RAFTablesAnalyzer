package dataManagementClasses;

import java.util.Scanner;

public class CharDataReader implements DataReader {
	private IntDataReader() {}; 
	
	public Character readDataFromArrayOfBytes(byte[] b, int index) {
		int value = 0; 
		int lSB; 
		for (int i=0; i < INTSIZE; i++) { 
			value = value << 8; 
			lSB = 0x000000ff & b[index + i];
			value = value | lSB; 
		}
		return value; 
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
