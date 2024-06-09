import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ForwardOnlyResultSet {
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

			java.sql.Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY); // It is default

			ResultSet resultSet = statement.executeQuery(query);

//			System.out.println(resultSet.next());
			System.out.println(resultSet.getArray(2));

//			System.out.println(resultSet.previous());

//			while (resultSet.next()) {
//				System.out.println(resultSet.getObject("first_name"));
//
//			}



		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
