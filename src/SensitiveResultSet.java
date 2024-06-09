import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class SensitiveResultSet {

	// Scrollable result set
	private static Connection conn;

	public static void main(String[] args) {

		String url = "jdbc:postgresql://localhost:5432/JDBC";

		String user = "sagar";
		String password = "sagar123";

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("Connected to the PostgreSQL server successfully.\n\n");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		printColleges();

	}

	public static void printColleges() {
		String query = "Select * from students";
		try {

			java.sql.Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY); // It is default

			ResultSet resultSet;

			resultSet = statement.executeQuery(query);
			
			//Here after result has been created, the scrollable type being sensitive,
			// the changes in database through another transaction will be reflected
			Thread.sleep(7000);
			
            Thread.sleep(8000); 


			while (resultSet.next()) {
				System.out.println(resultSet.getObject("first_name"));

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
