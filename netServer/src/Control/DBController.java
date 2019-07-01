package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
	
	public static final String initDB = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/network?characterEncoding=UTF-8&serverTimezone=UTC";
	public static final String user = "root";
	public static final String pwd = "dltjrrb1";
	
	
	
	private Connection conn;
	private String sql;
	private ResultSet rs;
	private Statement stmt;
	
	public DBController() {
		this.conn = null;
		this.sql = null;
		this.rs = null;
		this.stmt = null;
	}
	
	public void initConnect() throws ClassNotFoundException, SQLException {
		this.conn = connect();
	}
	
	public void enterSQL(String sql) throws ClassNotFoundException, SQLException {
		
		this.sql = sql;
		this.rs = null;
		this.stmt = null;
		
		stmt = this.conn.createStatement();
		rs = stmt.executeQuery(sql);
		
	}
	
	public void updateSQL(String sql) throws SQLException {
		this.stmt = this.conn.createStatement();
		stmt.executeUpdate(sql);
	}
	
	public void exitSQL(Connection conn) throws SQLException {
		if(this.rs != null) rs.close();
		if(this.stmt != null) stmt.close();
		if(conn != null) conn.close();
	}
	
	public Connection connect() throws ClassNotFoundException, SQLException{
		
		Class.forName(initDB);
		conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
	
	public Connection getConn() {
		return this.conn;
	}
	
	
	
	
//	public static void main(String []args) throws ClassNotFoundException, SQLException {
//		Connection conn = getCon();
//		String sql = "SELECT *FROM USER";
//		ResultSet rs = null;
//		Statement stmt = null;
//		
//		stmt = conn.createStatement();
//		rs = stmt.executeQuery(sql);
//		
//		while(rs.next()) {
//			String id = rs.getString(1);
//			String pw = rs.getString(2);
//			String name = rs.getString(3);
//			
//			System.out.println(id+" ,"+pw + " ,"+name);
//		}
//		
//		if(rs != null) rs.close();
//		if(stmt != null) stmt.close();
//		if(conn!= null) conn.close();
//	}
//	
//	public static Connection getCon() throws ClassNotFoundException, SQLException{
//		String url = "jdbc:mysql://localhost:3306/network?characterEncoding=UTF-8&serverTimezone=UTC";
//		String user = "root";
//		String pwd = "dltjrrb1";
//
//		Connection conn = null;
//		
//		Class.forName(initDB);
//		conn = DriverManager.getConnection(url,user,pwd);
//		
//		return conn;
//	}
	
}
