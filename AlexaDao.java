package wiseguy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class AlexaDao {

	
	
    
    public boolean updateData(String user, String email, double actual, double target){
    	String sql = "update USERINFO set ACTUAL_WEIGHT=?, TARGET_WEIGHT=? where EMAIL='"+email+"'";
          Connection conn = null;
		
		try {
			conn = DBUtils.getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//System.out.println("limit inside updateLimit"+limit);
			stmt.setDouble(1, actual);
			stmt.setDouble(2, target);
			if(stmt.executeUpdate()>0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public boolean updateEvent(String email, String event){
    	String sql = "update EVENTINFO set EVENT_DATE=?where EMAIL="+email;
          Connection conn = null;
		
		try {
			conn = DBUtils.getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			//System.out.println("limit inside updateLimit"+limit);
			stmt.setString(1, event);
			if(stmt.executeUpdate()>0)
				return true;
			else
				return false;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public long insertData(String user, String email, double actual, double target){
		String sql = "insert into USERINFO values(?,?,?,?)";
		Connection conn = null;
		int update = 0;
		
		try {
			conn = DBUtils.getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
            
			stmt.setString(1, user);
			stmt.setString(2,email);
			stmt.setDouble(3, actual);
			stmt.setDouble(4, target);
			update = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return update;
	}
    
    
public int insertToken(String token, String email) {
	
	Connection conn = null; 
	PreparedStatement stmt = null;
	int update = 0;
	
	String query = " insert into TOKENINFO (TOKEN, EMAIL)"
        + " values (?, ?)";
	//Calendar calendar = Calendar.getInstance();
    //java.sql.Date createdDate = new java.sql.Date(calendar.getTime().getTime());
    
    //java.sql.Timestamp createdDate = new java.sql.Timestamp(calendar.getTime().getTime());
	try {

		conn = DBUtils.getDBConnection();
		stmt = conn.prepareStatement(query);
		stmt.setString(1, token);
		stmt.setString(2, email);
		
		
		
		update = stmt.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return update;
	
}

public boolean getUser(String email){
	Connection conn = null; 
	PreparedStatement stmt = null;
	boolean flag = false;
	
	String query = "SELECT * FROM USERINFO where EMAIL='"+email+"'";
	String username=null;
	try {

		conn = DBUtils.getDBConnection();
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
		flag=true;
        
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return flag;
	
}

public long insertEvent(String email, String event){
	String sql = "insert into EVENTINFO values(?,?)";
	Connection conn = null;
	int update = 0;
	
	try {
		conn = DBUtils.getDBConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
        
		stmt.setString(1, email);
		stmt.setString(2,event);
		
		update = stmt.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return update;
}

public boolean getEventDate(String email, String event_date){
	Connection conn = null; 
	PreparedStatement stmt = null;
	boolean flag = false;
	String eventid="";
	
	String query = "SELECT EVENT_DATE FROM EVENTINFO where EMAIL='"+email+"' and EVENT_DATE='"+event_date+"'";
	
	try {

		conn = DBUtils.getDBConnection();
		stmt = conn.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()){
		// eventid=rs.getString("EVENT_DATE");
		flag=true;
		}else{
			flag=false;
		}
        
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return flag;
	
}
}
