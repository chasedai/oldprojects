package karaokeSys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class loginValidator extends dbConnection{

	public boolean login(String username, String password) throws SQLException{
		ResultSet rs;
        try {
            connectDatabase();
            String Sql = "select * from user where username='"+username+"';";
			rs = stmt.executeQuery(Sql);
			rs.next();
			System.out.println(rs.getString("password"));
			if(password.equals(rs.getString("password"))==false){
				return false;

			}else{
				return true;
			}
        } catch (Exception e) {
            e.printStackTrace();
            return false;
		} finally {
			stmt.close();
			conn.close();
		}
       
		
	}
}
