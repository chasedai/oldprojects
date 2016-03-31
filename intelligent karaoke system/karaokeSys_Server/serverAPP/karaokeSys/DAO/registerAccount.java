package karaokeSys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class registerAccount extends dbConnection{

	public boolean register(String username, String password) throws SQLException{
		PreparedStatement ps = null;
        ResultSet rs;
        try {
            connectDatabase();
            ps = (PreparedStatement) conn.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            ps.close();
            stmt.close();
			conn.close();
			return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
		} finally {
			ps.close();
			stmt.close();
			conn.close();
		}
       
		
	}
}
