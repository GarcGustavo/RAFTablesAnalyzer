package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * @author Gustavo
 *
 */
public class ByteDataWriter implements DataWriter {

	private static final int BYTESIZE = Byte.BYTES; 
	public static final ByteDataWriter INSTANCE = new ByteDataWriter(); 
	
	public ByteDataWriter() {}
	
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int index, Object rvalue) {
		byte byteValue = (byte) rvalue;
		for (int i=0; i < BYTESIZE; i++) { 
		    a[index + BYTESIZE - i - 1] = byteValue;
		}
	}

	@Override
	public String toString(Object value) {
		
		return String.format(DataUtils.BYTEFORMAT, (Byte) value); 
	}

}
