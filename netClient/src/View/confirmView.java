package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class confirmView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	public confirmView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(130, 161, 121, 43);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("\uD68C\uC6D0\uAC00\uC785\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
		textField.setBounds(12, 41, 353, 90);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
