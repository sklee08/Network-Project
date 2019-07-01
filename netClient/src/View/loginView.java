package View;

import Control.*;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class loginView extends JFrame {

	private JPanel contentPane;
	private JTextField textID;
	private JPasswordField textPw;
	private LoginController lc = null;
	private DBController dc = null;
	
	
	public loginView() {
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 771);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GUIController().popSign();
//				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(63, 636, 196, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("±¼¸²", Font.PLAIN, 26));
		lblId.setBounds(303, 176, 57, 76);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("±¼¸²", Font.PLAIN, 26));
		lblPassword.setBounds(239, 316, 121, 76);
		contentPane.add(lblPassword);
		
		textID = new JTextField();
		textID.setBounds(391, 199, 218, 41);
		contentPane.add(textID);
		textID.setColumns(10);
		
		JButton button = new JButton("\uB85C\uADF8\uC778");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
				String id = textID.getText();
				String pw = textPw.getText();
				GUIController gc = new GUIController();
				
				try {
					dc= new DBController();
					lc = new LoginController(dc);
					if(lc.login(id, pw) == 0) { 				// ·Î±×ÀÎ ¼º°ø
						gc.popLobby(id);
						setVisible(false);
					}
					else if(lc.login(id, pw) == 1){
						gc.popLoginError();
					}
					else {
						gc.popDup();
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
		button.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		button.setBounds(278, 464, 196, 41);
		contentPane.add(button);
		
		textPw = new JPasswordField();
		textPw.setBounds(391, 339, 218, 41);
		contentPane.add(textPw);
		
		textPw.addActionListener(new PwHandler(textID, textPw));
		
		JLabel background = new JLabel("");
//		background.setIcon(new ImageIcon("C:\\Users\\Lee\\Desktop\\login-background-images-2.jpg"));
		background.setIcon(new ImageIcon("./login-background-images-2.jpg"));
		background.setBounds(0, 0, 770, 732);
		contentPane.add(background);
	}
	
	class PwHandler implements ActionListener{

		JTextField textID;
		JPasswordField textPw;
		
		public PwHandler(JTextField textID, JPasswordField textPw) {
			this.textID = textID;
			this.textPw = textPw;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String id = textID.getText();
			String pw = textPw.getText();
			GUIController gc = new GUIController();
			
			try {
				dc= new DBController();
				lc = new LoginController(dc);
				if(lc.login(id, pw) == 0) { 				// ·Î±×ÀÎ ¼º°ø
					gc.popLobby(id);
					setVisible(false);
				}
				else if(lc.login(id, pw) == 1){
					gc.popLoginError();
				}
				else {
					gc.popDup();
				}
			
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
