package tableCollectionClasses;

import generalUtilities.DataUtils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author 
 *
 */
public class TableSchema {
	private AttributeInSchema[] attrs;   // the attributes
	private int size;     // number of attributes added

	/**
	 * @param n
	 */
	public TableSchema(int n) { 
		attrs = new AttributeInSchema[n]; 
	}

	/**
	 * @param attr
	 * @throws IllegalStateException
	 */
	public void addAttribute(AttributeInSchema attr) throws IllegalStateException { 
		if (size == attrs.length)
			throw new IllegalStateException("Table of attributes is full."); 
		attrs[size] = attr; 
		size++;
	}

	/**
	 * @return number of attributes
	 */
	public int getNumberOfAttrs() { 
		return size; 
	}

	/**
	 * @param index
	 * @return attributes in schema at index
	 * @throws IndexOutOfBoundsException
	 */
	public AttributeInSchema getAttr(int index) throws IndexOutOfBoundsException { 
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("getAttr: Index out of bounds: " + index); 
		return attrs[index]; 
	}

	/**
	 * @return validity
	 */
	public boolean isValid() { 
		return false; 
	}

	/**
	 * @param n
	 * @return instance of table schema
	 */
	public static TableSchema getInstance(int n) { 
		// PRE: n is a valid positive integer value
		return new TableSchema(n); 
	}

	/**
	 * @param file
	 * @return instance of table schema
	 * @throws IOException
	 */
	public static TableSchema getInstance(RandomAccessFile file) throws IOException { 
		file.seek(0);
		short nAttrs = file.readShort(); 
		TableSchema st = new TableSchema(nAttrs); 
		int offset = 0; 
		// read attributes 
		for (int i=0; i<nAttrs; i++) { 
			AttributeInSchema ais = new AttributeInSchema(file, offset); 
			st.addAttribute(ais); 
			offset += DataUtils.getTypeSize(ais.gettIndex()); 
		}
		return st; 

	}

	/**
	 * saves schema
	 * @param file
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void saveSchema(RandomAccessFile file) 
			throws IllegalStateException, IOException { 
		// Saves this schema into the given RAF, beginning at its current file pointer
		// location. The file is assumed to be open and with file pointer at 0. 

		if (file.getFilePointer() != 0)
			throw new IllegalStateException("File pointer is not at 0."); 

		// first write value for the number of attributes
		file.writeShort(size);

		// ready to save the schema; one attribute at the time...
		for (AttributeInSchema a : attrs) 
			a.writeToFile(file);
	}

	public String toString() { 
		String s = "|"; 

		for (int i=0; i<attrs.length-1; i++){
				s += String.format(DataUtils.STRINGFORMAT, attrs[i].getName()); 
		}
		s += String.format(DataUtils.STRINGFORMAT, attrs[attrs.length-1].getName())+" |";
		s += "\n"; 
		for (int i=0; i<=this.size * DataUtils.VALUEWIDE+2; i++) s+='='; 
		return s; 
	}

	/**
	 * @return record length
	 */
	public int getDataRecordLength() { 
		int len = 0; 

		for (AttributeInSchema ais : this.attrs) 
			len += ais.getDataSize(); 
		return len; 
	}

	/**
	 * @param selectedAttrs
	 * @return subschema
	 */
	public TableSchema getSubschema(ArrayList<Integer> selectedAttrs) { 
		TableSchema newSchema = new TableSchema(selectedAttrs.size()); 


		return newSchema; 
	}
}
