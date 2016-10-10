package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class LongDataWriter implements DataWriter{
	
	private static final int LONGSIZE = Long.BYTES; 
	public static final LongDataWriter INSTANCE = new LongDataWriter();
	
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object rv) {
		Long v = (Long) rv;
		long value = Long.valueOf(v);
		long lSB; 
		for (int i=0; i < LONGSIZE; i++) { 
			lSB = 0x000000ff & value;
			value = value >> 8; 
		    a[starting + LONGSIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
	}
	
	@Override
	public String toString(Object value) {
		return String.format(DataUtils.LONGFORMAT, (Long) value); 
	} 
}
