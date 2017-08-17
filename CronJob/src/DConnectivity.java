import java.sql.Connection;
import java.sql.DriverManager;

public class DConnectivity {

	private String databaseUrl = "jdbc:mysql://52.168.86.200:3306/deal2bid";
	private String userName = "root";
	private String password = "MediaANV123";
	
	
	public Connection getDatabaseConnectivity() {

	 
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		

		try {
			connection = DriverManager.getConnection(databaseUrl, userName, password);

		} catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			return connection;
		} else {
			System.out.println("Failed to make connection!");
			return null;
		}
	}

}
