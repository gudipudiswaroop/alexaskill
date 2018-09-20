package alexaskill;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
	
	private static Connection conn = null;
	
	private static DBConnection connection = new DBConnection();

	public static Connection getDBConnection() {
		
		
		try {
			if(conn == null || conn.isClosed()) {
				conn = connection.getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return conn;
	}
}
