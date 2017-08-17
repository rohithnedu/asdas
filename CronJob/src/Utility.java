import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utility {
	
	
	public  long convertDateTimeToMilliSeconds(String dateInString) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm");			
			Date date = sdf.parse(dateInString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);			
			return calendar.getTimeInMillis();
		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return 0;

	}
	
	
	public  long getCurrentTime_EventLocation() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm", Locale.ENGLISH);
		TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
		sdf.setTimeZone(tz);
		java.util.Date date1 = new java.util.Date();
		Timestamp local = new Timestamp(date1.getTime());
		String strDate = sdf.format(date1);
	 	//System.out.println("strDate       "+strDate);
		return this.convertDateTimeToMilliSeconds(strDate);

	}

	public  long convertDateTimeToMilliSeconds(String dateRec, String timeRec) {
		try {
			//System.out.println("timeRec--->"+timeRec);
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm");
			String dateInString = dateRec + " " + timeRec;
			Date date = sdf.parse(dateInString);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar.getTimeInMillis();

		} catch (Exception e) {
			System.out.println("Exception is " + e);
		}
		return 0;

	}

}
