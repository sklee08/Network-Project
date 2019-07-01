package Control;

import java.io.*;


import java.util.StringTokenizer;
import java.net.*;
import java.sql.SQLException;


import Control.*;

//프로토콜 만들기 
// %%로 구분

import java.util.ArrayList;
import java.util.LinkedList;

public class RoomServer {
	
	ArrayList<DataOutputStream> clientsOut;
	private ArrayList<String> list_id;
	private protocolController pc;
	private RoomController rc;
	private String id;
	private LinkedList<String> listBid;
	
	public RoomServer(int port) {
		try {
			list_id = new ArrayList<String>();
			clientsOut = new ArrayList<DataOutputStream>();
			pc = new protocolController();
			rc = new RoomController();
			listBid = new LinkedList<String>();
			
			ServerSocket ss = new ServerSocket(port);
			
			while(true) {
				Socket socket = ss.accept();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				clientsOut.add(dos);				
				
				
				

				id = dis.readUTF();
				dupAdd(list_id, id);
				String ex = pc.make_List(list_id);
				String e = pc.makeLogin(id);
				broadcast(ex);
				broadcast(e);
				RoomServerThread roomThread = new RoomServerThread(id, socket, dis, dos);
				roomThread.start();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	class RoomServerThread extends Thread{
		String msg;
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;
		RoomServer rs;
		String id;
		
		private RoomServerThread(String id, Socket socket, DataInputStream dis, DataOutputStream dos) {
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.id = id;
		}
		
		public void run() {
			try {
				
				
				while(true) {
					msg = dis.readUTF();					// client에서 서버로 받는 부분
					int num = pc.whichProtocol(msg);
					switch(num) {
					case 1 :						// 1 == ID_MSG 
						broadcast(msg);
						break;
					case 2 :			// 2 == LIST
						break;
					case 3 :			// 3 == ROOM
						break;
					case 4 :				
						// exit id 가 들어옴
						int i = pc.getExitRoomNum(msg);						
						try {
							rc.deleteRoom(i);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case 5 :						// file name이 들어옴
						String filename = pc.getFileProto(msg);
						broadcast(msg);
						break;
					case 6 :
						String l = pc.getBidId(msg);
						listBid.add(l);
						broadcast(msg);
						break;
					case 7 :
						broadcast(msg);
						break;
					case 8 :
						broadcast(msg);
						break;
					case 9 :
						broadcast(msg);
						break;
					case 10 :
						String mm = listBid.getLast();
						int mone = pc.getBidEndMoney(msg);
						String mmm = "BIDEND%%"+mm+"%%"+mone;
						broadcast(mmm);
						break;
					}
				}
			} catch (Exception e) {			// 나감 
				
				try {
					dis.close();
					dos.close();
					socket.close();
					clientsOut.remove(dos);
					list_id.remove(id);
					String msg = pc.makeLogout(id);
					String m = pc.make_List(list_id);
					broadcast(msg);
					broadcast(m);
				} catch(IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	public void broadcast(String msg) {
		synchronized(clientsOut) {
			for(DataOutputStream out : clientsOut) {
				try {
					out.writeUTF(msg);
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	
	public void dupAdd(ArrayList<String> arr, String id) {
		int size = arr.size();
		int i = 0;
		boolean is = true;
		
		for(i = 0; i< size; i++) {
			if(arr.get(i).equals(id)) {
				is = false;
				break;
			}
		}
		if(is) {
			arr.add(id);
		}
		
	}
	

}