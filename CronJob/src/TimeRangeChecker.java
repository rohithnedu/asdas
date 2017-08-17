import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimeRangeChecker {

	private PreparedStatement fetchDateTime = null;
	
	Utility utObj = new Utility();
	SendNotification sNotific = new SendNotification();

	public TimeRangeChecker() {

	}

	public void TimeRangeChecker(long current_Start_Time, long end_Time) {

		Connection obtainedConnection;
		DConnectivity dConnectivity = new DConnectivity();
		obtainedConnection = dConnectivity.getDatabaseConnectivity();

		if (obtainedConnection != null) {
			System.out.println("Fetching dates From Database..");
			try {
				fetchDateTime = obtainedConnection
						.prepareStatement("select Id_Event,Start_Time,Date_Event from event_schedule ; ");
			} catch (SQLException e) {

				e.printStackTrace();
			}

			ArrayList<DataHolder> dataList = new ArrayList<DataHolder>();

			Integer i = 0;
			try {
				ResultSet rsnvv = fetchDateTime.executeQuery();

				while (rsnvv.next()) {

					i++;
					String startTime = rsnvv.getString("Start_Time");
					String Date_Event = rsnvv.getString("Date_Event");
					int id_Event = rsnvv.getInt("Id_Event");
					//System.out.println("Start_Time"+startTime+"Date_Event"+Date_Event+"Id_Event"+id_Event);
					DataHolder data = new DataHolder(startTime, Date_Event, id_Event);
					dataList.add(data);

				}
			} catch (Exception e) {
				System.out.println("Exception while executing the query fetchDateTime " + e.toString());
			}
			ArrayList<Integer> toNotifyEventID = new ArrayList<Integer>();

			try {
				for (DataHolder data : dataList) {
										long eventDatabaseTimeMilliSec = utObj.convertDateTimeToMilliSeconds(data.Date_Event,
							data.startTime);

					//System.out.println("Event Id " + data.getId_Event() + "Start Time" + eventDatabaseTimeMilliSec
						//	+ "currentSingapore time"+utObj.getCurrentTime_EventLocation());

					if ((eventDatabaseTimeMilliSec > current_Start_Time) && (eventDatabaseTimeMilliSec < end_Time)) {
						//System.out.print("inside true" + " edt" + eventDatabaseTimeMilliSec + "ct" + current_Start_Time
						//		+ "end_Time" + end_Time +"          "+data.getId_Event());
						toNotifyEventID.add(data.getId_Event());
						
						System.out.println(" Satisfied for Event Id " + data.getId_Event());
						
						
						
					} else {
						//System.out.println("The current Event ID is not in the time frame--->" + data.getId_Event());
						System.out.println(" failed for Event Id " + data.getId_Event() +"eventDatabaseTimeMilliSec"+eventDatabaseTimeMilliSec);
					}
					

				}
				obtainedConnection.close();
				sNotific.fetchEmailID(toNotifyEventID);
			} catch (Exception e) {
				System.out.println("Exception while executing the query fetchEmailID " + e.toString());
			}
		}

		else {
			System.out.println("Connection failed");
		}

	}

}
