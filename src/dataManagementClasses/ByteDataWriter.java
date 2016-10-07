package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class ByteDataWriter implements DataWriter {

	private static final int BYTESIZE = Byte.SIZE; 
	public static final ByteDataReader INSTANCE = new ByteDataReader(); 
	
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object value) {
		byte byteValue = Byte.;
		int lSB; 
		for (int i=0; i < BYTESIZE; i++) { 
		    a[starting + BYTESIZE - i - 1] = (byte) (lSB & 0x000000ff);
		}
	}

	@Override
	public String toString(Object value) {
		return String.format(, (byte) value);
	}

}
