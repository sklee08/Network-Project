package View;

import Control.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class signUpView extends JFrame {

	private JPanel contentPane;
	private JTextField textSignID;
	private JTextField textSignPw;
	private JTextField textSignName;


	public signUpView() {
		setTitle("SIGNUP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		lblNewLabel.setBounds(88, 114, 40, 37);
		contentPane.add(lblNewLabel);
		
		textSignID = new JTextField();
		textSignID.setBounds(199, 114, 151, 33);
		contentPane.add(textSignID);
		textSignID.setColumns(10);
		
		textSignPw = new JTextField();
		textSignPw.setColumns(10);
		textSignPw.setBounds(199, 213, 151, 33);
		contentPane.add(textSignPw);
		
		JButton button = new JButton("\uD68C\uC6D0\uAC00\uC785");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = textSignID.getText();
				String pw = textSignPw.getText();
				String name = textSignName.getText();
				
				DBController dc = new DBController();
				try {
					dc.initConnect();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				boolean isLogin = new LoginController(dc).signUp(id, pw, name);
				if(isLogin) {						// ·Î±×ÀÎ µÊ
					setVisible(false);
				}
				else {								// ·Î±×ÀÎ X
					new GUIController().popSignUpError();
				}
				
				
			}
		});
		button.setFont(new Font("±¼¸²", Font.PLAIN, 16));
		button.setBounds(150, 611, 123, 37);
		contentPane.add(button);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		lblPassword.setBounds(53, 209, 134, 37);
		contentPane.add(lblPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("±¼¸²", Font.PLAIN, 24));
		lblName.setBounds(74, 317, 113, 37);
		contentPane.add(lblName);
		
		textSignName = new JTextField();
		textSignName.setColumns(10);
		textSignName.setBounds(199, 321, 151, 33);
		contentPane.add(textSignName);
	}

}
