package Control;

import java.sql.SQLException;

import Model.*;

import java.sql.*;


public class LoginController {
	
	private LoginModel lg;
	private DBController dc;
	private GUIController gc;
	
	
	public LoginController(DBController dc) {
		this.lg = null;
		this.dc = dc;
		this.gc = new GUIController();
	}
	
	
	public boolean signUp(String id, String pw, String name) {
		//ȸ������
		//DB�� �߰�
		
		ResultSet rs = null;
		Statement stmt = null;
		String s = "SELECT *FROM USER";
		
		try {
			String sql = "INSERT INTO USER (id, pw, name) VALUE('"+id+"','"+pw+"','"+name+"');";
			dc.initConnect();
			stmt = dc.getConn().createStatement();
			rs = stmt.executeQuery(s);
			while(rs.next()) {
				if(id.equals(rs.getString(1))) {
					return false;
				}
			
			}
			
			dc.updateSQL(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	public int login(String id, String pw) throws ClassNotFoundException, SQLException {
		//�α���
		// �ش� id�� pw�� db�� ������ ��, ������ ����
		
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "SELECT *FROM USER";
				
		
		dc.initConnect();
		stmt = dc.getConn().createStatement();
		rs = stmt.executeQuery(sql);

		while(rs.next()) {
			if(id.equals(rs.getString(1)) && pw.equals(rs.getString(2))) {
				
				if(rs.getInt(4) == 1) {
					// �ߺ� �α���
					return 2;
				}
				else {

					String s = "update user set isLogin = 1 where id = '"+id+"';";
					dc.updateSQL(s);
					return 0;
						
				}
			}
		}
		
		return 1;
		
	}
	
	public void setlg(LoginModel lg) {
		this.lg = lg;
	}
	
	public LoginModel getlg() {
		return this.lg;
	}
	
	
}
