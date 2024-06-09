import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {

	private static Statement statement;

	public static void main(String[] args) {
		String url = "jdbc:postgresql://localhost:5432/JDBC";
		String user = "sagar";
		String password = "sagar123";

		try {

			Class.forName("org.postgresql.Driver");

			Connection conn = DriverManager.getConnection(url, user, password);

			// Create a scrollable and read-only Statement
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Execute a query
			String query = "SELECT * FROM students";
			ResultSet resultSet = statement.executeQuery(query);
			
		
			System.out.println("----");
			System.out.println(resultSet);

			// Navigate to the last row
			if (resultSet.last()) {
				System.out.println("Last Row:");
				printRowData(resultSet);
			}

			// Navigate to the first row
			if (resultSet.first()) {
				System.out.println("\nFirst Row:");
				printRowData(resultSet);
			}

			// Navigate to the third row
			if (resultSet.absolute(3)) {
				System.out.println("\nRow at position 3:");
				printRowData(resultSet);
			}

			// Navigate to the previous row
			if (resultSet.previous()) {
				System.out.println("\nPrevious Row:");
				printRowData(resultSet);
			}

			// Navigate to the next row
			if (resultSet.next()) {
				System.out.println("\nNext Row:");
				printRowData(resultSet);
			}

			// Close the ResultSet and Statement
			resultSet.close();
			statement.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	private static void printRowData(ResultSet resultSet) throws SQLException {
		// Print data from the current row
		int id = resultSet.getInt("id");
		String first_name = resultSet.getString("first_name"); // Update with your column name
	
		System.out.println("ID: " + id + ", Name: " + first_name );
	}
}
