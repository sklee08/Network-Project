package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Control.GUIController;
import Control.protocolController;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ScrollPaneConstants;

public class roomView extends JFrame {

	private JPanel contentPane;
	private JTextField bidText;
	private JTextField inputText;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private JTextArea listArea;
	private ArrayList<String> list;
	private protocolController pc;
	private JScrollPane chatScroll;
	private GUIController gc;
	private String id;
	private JLabel label_2;
	private JTextArea bidMoneyTextArea;
	private JLabel label_3;
	private JTextArea textArea;
	private JLabel imageLabel;
	private JButton uploadBtn;
	private JButton suggestBtn;
	private JButton startBtn;
	private JButton endBtn;
	private JLabel lblNewLabel_1;

	public roomView(String id, int roomNum) {

		gc = new GUIController();
		pc = new protocolController();
		this.id = id;

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				// 꺼질 때 서버한테 특정프로토콜로 자신의 아이디를 보낸다.
				String msg = pc.makeExit(roomNum);
				String m = pc.makeExitRoom(id);
				try {
					dos.writeUTF(m);
					dos.writeUTF(msg);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});

		setTitle(roomNum + " ROOM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 776, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		bidText = new JTextField();
		bidText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					// 엔터 입력시
				}
			}
		});
		bidText.setBounds(289, 299, 193, 29);
		contentPane.add(bidText);
		bidText.setColumns(10);

		JLabel label = new JLabel("\uACBD\uB9E4\uAE08\uC561 : ");
		label.setFont(new Font("굴림", Font.PLAIN, 13));
		label.setBounds(214, 299, 88, 29);
		contentPane.add(label);

		inputText = new JTextField();
		inputText.addKeyListener(new KeyAdapter() { // 채팅창 엔터 입력시
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					// 엔터 입력시
					try {
						if (dis != null) { // 클라이언트에서 서버로 전송
							String sendMsg = pc.make_ID_MSG(id, inputText.getText());
							dos.writeUTF(sendMsg);
							inputText.setText("");
						}
					} catch (Exception e1) {

					}
				}
			}
		});
		inputText.setBounds(59, 459, 659, 31);
		contentPane.add(inputText);
		inputText.setColumns(10);

		JLabel lblNewLabel = new JLabel("MSG : ");
		lblNewLabel.setBounds(10, 465, 57, 15);
		contentPane.add(lblNewLabel);

		listArea = new JTextArea();
		listArea.setBounds(0, 32, 103, 256);
		contentPane.add(listArea);

		JLabel label_1 = new JLabel("\uC811\uC18D\uC790 \uBA85\uB2E8");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setBackground(Color.YELLOW);
		label_1.setBounds(10, 10, 126, 32);
		contentPane.add(label_1);

		suggestBtn = new JButton("\uC81C\uC548\uD558\uAE30");
		suggestBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 제안하기 버튼 마우스 클릭시
				String s = bidText.getText();
				int money = Integer.parseInt(s);
				try {
					String msg = pc.makeBidMoney(id, money);
					dos.writeUTF(msg);
				} catch (Exception e1) {

				}
				bidText.setText("");
			}
		});
		suggestBtn.setBounds(493, 302, 97, 23);
		contentPane.add(suggestBtn);

		uploadBtn = new JButton("\uC0AC\uC9C4 \uC62C\uB9AC\uAE30");
		uploadBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 사진 올리기
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files only", ImageIO.getReaderFileSuffixes());
				jfc.setFileFilter(filter);

				int returnVal = jfc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();				
					String msg = selectedFile.getAbsolutePath();
					String ret = pc.makeFile(msg);
					try {
						dos.writeUTF(ret);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				} else if (returnVal == JFileChooser.ERROR_OPTION) {
					JOptionPane.showMessageDialog(null, "FAILURE: FILE UPLOAD REJECTED", "File Selector Failed",
							JOptionPane.WARNING_MESSAGE);
				}
			}

		});
		uploadBtn.setBounds(486, 6, 116, 23);
		contentPane.add(uploadBtn);

		label_2 = new JLabel("\uBB3C\uD488 \uC0AC\uC9C4");
		label_2.setVerticalAlignment(SwingConstants.TOP);
		label_2.setBackground(Color.YELLOW);
		label_2.setBounds(155, 10, 126, 23);
		contentPane.add(label_2);

		bidMoneyTextArea = new JTextArea();
		bidMoneyTextArea.setBounds(633, 133, 115, 29);
		contentPane.add(bidMoneyTextArea);
		bidMoneyTextArea.append("0");

		label_3 = new JLabel("\uD604\uC7AC \uACBD\uB9E4 \uAC00\uACA9");
		label_3.setBounds(644, 108, 104, 15);
		contentPane.add(label_3);

		chatScroll = new JScrollPane();
		chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		chatScroll.setBounds(12, 337, 736, 116);
		contentPane.add(chatScroll);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		chatScroll.setViewportView(textArea);
		textArea.append(this.id + "님이 입장하였습니다.\n");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBounds(148, 32, 465, 256);
		contentPane.add(panel);
		
		imageLabel = new JLabel("");
		panel.add(imageLabel);
		
		startBtn = new JButton("\uACBD\uB9E4 \uC2DC\uC791");
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 경매시작
				String msg = "";
				msg = pc.makeBidStart("start");
				try {
					dos.writeUTF(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		startBtn.setBounds(644, 6, 97, 23);
		contentPane.add(startBtn);
		
		endBtn = new JButton("\uACBD\uB9E4 \uC885\uB8CC");
		endBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 경매 종료
				String m = bidMoneyTextArea.getText();
				int money = Integer.parseInt(m);
				String msg = pc.makeBidEnd(id, money);
				try {
					dos.writeUTF(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		endBtn.setBounds(645, 52, 97, 23);
		contentPane.add(endBtn);
		
		
		try {
			socket = new Socket("127.0.0.1", roomNum + 9999);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			roomClientThread rct = new roomClientThread();
			list = new ArrayList<String>();

			dos.writeUTF(this.id);

			rct.start();
			inputText.setText("");
			
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("./$.jpg"));
			lblNewLabel_1.setBounds(0, 0, 778, 500);
			contentPane.add(lblNewLabel_1);
			
			
						

		} catch (Exception e) {

		}

	}

	class roomClientThread extends Thread {
		public void run() {
			while (true) {
				try { 				// 서버로부터 클라에서 메시지 읽기 부분
					String msg = dis.readUTF();
					String getMsg = "";
					int num = pc.whichProtocol(msg);
					switch (num) {
					case 0 :
						break;
					case 1:
						id = pc.getID_from_ID_MSG(msg);
						getMsg = pc.getMSG_from_ID_MSG(msg);
						textArea.append(id + " : " + getMsg + "\n");
						chatScroll.getVerticalScrollBar().setValue(chatScroll.getVerticalScrollBar().getMaximum());
						break;
					case 2:
						list = pc.getList_from_LIST(msg);
						int i = 0;
						int size = list.size();

						listArea.setText("");
						for (i = 0; i < size; i++) {
							listArea.append(list.get(i) + "\n");

						}
												
						if(!list.get(0).equals(id)) {
							// 방장이 아님
							uploadBtn.setVisible(false);
							startBtn.setVisible(false);
							endBtn.setVisible(false);
							
						}
						else {
							// 방장임
							suggestBtn.setVisible(false);
						}
						break;
					case 3 :
						break;
					case 4 :
						break;
					case 5 :
						String fileName = pc.getFileProto(msg);
						imageLabel.setIcon(new ImageIcon(fileName));
						break;
					case 6 :
						int money = pc.getBidMoney(msg);
						String c = pc.getBidId(msg);
						
						int originMoney = Integer.parseInt(bidMoneyTextArea.getText());
						if(money > originMoney) {
							// 다음 비드 한 금액이 더 클 경우
							String s = String.valueOf(money);
							bidMoneyTextArea.setText("");
							bidMoneyTextArea.append(s);
							textArea.append(c+"님이 "+s+"원을 제시했습니다.\n");
							// 시간 초기화
						}
						else {
							// 다음 비드 한 금액이 더 작은 경우
							// 에러창 띄우기
						}
						break;
					case 7 :
						textArea.append("######### 경매가 시작되었습니다! #########\n");
						break;
					case 8 :
						String get = pc.getLogin(msg);
						if(!id.equals(get)) {
							textArea.append(get+"님이 입장하였습니다.\n");
						}
						break;
					case 9 :
						String b = pc.getExitRoom(msg);
						textArea.append(b+"님이 나갔습니다.\n");
						break;
					case 10 :
						String d = pc.getBidEnd(msg);
						int mon = pc.getBidEndMoney(msg);
						textArea.append("######### 경매가 끝났습니다! #########\n");
						textArea.append("낙찰자는 "+d+"이며, 낙찰 가격은 "+mon+"입니다.\n");
						suggestBtn.setVisible(false);
						endBtn.setVisible(false);
						startBtn.setVisible(false);
						uploadBtn.setVisible(false);
						break;
					}

					inputText.setText("");
				} catch (Exception e4) {

				}
			}
		}
	}
}
