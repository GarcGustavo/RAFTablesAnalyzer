package generalUtilities;

import dataManagementClasses.*;
import interfaces.DataReader;
import interfaces.DataWriter;

/**
 * @author Gustavo
 *
 */
public class DataUtils {

	//Formatting variables used to format data types as strings throughout the program
	public static int VALUEWIDE = 19;
	public static String BYTEFORMAT = "%"+VALUEWIDE +"X";
	public static String STRINGFORMAT = "%"+VALUEWIDE + "s"; 
	public static String CHARFORMAT = "%"+VALUEWIDE + "c"; 
	public static String INTEGERFORMAT = "%"+VALUEWIDE + "d";
	public static String SHORTFORMAT = "%"+VALUEWIDE + "d";
	public static String BOOLEANFORMAT = "%"+VALUEWIDE + "s"; 
	public static String FLOATFORMAT = "%"+VALUEWIDE + "f";
	public static String DOUBLEFORMAT = "%"+VALUEWIDE + "f"; 
	public static String LONGFORMAT = "%"+VALUEWIDE + "d";
	public static String DATEFORMAT = "%"+1+"3s/%02d/%4d"; 
	
	public static final TYPE[] TYPEList = {new TYPE("byte", Byte.BYTES, ByteDataReader.INSTANCE, ByteDataWriter.INSTANCE), 
										 new TYPE("char", Character.BYTES, CharDataReader.INSTANCE, CharDataWriter.INSTANCE), 
		                                 new TYPE("short", Short.BYTES, ShortDataReader.INSTANCE, ShortDataWriter.INSTANCE), 
		                                 new TYPE("int", Integer.BYTES, IntDataReader.INSTANCE, IntDataWriter.INSTANCE),
			                             new TYPE("long", Long.BYTES, LongDataReader.INSTANCE, LongDataWriter.INSTANCE), 
			                             new TYPE("float", Float.BYTES, FloatDataReader.INSTANCE, FloatDataWriter.INSTANCE), 
			                             new TYPE("double", Double.BYTES, DoubleDataReader.INSTANCE, DoubleDataWriter.INSTANCE), 
			                             new TYPE("boolean", 1, BooleanDataReader.INSTANCE, BooleanDataWriter.INSTANCE), 
			                             new TYPE("date", 4, DateDataReader.INSTANCE, DateDataWriter.INSTANCE)}; 

	/**
	 * @param tName
	 * @return type ID integer
	 */
	public static int getTypeID(String tName) { 
		for (int i=0; i<TYPEList.length; i++) 
			if (TYPEList[i].getName().equalsIgnoreCase(tName)) 
				return i; 
		return -1; 
	}
	
	/**
	 * @param tIndex
	 * @return type name string
	 * @throws IllegalArgumentException
	 */
	public static String getTypeName(int tIndex) throws IllegalArgumentException { 
		if (tIndex<0 || tIndex > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[tIndex].getName(); 
	}
	
	/**
	 * @param tName
	 * @return Size of specified type
	 */
	public static int getTypeSize(String tName) { 
		for (int i=0; i<TYPEList.length; i++) 
			if (TYPEList[i].getName().equals(tName)) 
				return TYPEList[i].getSize(); 
		return -1; 
	}

	/**
	 * @param index
	 * @return Size of specified type
	 */
	public static int getTypeSize(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getSize(); 
	}

	/**
	 * @param index
	 * @return Data reader for specified type
	 */
	public static DataReader getTypeDataReader(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getDataReader(); 		
	}
	
	/**
	 * @param index
	 * @return Data writer for specified type
	 */
	public static DataWriter getTypeDataWriter(int index) { 
		if (index<0 || index > TYPEList.length)
			throw new IllegalArgumentException("Invalid index for a valid data type."); 
		return TYPEList[index].getDataWriter(); 
		
	}
	
	/**
	 * @param tName
	 * @return Boolean indicating validity
	 */
	public static boolean isValidDataType(String tName) { 
		return getTypeID(tName) != -1; 
	}
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidName(String s) { 
		s = s.trim(); 
		if (s.length()==0 || s.length()>256) return false; 
		if (!Character.isLetter(s.charAt(0))) return false; 
		for (int i=1; i<s.length(); i++) 
			if (!Character.isLetter(s.charAt(i)) &&
				!Character.isDigit(s.charAt(i)) &&
				s.charAt(i) != '_') 
			   return false; 
		
		// if it reaches here, then it is a valid name
		return true; 
	}
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidInt(String s) { 
		try { 
			Integer.parseInt(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidBoolean(String s) {
		try {
			Boolean.parseBoolean(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidLong(String s) {
		try {
			Long.parseLong(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidShort(String s) {
		try {
			Short.parseShort(s);
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidChar(String s) {
		return s.length() == 1; 
	}

	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidByte(String s) {
		try {
			Byte.parseByte(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}

	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidFloat(String s) {
		try {
			Float.parseFloat(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}
	
	/**
	 * @param s
	 * @return Boolean indicating validity
	 */
	public static boolean isValidDouble(String s) {
		try {
			Double.parseDouble(s); 
			return true; 
		} catch(Exception e) { 
			return false; 
		}
	}


	/**
	 * @param month
	 * @param day
	 * @param year
	 * @return Boolean indicating validity
	 */
	public static boolean isValidDate(byte month, byte day, short year) { 
		if (year < 0)
			return false;
		if (month < 1 || month > 12) 
			return false; 
		
		int maxDays; 
		if (month == 2 && year % 4 == 0)   // month February on a leap year....
			maxDays = 29; 
		else 
			maxDays = Date.nDays(month-1); 
		if (day < 1 || day > maxDays)
			return false; 		

		// if it reaches here, then the date is valid as per the specs given
		return true;
	}
}
