package tableCollectionClasses;

import generalUtilities.DataUtils;
import interfaces.DataReader;
import interfaces.DataWriter;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import dataManagementClasses.Attribute;

/**
 * @author 
 *
 */
public class AttributeInSchema extends Attribute {
	private int dataOffset;     // offset of its value in the records of table it is part of
	private DataReader dataReader; 
	private DataWriter dataWriter; 
	/**
	 * @param name
	 * @param tIndex
	 * @param dataOffset
	 */
	public AttributeInSchema(String name, int tIndex, int dataOffset) 
	{ 
		super(name, tIndex);  
		this.dataOffset = dataOffset; 
		this.dataReader = DataUtils.getTypeDataReader(tIndex); 
		this.dataWriter = DataUtils.getTypeDataWriter(tIndex); 
	}

	/**
	 * @param file
	 * @param dataOffset
	 * @throws IOException
	 */
	public AttributeInSchema(RandomAccessFile file, int dataOffset) 
			throws IOException 
	{ 
		super(file);  
		this.dataOffset = dataOffset; 
		this.dataReader = DataUtils.getTypeDataReader(super.gettIndex()); 
		this.dataWriter = DataUtils.getTypeDataWriter(super.gettIndex()); 		
	}
	
	/**
	 * @return data offset as integer
	 */
	public int getDataOffset() { 
		return dataOffset; 
	}
	
	/**
	 * @param a
	 * @param starting
	 * @return data read from byte array
	 */
	public Object readDataValueFromArrayOfBytes(byte[] a, int starting) { 
		return dataReader.readDataFromArrayOfBytes(a, starting); 
	}
	
	/**
	 * @param input
	 * @return data read from input scanner
	 */
	public Object readDataValueFromInputScanner(Scanner input) {  
		return dataReader.readDataFromInputScanner(input); 
	}
	
	/**
	 * @param a
	 * @param starting
	 * @param value
	 */
	public void writeDataValueToArrayOfBytes(byte[] a, int starting, Object value) { 
		dataWriter.writeDataToArrayOfBytes(a, starting, value);
	}
	
	/**
	 * @param value
	 * @return string of written data
	 */
	public String toString(Object value) { 
		return dataWriter.toString(value); 
	}
}
