package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class LongDataWriter implements DataWriter{
	
	private static final int LONGSIZE = Long.BYTES; 
	public static final LongDataWriter INSTANCE = new LongDataWriter();
	
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object value) {
		Long v = (Long) value;
		int lSB; 
		for (int i=0; i < LONGSIZE; i++) { 
			lSB = 0x000000ff & v.intValue();
			v = v >> 8; 
		    a[v.intValue() + LONGSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
	}
	
	//Need to find equivalent to FLOATFORMAT but for long
	@Override
	public String toString(Object value) {
		return String.format(DataUtils.FLOATFORMAT, (Long) value); 
	} 
}
