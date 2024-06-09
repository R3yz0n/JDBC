import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PreparedStatementDemo {
	private static Connection conn;

	public PreparedStatementDemo() {

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

	}

	public static void main(String[] args) {

		PreparedStatementDemo pDemo = new PreparedStatementDemo();
		AddCollege("New College", "Kathmandu");

	}

	public static void AddCollege(String name, String location) {
		String query = "INSERT INTO COLLEGES (college_name,location) VALUES(?,?)";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, location);
			System.out.println("Rows inserted " + statement.executeUpdate());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
