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
				// ���� �� �������� Ư���������ݷ� �ڽ��� ���̵� ������.
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
					// ���� �Է½�
				}
			}
		});
		bidText.setBounds(289, 299, 193, 29);
		contentPane.add(bidText);
		bidText.setColumns(10);

		JLabel label = new JLabel("\uACBD\uB9E4\uAE08\uC561 : ");
		label.setFont(new Font("����", Font.PLAIN, 13));
		label.setBounds(214, 299, 88, 29);
		contentPane.add(label);

		inputText = new JTextField();
		inputText.addKeyListener(new KeyAdapter() { // ä��â ���� �Է½�
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER) {
					// ���� �Է½�
					try {
						if (dis != null) { // Ŭ���̾�Ʈ���� ������ ����
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
				// �����ϱ� ��ư ���콺 Ŭ����
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
				// ���� �ø���
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
		textArea.append(this.id + "���� �����Ͽ����ϴ�.\n");
		
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
				// ��Ž���
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
				// ��� ����
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
				try { 				// �����κ��� Ŭ�󿡼� �޽��� �б� �κ�
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
							// ������ �ƴ�
							uploadBtn.setVisible(false);
							startBtn.setVisible(false);
							endBtn.setVisible(false);
							
						}
						else {
							// ������
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
							// ���� ��� �� �ݾ��� �� Ŭ ���
							String s = String.valueOf(money);
							bidMoneyTextArea.setText("");
							bidMoneyTextArea.append(s);
							textArea.append(c+"���� "+s+"���� �����߽��ϴ�.\n");
							// �ð� �ʱ�ȭ
						}
						else {
							// ���� ��� �� �ݾ��� �� ���� ���
							// ����â ����
						}
						break;
					case 7 :
						textArea.append("######### ��Ű� ���۵Ǿ����ϴ�! #########\n");
						break;
					case 8 :
						String get = pc.getLogin(msg);
						if(!id.equals(get)) {
							textArea.append(get+"���� �����Ͽ����ϴ�.\n");
						}
						break;
					case 9 :
						String b = pc.getExitRoom(msg);
						textArea.append(b+"���� �������ϴ�.\n");
						break;
					case 10 :
						String d = pc.getBidEnd(msg);
						int mon = pc.getBidEndMoney(msg);
						textArea.append("######### ��Ű� �������ϴ�! #########\n");
						textArea.append("�����ڴ� "+d+"�̸�, ���� ������ "+mon+"�Դϴ�.\n");
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
