import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

class Date {
	public int day;
	public int month;
	public int year;
	
	Date(int day, int month, int year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public static void DateValidator(int day, int month, int year) throws NotValidDateException{
		try {
	        LocalDate.of(year, month, day);
	    } catch (DateTimeException e) {
	        throw new NotValidDateException("Exception: Invalid date: " + day + "/" + month + "/" + year);
	    }
	}
}
