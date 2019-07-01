package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnterRoomErrorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdPassword;

	
	public EnterRoomErrorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtIdPassword = new JTextField();
		txtIdPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdPassword.setText("\uD574\uB2F9 \uBC29 \uBC88\uD638\uB294 \uC774\uBBF8 \uC874\uC7AC\uD569\uB2C8\uB2E4.");
		txtIdPassword.setBounds(45, 64, 343, 87);
		contentPane.add(txtIdPassword);
		txtIdPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(166, 192, 97, 23);
		contentPane.add(btnNewButton);
	}

}
