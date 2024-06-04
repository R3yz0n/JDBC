import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseOperation {

	private static Statement statement;

	public DatabaseOperation() {
		String url = "jdbc:postgresql://localhost:5432/JDBC";
		String user = "sagar";
		String password = "sagar123";

		try {
			Class.forName("org.postgresql.Driver");
			java.sql.Connection conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
			System.out.println("Connected to the PostgreSQL server successfully.\n\n");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		DatabaseOperation db = new DatabaseOperation();

//		createTables();
//		insertData();
//		readData();
//		DeleteStudentData();
//		DeleteTables();

	}

	public static void createTables() {

		try {

			boolean resultset1 = statement.execute(createCollegeTable);
			boolean resultset2 = statement.execute(createStudentsTable);

			System.out.println(resultset1);
			System.out.println(resultset2);

		} catch (SQLException e) {

			System.out.println("ERROR  " + e.getMessage());
		}
	}

	public static void insertData() {

		// Explicit importing class

		try {

			int resultset1 = statement.executeUpdate(insertColleges);
			int resultset2 = statement.executeUpdate(insertStudents);

			System.out.println("College rows affected  " + resultset1);
			System.out.println("Student rows affected  " + resultset2);

		} catch (Exception e) {
			System.out.println("ERROR  " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void readData() {
		// Note for natural join if college_id in student table must not be id id in

		try {

			ResultSet resultSet = statement.executeQuery(readData);

			System.out.println("Id\tFirst Name\tLast Name\tDOB\t \t\tPhone Number\tAge\tCollege Name\tLocation\tEmail");

			while (resultSet.next()) {
				System.out.println(resultSet.getObject("id") + "\t" + resultSet.getObject("first_name") + "\t\t"
						+ resultSet.getObject("last_name") + "\t\t" + resultSet.getObject("dob") + "\t" + "\t"
						+ resultSet.getObject("phone_number") + "\t" + resultSet.getObject("age") + "\t"
						+ resultSet.getObject("college_name") + "\t" + resultSet.getObject("location") + "\t"
						+ resultSet.getObject("email"));
			}

		} catch (Exception e) {
			System.out.println("ERROR  " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void DeleteStudentData() {

		String studentToBeDeleted = "DELETE FROM students where id=1";

		try {
			int resultset = statement.executeUpdate(studentToBeDeleted);
			System.out.println(resultset);

		} catch (Exception e) {

		}

	}

	public static void DeleteTables() {

		String deleteStudentsTable = "DROP table students";
		String deleteCollegeTable = "DROP table  colleges";

		try {
			boolean resultset1 = statement.execute(deleteStudentsTable);
			boolean resultset2 = statement.execute(deleteCollegeTable);
			System.out.println(resultset1);
			System.out.println(resultset2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();

		}

	}

	// Queries

	final static String createCollegeTable = "CREATE TABLE IF NOT EXISTS colleges ("
			+ "college_id SERIAL NOT NULL PRIMARY KEY," + "college_name VARCHAR(100) NOT NULL, "
			+ "location VARCHAR(100)" + ");";

	final static String createStudentsTable = "CREATE TABLE IF NOT EXISTS students ("
			+ "id SERIAL NOT NULL PRIMARY KEY, " + "first_name VARCHAR(50), " + "last_name VARCHAR(50), "
			+ "dob DATE NOT NULL, " + "email VARCHAR(100), " + "phone_number VARCHAR(15), " + "age INT, "
			+ "college_id INT NOT NULL, " + "FOREIGN KEY (college_id) REFERENCES colleges(college_id)" + ");";

	final static String insertColleges = "INSERT INTO colleges (college_name, location) VALUES "
			+ "('Nepathya', 'manigram'), " + "('New horizon', 'butwal');";

	final static String insertStudents = "INSERT INTO students (first_name, last_name, dob, email, phone_number, age, college_id) VALUES "
			+ "('John', 'Doe', '2000-01-01', 'john.doe@example.com', '1234567890', 24, 1), "
			+ "('Jane', 'Smith', '1999-02-02', 'jane.smith@example.com', '1234567891', 25, 1), "
			+ "('Michael', 'Brown', '2001-03-03', 'michael.brown@example.com', '1234567892', 23, 1), "
			+ "('Emily', 'Davis', '2002-04-04', 'emily.davis@example.com', '1234567893', 22, 1), "
			+ "('Daniel', 'Miller', '1998-05-05', 'daniel.miller@example.com', '1234567894', 26, 1), "
			+ "('Sophia', 'Wilson', '2000-06-06', 'sophia.wilson@example.com', '1234567895', 24, 2), "
			+ "('William', 'Moore', '1999-07-07', 'william.moore@example.com', '1234567896', 25, 2), "
			+ "('Olivia', 'Taylor', '2001-08-08', 'olivia.taylor@example.com', '1234567897', 23, 2), "
			+ "('James', 'Anderson', '2002-09-09', 'james.anderson@example.com', '1234567898', 22, 2), "
			+ "('Isabella', 'Thomas', '1998-10-10', 'isabella.thomas@example.com', '1234567899', 26, 2);";

	final static String readData = "Select * from colleges natural join students where college_id=1 ";
}
