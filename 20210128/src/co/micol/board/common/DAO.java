package co.micol.board.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "micol";
	private String password = "1234";
	
	public Connection conn;
	
	public DAO() {
		try {
			Class.forName(driver); //드라이버를 로드해주는 작업.
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
}
