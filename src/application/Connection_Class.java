package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Class {

	static String url = "jdbc:mysql://localhost:3306/ENSAO";
	static String username = "root";
	static String password = "mercedesamggt63!";
	public static Connection getConnection() throws SQLException {
       
			return DriverManager.getConnection(url, username, password);
		
		}

}
