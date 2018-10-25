package dbpkg;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconn {
	
	public static Connection getConnection() {
		Connection con = null;
		
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr", "hkitedu");
			System.out.println("연결 성공!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
