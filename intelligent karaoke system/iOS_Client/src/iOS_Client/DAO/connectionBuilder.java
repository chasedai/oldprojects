package iOS_Client.DAO;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class connectionBuilder {

	protected Connection conn=null;
	protected Statement stmt;
	
	public void connectDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("success");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver not found");
			e1.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/chaseKTV";
		try {
			conn =  (Connection) DriverManager.getConnection(url, "root",
					"admin");
			stmt =  (Statement) conn.createStatement();
			System.out.print("Success");
			// stmt.close();
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
