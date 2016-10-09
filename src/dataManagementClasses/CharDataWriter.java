package dataManagementClasses;

import generalUtilities.DataUtils;
import interfaces.DataWriter;

public class CharDataWriter implements DataWriter{
	
	private static final int CHARSIZE = Character.SIZE; 
	public static final CharDataWriter INSTANCE = new CharDataWriter(); 
	
	private CharDataWriter() {}; 

	//Need to test/fix writer method
	@Override
	public void writeDataToArrayOfBytes(byte[] a, int starting, Object rvalue) {
		Character value = (Character) rvalue; 
		a[starting] = (byte) (value.charValue());  
	}

	@Override
	public String toString(Object value) {
		return String.format(DataUtils.CHARFORMAT, (Character) value); 
	}

}
