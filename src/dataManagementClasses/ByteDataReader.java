package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

public class ByteDataReader implements DataReader {
	
	private static final int BYTESIZE = Byte.BYTES; 
	public static final ByteDataReader INSTANCE = new ByteDataReader(); 
	
	private ByteDataReader() {}; 

	@Override
	public Byte readDataFromArrayOfBytes(byte[] b, int index) {
		byte value = 0;
		for (int i=0; i < BYTESIZE; i++) { 
			value += b[index+i];
		}
		return value;
	}

	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			byte b = Byte.parseByte(s); 
			return new Byte(b); 
		} catch (Exception e) { 
			return null; 
		}
	}

}