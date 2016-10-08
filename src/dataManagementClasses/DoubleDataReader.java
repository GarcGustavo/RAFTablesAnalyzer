package dataManagementClasses;

import java.util.Scanner;

import interfaces.DataReader;

public class DoubleDataReader implements DataReader {
	
	private static final int DOUBLESIZE = Double.BYTES; 
	public static final DoubleDataReader INSTANCE = new DoubleDataReader(); 
	
	private DoubleDataReader() {}; 

	@Override
	public Double readDataFromArrayOfBytes(byte[] a, int starting) {
		double value = 0; 
		int lSB; 
		for (int i=0; i < DOUBLESIZE; i++) { 
			value = (int)value << 8; 
			lSB = 0x000000ff & a[starting + i];
			value = (int)value | lSB; 
		}
		return value; 
	}

	@Override
	public Object readDataFromInputScanner(Scanner input) {
		String s = input.nextLine(); 
		try {
			double v = Double.parseDouble(s); 
			return new Double(v); 
		} catch (Exception e) { 
			return null; 
		}
	}

}
