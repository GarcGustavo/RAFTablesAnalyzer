package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

/**
 * @author Gustavo
 *
 */
public class ShortDataWriter implements DataWriter {
	
	private static final int SHORTSIZE = Short.BYTES; 
	public static final ShortDataWriter INSTANCE = new ShortDataWriter(); 
	
	private ShortDataWriter() {}; 

	@Override
	public void writeDataToArrayOfBytes(byte[] b, int index, Object rvalue) {
		short value = (Short) rvalue; 
		int lSB; 
		for (int i=0; i < SHORTSIZE; i++) { 
			lSB = 0x000000ff & value;
			value = (short) (value >> 8); 
		    b[index + SHORTSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.SHORTFORMAT, (Short) value);
	}

}
