package carAccidentETL;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import com.csvreader.CsvReader;

public class functionClass {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://rm-uf6dhz6u4b9372838ao.mysql.rds.aliyuncs.com:3306/accidentData";
	static final String USER = "tairui123";
	static final String PASS = "tairui123A";

	public static void main(String[] args) {
		functionClass fClass = new functionClass();
		// load Data from CSV files to MySQL database
		fClass.accidentETL();
		fClass.vehiclesETL();
	}

	public boolean accidentETL() {
		Connection conn = null;
		String sql = "insert into accidents values(?,?,?,?)";// SQL statement
		try {
			CsvReader readerAccident = new CsvReader("Accidents0515.csv");
			readerAccident.readHeaders();

			while (readerAccident.readRecord()) {
				String accident_Index = readerAccident.get(0); // get(String) doesn't work for the first column, have no
																// clue at the moment. using get(int) as a workaround
				String day_of_Week = readerAccident.get("Day_of_Week");
				String accident_Time = readerAccident.get("Time");
				String road_Type = readerAccident.get("Road_Type");

//				System.out.println(accident_Index);						//test print
				/**
				 * to be continued need a few lines to write the info into MySQL table here
				 */

				// load JDBC driver
				Class.forName(JDBC_DRIVER);

				System.out.println("Preparing to write a new record in Accidents table");
				conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setString(1, accident_Index);
				pst.setInt(2, Integer.parseInt(day_of_Week));
				pst.setString(3, accident_Time);
				pst.setInt(4, Integer.parseInt(road_Type));
				pst.executeUpdate();

				System.out.println("Successfully wrote a new record to Accidents table");
				System.out.println("Accident ID:" + accident_Index + " completed");
				System.out.println("==============================================");
				pst.close();
				conn.close();

			}

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean vehiclesETL() {
		Connection conn = null;
		String sql = "insert into accidents values(?,?,?,?)";// SQL statement
		try {

			CsvReader readerVehicles = new CsvReader("Vehicles0515.csv");
			readerVehicles.readHeaders();

			while (readerVehicles.readRecord()) {
				String accident_Index = readerVehicles.get(0);
				String vehicle_Type = readerVehicles.get("Vehicle_Type");
				String sex_of_Driver = readerVehicles.get("Sex_of_Driver");
				String age_of_Driver = readerVehicles.get("Age_of_Driver");

				/**
				 * to be continued need a few lines to write the info into MySQL table here
				 */

				// load JDBC driver
				Class.forName(JDBC_DRIVER);
				System.out.println("Preparing to write a new record in Vehicles table");
				conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

				PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sql);
				pst.setString(1, accident_Index);
				pst.setInt(2, Integer.parseInt(vehicle_Type));
				pst.setInt(3, Integer.parseInt(sex_of_Driver));
				pst.setInt(4, Integer.parseInt(age_of_Driver));
				pst.executeUpdate();
				System.out.println("Successfully wrote a new record to Accidents table");
				System.out.println("Accident ID:" + accident_Index + " completed");
				System.out.println("==============================================");
				pst.close();
				conn.close();
			}

			return true;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
