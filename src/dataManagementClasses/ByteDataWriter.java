package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class ByteDataWriter implements DataWriter {

	private static final int BYTESIZE = Byte.SIZE; 
	public static final ByteDataWriter INSTANCE = new ByteDataWriter(); 
	
	public ByteDataWriter() {}
	
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object value) {
		byte byteValue = (byte) value;
		for (int i=0; i < BYTESIZE; i++) { 
		    a[starting + BYTESIZE - i - 1] = byteValue;
		}
	}

	//Possible error with format parameters, test throughly
	@Override
	public String toString(Object value) {
		
		return String.format("%02X", (Byte) value); 
	}

}
