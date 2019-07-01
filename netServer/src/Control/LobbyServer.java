package Control;

import java.io.*;


import java.util.StringTokenizer;
import java.net.*;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import Control.*;

//프로토콜 만들기 
// %%로 구분

import java.util.ArrayList;

public class LobbyServer {
	
	ArrayList<DataOutputStream> clientsOut;
	private ArrayList<String> list_id;
	private protocolController pc;
	private String id;
	
	public LobbyServer() {
		try {
			list_id = new ArrayList<String>();
			clientsOut = new ArrayList<DataOutputStream>();
			pc = new protocolController();
			
			
			ServerSocket ss = new ServerSocket(7777);
			System.out.println("서버 가동");
			
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
				ServerThread s_thread = new ServerThread(id, socket, dis, dos);
				s_thread.start();
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	class ServerThread extends Thread{
		String msg;
		Socket socket;
		DataInputStream dis;
		DataOutputStream dos;
		String id;
		
		private ServerThread(String id, Socket socket, DataInputStream dis, DataOutputStream dos) {
			this.socket = socket;
			this.dis = dis;
			this.dos = dos;
			this.id= id;
		}
		
		public void run() {
			try {				
				while(true) {
					msg = dis.readUTF();					// client에서 서버로 받는 부분
					int num = pc.whichProtocol(msg);
					switch(num) {
					case 1 :						// 1 == ID_MSG 
						broadcast(msg);
					case 2 :			// 2 == LIST
						break;
					case 3 :			// 3 == ROOM
						int roomNum = pc.getRoomNum(msg);		// 몇번 방인지
						int port = roomNum + 9999;
						new RoomServer(port);
					}
				}
			} catch (IOException e) {			// 나감 
				try {
					dis.close();
					dos.close();
					socket.close();
					clientsOut.remove(dos);
					list_id.remove(id);
					String msg = pc.makeLogout(id);
					String m = pc.make_List(list_id);
					broadcast(m);
					broadcast(msg);
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