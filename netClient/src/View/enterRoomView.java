package View;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Control.GUIController;
import Control.RoomController;

public class enterRoomView extends JFrame {

	private JPanel contentPane;
	private JTextArea roomName;
	private JPasswordField pwf;
	private JTextArea roomNumArea;
	private RoomController rc;
	private GUIController gc;
	


	public enterRoomView(String id) {
		
		rc= new RoomController();
		gc = new GUIController();
		
		setTitle("\uC811\uC18D");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uBC29 \uC774\uB984 : ");
		lblNewLabel.setBounds(93, 60, 57, 15);
		contentPane.add(lblNewLabel);
		
		roomName = new JTextArea();
		roomName.setBounds(162, 56, 153, 24);
		contentPane.add(roomName);
		
		JLabel label = new JLabel("\uBC29 \uC811\uC18D\uD558\uAE30");
		label.setBounds(176, 10, 87, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 : ");
		label_1.setBounds(85, 113, 76, 15);
		contentPane.add(label_1);
		
		pwf = new JPasswordField();
		pwf.setBounds(162, 109, 153, 24);
		contentPane.add(pwf);
		
		JButton btnNewButton = new JButton("\uC811\uC18D");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 접속하기 
				String name = roomName.getText();
				String pw = new String(pwf.getPassword());
				int num = Integer.parseInt(roomNumArea.getText());
				try {
					if(rc.enterRoom(name, pw, num)) {
						// 접속
						// 연결
						gc.popRoomView(id, num);
						setVisible(false);
					}
					else {
						if(rc.findRoom(num) != null) {
							// 해당 번호의 방이 존재
							// 방이 이미 존재한다는 에러 창 팝
							gc.popRoomError();
						}
						else {
							//비밀번호 오류창 팝업
							gc.popLoginError();
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(162, 200, 97, 23);
		contentPane.add(btnNewButton);
		
		JLabel label_2 = new JLabel("\uBC29 \uBC88\uD638 : ");
		label_2.setBounds(93, 160, 57, 15);
		contentPane.add(label_2);
		
		roomNumArea = new JTextArea();
		roomNumArea.setBounds(162, 156, 153, 24);
		contentPane.add(roomNumArea);
	}
}
