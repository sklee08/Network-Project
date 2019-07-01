package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class signUpErrorView extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;


	public signUpErrorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtId = new JTextField();
		txtId.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("\uC774\uBBF8 \uC874\uC7AC\uD558\uB294 ID \uC785\uB2C8\uB2E4.");
		txtId.setBounds(56, 68, 331, 93);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JButton button = new JButton("\uD655\uC778");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		button.setBounds(170, 202, 97, 23);
		contentPane.add(button);
	}

}
