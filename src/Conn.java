
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

	private String url = "jdbc:postgresql://localhost:5432/JDBC";
	private String user = "sagar";
	private String password = "sagar123";

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the PostgreSQL server successfully.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
//			System.out.println(e);
		}
		return conn;
	}

}


