import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Updatable {

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

			java.sql.Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			// updating first row
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();

			resultSet.updateString("first_name", "heyboi");
			resultSet.updateRow();
//			resultSet.refreshRow(); //I gotta ask question
//			Thread.sleep(1000);
			System.out.println(resultSet.getObject("first_name"));
			System.out.println("sucessfull updated the first row with first_name heyboi");

//			while (resultSet.next()) {
//				resultSet.updateString("first_name", "lamo");
//				resultSet.updateRow();
//				System.out.println(resultSet.getObject("first_name"));
//
//			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
