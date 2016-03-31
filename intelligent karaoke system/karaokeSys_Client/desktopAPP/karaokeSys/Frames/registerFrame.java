package karaokeSys.Frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JTextField;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import karaokeSys.Actions.registerAccount;


public class registerFrame {

	private JFrame frmRegister;
	private JTextField usernameText;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame window = new registerFrame();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public registerFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//passwordText
			}
		});
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 257, 199);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(28, 39, 88, 18);
		frmRegister.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(28, 70, 88, 18);
		frmRegister.getContentPane().add(lblPassword);
		
		usernameText = new JTextField();
		usernameText.setBounds(105, 36, 104, 24);
		frmRegister.getContentPane().add(usernameText);
		usernameText.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 70, 104, 24);
		frmRegister.getContentPane().add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerAccount rA = new registerAccount();
				try {
					rA.register(usernameText.getText(), passwordField.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRegister.setBounds(28, 102, 181, 27);
		frmRegister.getContentPane().add(btnRegister);
	}
}
