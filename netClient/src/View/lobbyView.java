package View;

import java.awt.BorderLayout;

import Control.*;
import Model.RoomModel;

import java.net.*;
import java.sql.SQLException;
import java.io.*;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Control.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class lobbyView extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsg;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private JTextArea textArea;
	private String id;
	private ArrayList<String> list;
	private protocolController pc;
	private JTextArea listArea;
	private JScrollPane chatScroll;
	private JLabel lblNewLabel_1;
	private GUIController gc;
	private RoomController rc;
	private JTextArea roomField1;
	private JTextArea roomField2;
	private JTextArea roomField3;
	private JTextArea roomField4;
	private DBController dc;
	private JLabel lblNewLabel_2;
	
	// 프로토콜
	// %%로 구분 


	public lobbyView(String id) {
		
		
		this.id = id;
		this.gc = new GUIController();
		rc = new RoomController();
		dc = new DBController();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					dc.initConnect();
					System.out.println(id);
					String s = "update user set isLogin = 0 where id = '"+id+"';";
					dc.updateSQL(s);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});
		
		setTitle("LOBBY");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 788, 777);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		
		JLabel lblNewLabel = new JLabel("MSG :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(-28, 706, 126, 32);
		contentPane.add(lblNewLabel);
		
		JButton createRoomButton = new JButton("\uBC29\uB9CC\uB4E4\uAE30");
		createRoomButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				// 방만들기 버튼
				
				gc.popCreateRoomView(id,socket);
				
				
				
			}
		});
		createRoomButton.setFont(new Font("굴림", Font.PLAIN, 15));
		createRoomButton.setBounds(12, 10, 126, 44);
		contentPane.add(createRoomButton);
		
		JButton start_chat = new JButton("\uACBD\uB9E4\uC7A5 \uC0C1\uD0DC \uAC31\uC2E0");
		start_chat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {							// 경매장 방 경신
								
				try {
					
					RoomModel rm1 = rc.findRoom(1);
					RoomModel rm2 = rc.findRoom(2);
					RoomModel rm3 = rc.findRoom(3);
					RoomModel rm4 = rc.findRoom(4);
					
					roomField1.setText("");
					roomField2.setText("");
					roomField3.setText("");
					roomField4.setText("");
					
					if(rm1 != null) {
						
						roomField1.append(rm1.getRoomNum()+"번 방\n\n");
						roomField1.append("방 제목 : "+rm1.getId()+"\n\n");
					}
					
					if(rm2 != null) {
						
						roomField2.append(rm2.getRoomNum()+"번 방\n\n");
						roomField2.append("방 제목 : "+rm2.getId()+"\n\n");
					}
					
					if(rm3 != null) {
						
						roomField3.append(rm3.getRoomNum()+"번 방\n\n");
						roomField3.append("방 제목 : "+rm3.getId()+"\n\n");
					}
					
					if(rm4 != null) {
						
						roomField4.append(rm4.getRoomNum()+"번 방\n\n");
						roomField4.append("방 제목 : "+rm4.getId()+"\n\n");
					}		
					
					
					
				} catch(Exception e2) {
					System.out.println("Cannot find the room!");
				}
				
			}
		});
		
		start_chat.setFont(new Font("굴림", Font.PLAIN, 15));
		start_chat.setBounds(166, 10, 162, 44);
		contentPane.add(start_chat);
		
		JButton button_1 = new JButton("\uBC29 \uC811\uC18D");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				// 방접속
				
				gc.popEnterRoomView(id);
				// 접속 버튼
				
			}
		});
		button_1.setFont(new Font("굴림", Font.PLAIN, 15));
		button_1.setBounds(351, 10, 126, 44);
		contentPane.add(button_1);
		
		txtMsg = new JTextField();
		txtMsg.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				if(code == KeyEvent.VK_ENTER) {
					// 엔터 입력시
					try {
						if(dis != null) {							// 클라이어트에서 서버로 전송 

							String send_msg = pc.make_ID_MSG(id, txtMsg.getText());
							dos.writeUTF(send_msg);
							txtMsg.setText("");
						}
						else {
							
						}
					}catch(IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});

		txtMsg.setForeground(new Color(0, 0, 0));
		txtMsg.setBounds(68, 706, 692, 32);
		contentPane.add(txtMsg);
		txtMsg.setColumns(10);
		
		chatScroll = new JScrollPane();
		chatScroll.setBounds(12, 560, 748, 139);
		contentPane.add(chatScroll);
		
		textArea = new JTextArea();
		chatScroll.setViewportView(textArea);
		textArea.setText(this.id+"님이 입장하였습니다.\n");

		listArea = new JTextArea();
		listArea.setBounds(12, 148, 126, 340);
		contentPane.add(listArea);
		
		lblNewLabel_1 = new JLabel("\uC811\uC18D\uC790 \uBA85\uB2E8");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setBackground(Color.YELLOW);
		lblNewLabel_1.setBounds(12, 120, 126, 32);
		contentPane.add(lblNewLabel_1);
		
		roomField1 = new JTextArea();
		roomField1.setBackground(UIManager.getColor("CheckBox.light"));
		roomField1.setBounds(234, 95, 214, 196);
		contentPane.add(roomField1);
		
		roomField2 = new JTextArea();
		roomField2.setBackground(UIManager.getColor("CheckBox.light"));
		roomField2.setBounds(520, 95, 214, 196);
		contentPane.add(roomField2);
		
		roomField3 = new JTextArea();
		roomField3.setBackground(UIManager.getColor("CheckBox.light"));
		roomField3.setBounds(234, 326, 214, 196);
		contentPane.add(roomField3);
		
		roomField4 = new JTextArea();
		roomField4.setBackground(UIManager.getColor("CheckBox.light"));
		roomField4.setBounds(520, 326, 214, 196);
		contentPane.add(roomField4);
		
		
			
		try {							// 연결
			//chatName 에 id 입력
			socket = new Socket("127.0.0.1", 7777);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			ClientThread ct = new ClientThread();
			list = new ArrayList<String>();
			pc = new protocolController();
			
			dos.writeUTF(this.id);
			
			ct.start();

			txtMsg.setText("");
			
			lblNewLabel_2 = new JLabel("");
//			lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Lee\\Desktop\\Colorful-Background_1.jpg"));
			lblNewLabel_2.setIcon(new ImageIcon("./Colorful-Background_1.jpg"));
			lblNewLabel_2.setBounds(0, 0, 772, 738);
			contentPane.add(lblNewLabel_2);
			
			
			
		} catch (IOException e3) {
		}
		
		
	}

	
	class ClientThread extends Thread{
		public void run() {
			while(true) {
				try {				//서버에서 오는 메시지 읽는 부분
					String msg = dis.readUTF();
					String getMsg = "";
					int num = pc.whichProtocol(msg);
					switch(num) {
					case 0 :
						String m = pc.getLogout(msg);
						textArea.append(m+"님이 나갔습니다.\n");
						break;
					case 1 :						// 1 == ID_MSG
						id = pc.getID_from_ID_MSG(msg);
						getMsg = pc.getMSG_from_ID_MSG(msg);
						textArea.append(id + " : " +getMsg + "\n");
						chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());
						break;
					case 2:						// 2 == LIST
						list = pc.getList_from_LIST(msg);
						int i = 0;							
						int size = list.size();
						
						listArea.setText("");
						for(i = 0; i<size; i++) {
							listArea.append(list.get(i) + "\n");
						}
						
						
						
						break;
					case 8 :
						String get = pc.getLogin(msg);
						if(!id.equals(get)) {
							textArea.append(get+"님이 입장하였습니다.\n");
						}
						break;
						
					}
					
					
					
					
					txtMsg.setText("");
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
