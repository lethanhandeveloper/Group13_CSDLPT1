package guis;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controls.Database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/*	3 nhom : ngan hang(1), chi nhanh(2), pgd(3)
 * 	hanoi :  nganhang1, chinhanh1, pgd1
 * 	danang : chinhanh2, pgd2
 * 	hcm : chinhanh3, pgd3
 * 
 * 
 * 
 * 
 * 
 */

public class LoginForm extends JFrame {
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private String serverDesArr[] = { "Hà Nội", "Đà Nẵng", "TP Hồ Chí Minh" };
	JComboBox comboBox;
	
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		try {
	        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	            if ("Nimbus".equals(info.getName())) {
	                javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                break;
	            }
	        }
	    } catch (ClassNotFoundException ex) {
	        
	    } catch (InstantiationException ex) {

	    } catch (IllegalAccessException ex) {
	            
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {

	    }
		
	    
	    
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setTitle("Đăng Nhập");
		setBounds(100, 100, 467, 410);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(115, 10, 247, 55);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox(serverDesArr);
		comboBox.setBounds(27, 119, 392, 28);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Chi nhánh");
		lblNewLabel_2.setBounds(27, 92, 82, 17);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tên đăng nhập");
		lblNewLabel_2_2.setBounds(27, 167, 82, 17);
		contentPane.add(lblNewLabel_2_2);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(27, 194, 392, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Mật khẩu");
		lblNewLabel_2_2_1.setBounds(27, 232, 82, 17);
		contentPane.add(lblNewLabel_2_2_1);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLogin();
			}
		});
		btnLogin.setBounds(27, 307, 392, 40);
		contentPane.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPassword.setBounds(26, 259, 393, 28);
		contentPane.add(txtPassword);
		
	
	}

	public void handleLogin(){
		String serverName = "";
		String currentBranch;
		
		if(comboBox.getSelectedIndex() == 0) {
			serverName = "CSDLPTTEST_0";
			currentBranch = "HANOI";
		}else if(comboBox.getSelectedIndex() == 1) {
			serverName = "CSDLPTTEST1";
			currentBranch = "DANANG";
		}else {
			serverName = "CSDLPT2";
			currentBranch = "HCM";
		}
		
		String dbLoginName = txtUsername.getText().toString();
		String dbPassword = txtPassword.getText().toString();
		
		Database database = new Database(serverName, dbLoginName, dbPassword,this);
		try {
			database.connect();
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
			MainForm mainform = new MainForm(database, currentBranch, txtUsername.getText().toString());
			mainform.setVisible(true);
			dispose();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Sai thông tin đăng nhập");
		}
		

	}
	
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
