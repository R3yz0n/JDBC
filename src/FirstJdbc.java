
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
public class FirstJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		
	
		String url="jdbc:postgresql://localhost:5432/JDBC";
		String user="sagar";
		String password="sagar123";
		
			Class.forName("org.postgresql.Driver");
			Connection connection=DriverManager.getConnection(url,user,password);
			System.out.println(connection);
			Statement statement=connection.createStatement();
			
			
//			String createTable="Create table users(Id INT PRIMARY KEY,NAME VARCHAR(50))";
			String readData="Select * from users";
			ResultSet resultset=statement.executeQuery(readData);
//			System.out.println(resultset);
			
			while(resultset.next())
			{
				System.out.println("Name  "+resultset.getObject("name"));
			}
			
//			String insertData="INSERT INTO";
//			ResultSet resultset=statement.executeQuery(insertData);

			

//			resultSet.
		
		}
		catch(Exception e)
		{
			System.out.println(" "+e);
		}

	}

}
