package karaokeSys.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import karaokeSys.Actions.loginValidator;

public class Login {

	private JFrame frmLogin;
	private JTextField textUsername;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 316, 199);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(48, 34, 72, 18);
		frmLogin.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(48, 64, 72, 18);
		frmLogin.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(127, 34, 118, 24);
		frmLogin.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(127, 61, 118, 24);
		frmLogin.getContentPane().add(textPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String password = textPassword.getText();
				loginValidator lV = new loginValidator();
				try {
					if(lV.validate(username, password)==true){
						mainFrame mF = new mainFrame();
						mF.main(null);
						frmLogin.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "Invalid Username/Password", " Error ", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid Username/Password", " Error ", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(147, 90, 103, 27);
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFrame rF = new registerFrame();
				rF.main(null);
			}
		});
		btnRegister.setBounds(48, 90, 97, 27);
		frmLogin.getContentPane().add(btnRegister);
	}
}
