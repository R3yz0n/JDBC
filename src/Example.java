import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Example {
	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/JDBC";

		String user = "sagar";
		String password = "sagar123";

		try {

			Class.forName("org.postgresql.Driver");
			java.sql.Connection conn = DriverManager.getConnection(url, user, password);

			Statement statement = conn.createStatement();
			System.out.println("Connected to the PostgreSQL server successfully.\n\n");

			demonstrateScrollSensitiveWithRefresh(conn);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

	public static void demonstrateScrollSensitiveWithRefresh(Connection conn) {

		String query = "SELECT * FROM students";
		try (Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			ResultSet resultSet = statement.executeQuery(query);

			// Move to the first row and print initial value
			resultSet.first();
			System.out.println("Initial first_name: " + resultSet.getString("first_name"));

			// Simulate external update
			externalUpdate(conn);

			// Sleep for a moment to allow external update to be processed
			Thread.sleep(1000);

			// Explicitly refresh the current row to get the latest data from the database
			resultSet.refreshRow();
			System.out.println("Refreshed first_name: " + resultSet.getString("first_name"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void externalUpdate(Connection conn) {
		try (Statement statement = conn.createStatement()) {

			// External update to the database
			statement.executeUpdate("UPDATE students SET first_name = 'yoslo' WHERE id = 1");
			System.out.println("External Update: first_name set to sagar");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
