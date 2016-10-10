package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

public class ByteDataReader implements DataReader {
	
	private static final int BYTESIZE = Byte.BYTES; 
	public static final ByteDataReader INSTANCE = new ByteDataReader(); 
	
	private ByteDataReader() {}; 

	//THE PARSEBYTE PART MIGHT NEED SOME ADJUSTING SINCE I DON'T KNOW HOW BYTES TO STRING VALUES WORK
	//TEST LATER BEFORE FINISHING UP, PLEASE TELL ME YOU DIDN'T SPEND 2 HOURS LOOKING FOR THIS ERROR
	@Override
	public Byte readDataFromArrayOfBytes(byte[] a, int starting) {
		byte value = 0;
		for (int i=0; i < BYTESIZE; i++) { 
			value += a[i];
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