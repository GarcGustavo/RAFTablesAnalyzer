package generalUtilities;

import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * @author 
 *
 */
public class TYPE { 
	private String name; 
	private int size;    // number of bytes
	private DataReader dataReader;  // data reader for this data type
	private DataWriter dataWriter;  // data writer for this data type
	/**
	 * @param name
	 * @param size
	 * @param dr
	 * @param dw
	 */
	public TYPE(String name, int size, DataReader dr, DataWriter dw) { 
		this.name = name; 
		this.size = size; 
		this.dataReader = dr; 
		this.dataWriter = dw; 
	}
	/**
	 * @return name as string
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return size as int
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @return data reader
	 */
	public DataReader getDataReader() {
		return dataReader;
	}
	/**
	 * @param dr sets data reader
	 */
	public void setDataReader(DataReader dr) {
		this.dataReader = dr;
	}
	/**
	 * @return data writer
	 */
	public DataWriter getDataWriter() {
		return dataWriter;
	}
	/**
	 * @param dw sets data writer
	 */
	public void setDataWriter(DataWriter dw) {
		this.dataWriter = dw;
	}
	
}