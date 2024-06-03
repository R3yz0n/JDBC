import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class Assignment {

	public static void main(String[]args)
	{

		
		String url="jdbc:postgresql://localhost:5432/JDBC";
		String user="sagar";
		String password="sagar123";
		
			try {
				Class.forName("org.postgresql.Driver");
				Connection connection=DriverManager.getConnection(url,user,password);
				System.out.println(connection);
				Statement statement=connection.createStatement();
				
				//CREATING A TABLE NAME STUDENTS
				String createStudentTable="CREATE TABLE IF NOT EXISTS students (\n"
						+ "    student_id SERIAL NOT NULL PRIMARY KEY,\n"
						+ "    first_name VARCHAR(50) NOT NULL,\n"
						+ "    last_name VARCHAR(50) NOT NULL,\n"
						+ "    date_of_birth DATE NOT NULL,\n"
						+ "    email VARCHAR(100) UNIQUE NOT NULL,\n"
						+ "    phone_number VARCHAR(15),\n"
						+ "    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP\n"
						+ ");\n"
						+ " ";
				

				System.out.println(createStudentTable);
				
			}
			catch(Exception e)
			{
				System.out.println("ERROR HAS OCCURED "+e);
			}
	}
}
