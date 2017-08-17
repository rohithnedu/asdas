
public class CronRunner {

	public static void main(String[] args) {
		
		
		
		TimeRangeChecker timeRange = new TimeRangeChecker();
		
		Utility utobj = new Utility();
		long curr_Time = utobj.getCurrentTime_EventLocation();
		long end_Time = 300000;
	    end_Time =  curr_Time + end_Time;
	   
	    timeRange.TimeRangeChecker(curr_Time, end_Time);
        
	}

}
