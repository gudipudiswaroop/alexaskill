package wiseguy;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.Statement;

public class DBConnection {
	
	Connection conn = null;
	
	static final String DB_URL = "YOUR JDBC ENDPOINT URL HERE";

	   //  Database credentials
	   static final String USER = "YOUR DB SCHEMA USERNAME";
	   static final String PASS = "YOUR DB SCHEMA PASSWORD";
	
	

	public Connection getConnection(){
		Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName("com.mysql.jdbc.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		   }catch(ClassNotFoundException e){
			   System.out.println("exception is:"+e);
		   }
		   catch(SQLException e){
		        System.out.println("Caught SQL Exception: " + e);
		    }
		    return conn;
	}

	public void closeConnection() throws SQLException {
	    conn.close();
	}

}

