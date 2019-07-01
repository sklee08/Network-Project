package Model;

public class RoomModel {
	
	private int roomNum;
	private String id;
	private String pw;
	
	public RoomModel(int roomNum, String id, String pw) {
		this.roomNum = roomNum;
		this.id = id;
		this.pw = pw;
	}
	
	public RoomModel() {
		this.roomNum = 0;
		this.id = "";
		this.pw = "";
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	
	
	
}
