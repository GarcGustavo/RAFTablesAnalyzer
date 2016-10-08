package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class DoubleDataWriter implements DataWriter{
	
	private static final int DOUBLESIZE = Double.BYTES; 
	public static final DoubleDataWriter INSTANCE = new DoubleDataWriter(); 
	
	private DoubleDataWriter() {}; 

	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object rv) {
		Double v = (Double) rv; 
		long value = Double.doubleToLongBits(v); 
		long lSB; 
		for (int i=0; i < DOUBLESIZE; i++) { 
			lSB = 0x000000ff & value;
			value = value >> 8; 
		    a[starting + DOUBLESIZE - i - 1] = (byte) (lSB & 0x000000ff); 
		}
	}

	//Need to find equivalent for INTEGERFORMAT to long
	@Override
	public String toString(Object value) {
		return String.format(DataUtils.INTEGERFORMAT, (Long) value);
	}

}
