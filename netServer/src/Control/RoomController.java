package Control;

import java.sql.*;


import Model.*;

public class RoomController {

	private RoomModel rm;
	private DBController dc;
	Statement stmt;
	ResultSet rs;
	
	public RoomController() {
		this.rm = new RoomModel();
		this.dc = new DBController();
	}
	
	
	
	public void makeRoom(RoomModel rm) {
		
		int num = rm.getRoomNum();
		String id = rm.getId();
		String pw = rm.getPw();
		
		String s = "Insert into roominfo (num, roomName, pw) value("+num+",'"+id+"','"+pw+"');";
		try {
			dc.initConnect();
			dc.updateSQL(s);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean enterRoom(String id, String pw, int roomNum) throws ClassNotFoundException, SQLException {
		
		String s = "select *from roominfo;";
		dc.initConnect();
		stmt = dc.getConn().createStatement();
		rs = stmt.executeQuery(s);
		
		while(rs.next()) {
			if(roomNum == rs.getInt(1) && id.equals(rs.getString(2)) && pw.equals(rs.getString(3))) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	public RoomModel findRoom(int roomNum) throws ClassNotFoundException, SQLException {
		String s = "select *from roominfo;";
		RoomModel tmp = null;
		dc.initConnect();
		stmt = dc.getConn().createStatement();
		rs = stmt.executeQuery(s);
		while(rs.next()) {
			if(rs.getInt(1) == roomNum) {
				tmp = new RoomModel();
				tmp.setRoomNum(roomNum);
				tmp.setId(rs.getString(2));
				tmp.setPw(rs.getString(3));
				break;
			}
		}
		
		return tmp;
		
	}
	
	public boolean deleteRoom(int roomNum) throws ClassNotFoundException, SQLException {
		RoomModel tmp = findRoom(roomNum);
		if(tmp == null) {
			return false;
		}
		else {
			String s = "delete from roomInfo where num = "+roomNum+";";
			dc.initConnect();
			dc.updateSQL(s);
			return true;
		}
	}
	
	
	
}
