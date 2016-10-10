package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class CharDataWriter implements DataWriter{
	
	private static final int CHARSIZE = Character.BYTES; 
	public static final CharDataWriter INSTANCE = new CharDataWriter(); 
	
	private CharDataWriter() {}; 

	//Need to test/fix writer method
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object rvalue) {
		Character value = (Character) rvalue.toString().charAt(0);
		for (int i=0; i < CHARSIZE; i++) { 
			a[starting + CHARSIZE - i - 1] = (byte) (value.charValue());
		}
		
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.CHARFORMAT, (Character) value); 
	}

}
