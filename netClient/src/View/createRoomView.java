package View;
import Model.*;


import Control.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class createRoomView extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextArea roomNameArea;
	private JTextArea roomNumArea;
	private RoomController rc;
	private DataOutputStream dos;
	private protocolController pc;
	private DBController dc;
	private GUIController gc;
	private String id;
	
	
	
	
	public createRoomView(String id,Socket socket) {
		
		rc = new RoomController();
		pc = new protocolController();
		dc = new DBController();
		gc = new GUIController();
		this.id = id;
		// 클라이언트 로서 룸 서버에 접근 
		
		setTitle("\uBC29\uB9CC\uB4E4\uAE30");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uBC29 \uC774\uB984 :");
		lblNewLabel.setBounds(49, 41, 76, 31);
		contentPane.add(lblNewLabel);
		
		roomNameArea = new JTextArea();
		roomNameArea.setBounds(118, 41, 224, 31);
		contentPane.add(roomNameArea);
		
		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638 :");
		label.setBounds(49, 100, 76, 31);
		contentPane.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 100, 224, 31);
		contentPane.add(passwordField);
		
		JButton button = new JButton("\uBC29\uB9CC\uB4E4\uAE30");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			// 방 만들기.
				String name = roomNameArea.getText();				// 방 id
				String pw = new String(passwordField.getPassword());	// 방 pw
				int num = Integer.parseInt(roomNumArea.getText());		// 방 번호
				try {
					dos = new DataOutputStream(socket.getOutputStream());
					String str = "";
					RoomModel rm = rc.findRoom(num);
					if(rm == null) {
						str = pc.makeRoomEnterProtocol(num);
						rm = new RoomModel(num,name,pw);
						rc.makeRoom(rm);
						dos.writeUTF(str);
						gc.popRoomView(id,num);
						setVisible(false);
						
					}
					else {
						// 찾음 -> 해당 방 번호는 이미 존재하는 방입니다 -> 오류 에러창 pop
					}
					// 프로토콜 3번 전송
					
					
					
				} catch (IOException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				
				
				
			}
		});
		button.setBounds(166, 207, 97, 23);
		contentPane.add(button);
		
		JLabel label_1 = new JLabel("\uBC29 \uBC88\uD638 :");
		label_1.setBounds(49, 153, 76, 31);
		contentPane.add(label_1);
		
		roomNumArea = new JTextArea();
		roomNumArea.setBounds(118, 153, 224, 31);
		contentPane.add(roomNumArea);
	}
}
