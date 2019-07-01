package Control;

import java.util.StringTokenizer;
import java.util.ArrayList;

public class protocolController {
	
	private final static String LOGOUT = "LOGOUT";				// �α׾ƿ� ��������
	private final static String ID_MSG = "ID/MSG";				// id�� �޽��� ������ ��������
	private final static String BLANK = "%%"; 					// ���� ����
	private final static String LIST = "LIST";					// ����Ʈ �޽��� ��������
	private final static String ROOM = "ROOM";					// �� ���� ��������
	private final static String EXIT = "EXIT";					// â ������ ��������
	private final static String SENDFILE = "SEND/FILE";			// ���� ���� ��������
	private final static String BIDMONEY = "BID/MONEY";			// �� ��� ��������
	private final static String BIDSTART = "BID/START";			// ��� ���� ��������
	private final static String LOGIN = "LOGIN";
	private final static String EXITROOM = "EXIT/ROOM";
	private final static String BIDEND = "BIDEND";
	
	private String send_msg;
	
	
	public void protocolController() {
		this.send_msg = null;
	}
	
	
	
	public int whichProtocol(String msg) {
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		String first = st.nextToken();
		
		if(first.equals(LOGOUT)) {
			return 0;
		}
		else if(first.equals(ID_MSG)) {
			return 1;							// 1 == ID_MSG
		}
		else if(first.equals(LIST)) {			
			return 2;							// 2 == LIST
		}
		else if(first.equals(ROOM)){			// 3 == ROOM
			return 3;
		}
		else if(first.equals(EXIT)){			//4 == EXIT
			return 4;
		}
		else if(first.equals(SENDFILE)) {		//5 == SENDFILE
			return 5;
		}
		else if(first.equals(BIDMONEY)) {
			return 6;
		}
		else if(first.equals(BIDSTART)) {
			return 7;
		}
		else if(first.equals(LOGIN)) {
			return 8;
		}
		else if(first.equals(EXITROOM)) {
			return 9;
		}
		else if(first.equals(BIDEND)) {
			return 10;
		}
		else {
			return -1;
		}
		
	}
	
	public String makeBidEnd(String id, int money) {
		String ret = "";
		ret = BIDEND + BLANK + id + BLANK + money;
		return ret;
	}
	
	public String getBidEnd(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public int getBidEndMoney(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		ret = st.nextToken();
		int re = Integer.parseInt(ret);
		return re;
	}
	
	public String makeExitRoom(String id) {
		String ret = "";
		ret = EXITROOM + BLANK + id;
		return ret;
	}
	
	public String getExitRoom(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public String makeLogin(String in) {
		String ret = "";
		ret = LOGIN + BLANK + in;
		return ret;
	}
	
	public String getLogin(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public String makeLogout(String out) {
		String ret = "";
		ret = LOGOUT + BLANK + out;
		return ret;
	}
	
	public String getLogout(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public String makeBidStart(String bid) {
		String ret = "";
		ret = BIDSTART + BLANK + bid;
		return ret;
	}
	
	public String getBidStart(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public String makeBidMoney(String id, int money) {
		String ret = "";
		ret = BIDMONEY + BLANK + money+ BLANK + id;
		return ret;
	}
	
	public String getBidId(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret =st.nextToken();
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public int getBidMoney(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		int re = Integer.parseInt(ret);
		return re;
	}
	
	public String makeFile(String msg) {
		String ret = "";
		ret = SENDFILE+BLANK+msg;
		return ret;
	}
	
	public String getFileProto(String msg) {
		String ret;
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	
	public String make_ID_MSG(String id, String msg) {				//ID/MSG ���� ������ִ� �Լ�
		send_msg = "";
		send_msg = ID_MSG + BLANK + id + BLANK + msg;
		return send_msg;
	}
	
	public String make_List(ArrayList<String> arr) {				// ����Ʈ���� id ��� ����Ʈ ������ִ� �Լ�
		send_msg = "";
		send_msg = LIST + BLANK;
		int size = arr.size();
		int i = 0;
		
		for(i = 0; i<size; i++) {
			send_msg = send_msg + arr.get(i) +BLANK;
		}
		
		return send_msg;
	}
	
	public String getID_from_ID_MSG(String msg) {			//ID_MSG ���Ŀ��� ID�о�ͼ� ����
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		String ret = st.nextToken();
		ret =st.nextToken();
		return ret;
	}
	
	public String getMSG_from_ID_MSG(String msg) {			//ID_MSG ���Ŀ��� MSG�о�ͼ� ����
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		String ret = st.nextToken();
		ret = st.nextToken();
		ret = st.nextToken();
		return ret;
	}
	
	public ArrayList<String> getList_from_LIST(String msg){		// ����Ʈ �������� ���� String�� �ִ� id ��ϵ��� ���� list �������ִ� �Լ�
		ArrayList<String> ret = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		String first = st.nextToken();
		
		while(st.hasMoreTokens()) {
			ret.add(st.nextToken());
		}
		
		return ret;
		
	}
	
	public String makeRoomEnterProtocol(int num) {
		String ret = "";
		ret = ROOM + BLANK + num;
		return ret;
	}
	
	public int getRoomNum(String proto) {
		StringTokenizer st = new StringTokenizer(proto, BLANK);
		String ret = st.nextToken();
		ret = st.nextToken();
		int re = Integer.parseInt(ret);
		return re;
	}
	
	public String makeExit(int roomNum) {
		String ret = "";
		ret = EXIT + BLANK + roomNum;
		return ret;
	}
	
	
	public int getExitRoomNum(String msg) {
		StringTokenizer st = new StringTokenizer(msg, BLANK);
		String ret = st.nextToken();
		ret = st.nextToken();
		int re = Integer.parseInt(ret);
		return re;
	}
	
	
	
}
