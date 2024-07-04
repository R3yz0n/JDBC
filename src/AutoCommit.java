import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;



public class AutoCommit {
    public static void main(String[] args) {
        String JDBC_URL = "jdbc:postgresql://localhost:5432/JDBC";
        String JDBC_USER = "sagar";
        String JDBC_PASSWORD = "sagar123";

        try {
//        	Connection conn = DriverManager.getConnection(JDBC_URL)
        	Connection con  = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        	con.setAutoCommit(false);
        	CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
            cachedRowSet.setUrl(JDBC_URL);
            cachedRowSet.setUsername(JDBC_USER);
            cachedRowSet.setPassword(JDBC_PASSWORD);
//            cachedRowSet.getConnection().setAutoCommit (false);

//            cachedRowSet.getCo
            cachedRowSet.setCommand("SELECT * FROM colleges");
            cachedRowSet.execute(con);
            
            // Process data without maintaining continuous connection to DB
            while (cachedRowSet.next()) {
                System.out.println(cachedRowSet.getString("college_name"));
            }

            // Update data if required
            cachedRowSet.absolute(2);  // move to the 2nd row
            cachedRowSet.updateString("college_name", "New horizon");
            cachedRowSet.updateRow();

            // Sync changes back to database
            cachedRowSet.acceptChanges(con);
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}