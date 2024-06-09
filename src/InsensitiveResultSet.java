import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class InsensitiveResultSet {

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
					ResultSet.CONCUR_READ_ONLY); // It is default

			ResultSet resultSet = statement.executeQuery(query);
			//Here after result has been created, the scrollable type being insensitive,
			// the changes in db throught another transation wont be reflected
			Thread.sleep(7000);

//			System.out.println(resultSet.next());
//			System.out.println(resultSet.getString("first_name"));
//			System.out.println(resultSet.next());
//			System.out.println(resultSet.previous());

			System.out.println(resultSet.absolute(10));
			System.out.println(resultSet.getArray(2));

			while (resultSet.next()) {
				System.out.println(resultSet.getObject("first_name"));

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
