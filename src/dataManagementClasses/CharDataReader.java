package dataManagementClasses;

import interfaces.DataReader;

import java.util.Scanner;

public class CharDataReader implements DataReader {
	
	@SuppressWarnings("unused")
	private static final int CHARSIZE = Character.BYTES; 
	public static final CharDataReader INSTANCE = new CharDataReader(); 

	private CharDataReader() {}; 

	public Character readDataFromArrayOfBytes(byte[] b, int index) {
		byte bytes[] = {b[index],b[index+1]};
		char c = new String(bytes).charAt(0);
		return c;
	}


	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine();
		if(s.length()==1)
			return s.charAt(0);
		else
			return null;
	}

}
