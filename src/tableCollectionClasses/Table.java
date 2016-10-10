package tableCollectionClasses;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * @author 
 *
 */
public class Table {
	private TableSchema ts; 
	private ArrayList<Record> rList; 
	private int recordLength; 
	
	/**
	 * @param ts table schema
	 */
	public Table(TableSchema ts) { 
		this.ts = ts; 
		recordLength = ts.getDataRecordLength(); 
		rList = new ArrayList<>(); 
	}
	
	/**
	 * @param index
	 * @return returns attribute in schema
	 */
	public AttributeInSchema getAttribute(int index) { 
		return ts.getAttr(index); 
	}
	
	/**
	 * @return number of attributes
	 */
	public int getNumberOfAttrs() { 
		return ts.getNumberOfAttrs(); 
	}
	
	/**
	 * @return new record instance
	 */
	public Record getNewRecordInstance() { 
		return new Record(ts); 
	}
	
	/**
	 * @param r adds new record to list
	 */
	public void addRecord(Record r) { 
		rList.add(r); 
	}
	
	/**
	 * @param index
	 * @return record at index
	 */
	public Record getRecord(int index) { 
		return rList.get(index); 
	}
	
	/**
	 * @return record length
	 */
	public int getRecordLength() { 
		return recordLength; 
	}
	
	/**
	 * displays table schema
	 */
	public void displaySchema() { 
		System.out.println(ts); 
	}
	
	/**
	 * displays table of table schema
	 */
	public void displayTable() { 
		this.displaySchema();
		for(Record r : rList) 
			System.out.println(r); 
	}
	
	/**
	 * @param file
	 * @throws IOException
	 */
	public void readTableDataFromFile(RandomAccessFile file) throws IOException {

		long numberOfDataRecords = 
				(file.length() - file.getFilePointer())/ts.getDataRecordLength(); 
				
		for (int dr = 1; dr <= numberOfDataRecords; dr++) {
			Record record = getNewRecordInstance(); 
			record.readFromFile(file);
			addRecord(record); 
		}

	}
}
