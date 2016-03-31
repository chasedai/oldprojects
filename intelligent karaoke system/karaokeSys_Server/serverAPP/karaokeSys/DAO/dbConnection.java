package karaokeSys.DAO;


import java.sql.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;


public class dbConnection {
	protected Connection conn=null;
	protected Statement stmt;
	
	public void connectDatabase() {
		try {
			// 调用Class.forName()方法加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("Driver not found");
			e1.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/chaseKTV";

		try {
			conn = (Connection) DriverManager.getConnection(url, "root",
					"admin");
			stmt = (Statement) conn.createStatement();
			// stmt.close();
			// conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
