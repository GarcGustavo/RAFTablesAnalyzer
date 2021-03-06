package dataManagementClasses;

import java.security.InvalidParameterException;

import generalUtilities.DataUtils;


/**
 * @author 
 *
 */
public class Date implements Comparable<Date> {
	private static int NDAYSPERMONTH[] = {31, 28, 31, 30, 31, 30, 
		                                  31, 31, 30, 31, 30, 31}; 
	private static String MONTHNAME[] = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", 
		                                 "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; 
	private byte month, day; 
	private short year; 
	
	/**
	 * @param month
	 * @param day
	 * @param year
	 * @throws InvalidParameterException
	 */
	public Date(byte month, byte day, short year) throws InvalidParameterException { 
		if (!DataUtils.isValidDate(month, day, year))
			throw new InvalidParameterException("Given date is not valid."); 
		this.month = month; 
		this.day = day; 
		this.year = year; 
		
	}
	/**
	 * @return byte representation of month
	 */
	public byte getMonth() {
		return month;
	}
	/**
	 * @return byte representation of day
	 */
	public byte getDay() {
		return day;
	}
	/**
	 * @return byte representation of year
	 */
	public short getYear() {
		return year;
	}

	public String toString() { 
		return String.format(DataUtils.DATEFORMAT, MONTHNAME[month-1], day, year); 
	}
	
	public static int nDays(int month) { 
		return NDAYSPERMONTH[month]; 
	}
	
	public boolean equals(Object other) { 
		if (other == null) return false; 
		if (!(other instanceof Date)) return false; 
		Date otherDate = (Date) other; 
		return this.month == otherDate.month &&
				this.day == otherDate.day &&
				this.year == otherDate.year; 
	}
	
	public int compareTo(Date other) { 
		if (this.year < other.year) return -1; 
		if (this.year > other.year) return 1; 
		if (this.month < other.month) return -1; 
		if (this.month > other.month) return 1; 
		if (this.day < other.day) return -1;
		if (this.day > other.day) return 1; 
		// if this point is reached, then they are equal
		return 0; 
	}
}
