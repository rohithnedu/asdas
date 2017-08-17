import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SendNotification {

	private PreparedStatement fetchEmailID = null;
	@SuppressWarnings("unused")
	private PreparedStatement fetchNotificationPref = null;

	
	public void fetchEmailID(ArrayList<Integer> eventList) {

		Connection obtainedConnection;
		DConnectivity dConnectivity = new DConnectivity();
		obtainedConnection = dConnectivity.getDatabaseConnectivity();

		if (obtainedConnection == null) {

			System.out.println("Connection failed");
			return;
		}
		System.out.println("Fetching emailID who want to be notified of the events--Line 24.");
		try {

			fetchEmailID = obtainedConnection.prepareStatement(
					" SELECT * FROM notif_pref WHERE email IN   (SELECT email FROM eventnotification WHERE event_id=?)");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ArrayList<String> emailToBeNotified = new ArrayList<String>();

		for (int eventID : eventList) {

			try {
				System.out.println("Line 40");
				fetchEmailID.setInt(1, eventID);
				ResultSet resultEmailId = fetchEmailID.executeQuery();

				while (resultEmailId.next()) {
					emailToBeNotified.add(resultEmailId.getString("email"));
				}

			} catch (Exception e) {
				System.out.print("in Catch");
			}
		}

		getNotificationpreference(emailToBeNotified, obtainedConnection);

	}

	private boolean getNotificationpreference(ArrayList<String> emailList, Connection con) {

		try {
			fetchNotificationPref = con.prepareStatement("select pref from notif_pref where email = ?");
		} catch (SQLException e) {

			e.printStackTrace();
		}

		for (String s : emailList) {
			try {
				fetchNotificationPref.setString(1, s);
				ResultSet notifPref = fetchNotificationPref.executeQuery();

				while (notifPref.next()) {
					System.out.println("Line 69 int pref-->" + notifPref.getInt("pref") + "Email ID is    " + s);
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}

		return false;

	}
}
