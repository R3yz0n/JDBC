import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueringAndMutation {

	// When we use execute() method it returns true in case of Querying eg SELECT
	// statement

	private static Statement statement;

	public QueringAndMutation() {

		// Driver is not need for jdbc above 4

		String url = "jdbc:postgresql://localhost:5432/JDBC";

		String user = "sagar";
		String password = "sagar123";

		try {

			// Connection is an interface defined in JRE so we cannot create a object out of
			// it

			// DriverManager is an class that returns the Connection interface that
			// implements the
			// RDBMS provider

//			When you obtain a database connection using DriverManager.getConnection(url, user, password),
//			This object represents the actual database connection and provides the functionality specified by the methods in the Connection interface.

			// Shortcut ma vannu parda conn is of type Connection interface which hold the
			// refrence to the class defined in the JDBC driver eg postgres

			Class.forName("org.postgresql.Driver");
			java.sql.Connection conn = DriverManager.getConnection(url, user, password);

			statement = conn.createStatement();
			System.out.println("Connected to the PostgreSQL server successfully.\n\n");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws Exception {

		QueringAndMutation db = new QueringAndMutation();
		/* */ System.out.println(db);

		createTables();
		insertData();
		readData();
//		UpdateRecord();
//		DeleteStudentData();
//		DeleteTables();
//		readData();
//		System.out.println(statement.getUpdateCount());
		statement.close();
	}

	public static void createTables() {

		try {
//Execute returns true when it's SELECT statement and further ResultSet and loop through it otherwise false and then we can use
// getUpdateCount() to check no of rows affected
			boolean isCreatedCollege = statement.execute(createCollegeTable);

			boolean isCreatedStudents = statement.execute(createStudentsTable);
//If getUpdateCount() shows 0 then it is DDL statement as per my guess

			System.out.println(statement.getUpdateCount() == 0 ? "DDL statement" : "DML mutation statements");
			System.out.println(!isCreatedCollege ? "yes students is created" : "no");
			System.out.println(!isCreatedStudents ? "yes colleges is created" : "no");

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

	public static void UpdateRecord() {

		try {
			boolean resultset = statement.execute(updateData); // returns false for mutation
			System.out.println(resultset);
			System.out.println(statement.getUpdateCount());

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();

		}

	}

	public static void DeleteTables() {

		String deleteStudentsTable = "DROP table students";
		String deleteCollegeTable = "DROP table  colleges";

		try {
			boolean resultset1 = statement.execute(deleteStudentsTable);
			boolean resultset2 = statement.execute(deleteCollegeTable);
			System.out.println();
			System.out.println(statement.getResultSet());

			System.out.println(resultset1);
			System.out.println(resultset2);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();

		}

	}

	// Queries

	public static void MultipleQueries() {

		String query1 = "Select * from student where id='1' ";
//		private String query2="Select * from student where id='1' ";

	}

	private final static String createCollegeTable = "CREATE TABLE IF NOT EXISTS colleges ("
			+ "college_id SERIAL NOT NULL PRIMARY KEY," + "college_name VARCHAR(100) NOT NULL, "
			+ "location VARCHAR(100)" + ");";

	private final static String createStudentsTable = "CREATE TABLE IF NOT EXISTS students ("
			+ "id SERIAL NOT NULL PRIMARY KEY, " + "first_name VARCHAR(50), " + "last_name VARCHAR(50), "
			+ "dob DATE NOT NULL, " + "email VARCHAR(100), " + "phone_number VARCHAR(15), " + "age INT, "
			+ "college_id INT NOT NULL, " + "FOREIGN KEY (college_id) REFERENCES colleges(college_id)" + ");";

	private final static String insertColleges = "INSERT INTO colleges (college_name, location) VALUES "
			+ "('Nepathya', 'manigram'), " + "('New horizon', 'butwal');";

	private final static String insertStudents = "INSERT INTO students (first_name, last_name, dob, email, phone_number, age, college_id) VALUES "
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

	private final static String readData = "Select * from colleges natural join students where college_id=1 ";

	private final static String updateData = "Update students set first_name='xyzzzzzzzz' where first_name='John'";
}
