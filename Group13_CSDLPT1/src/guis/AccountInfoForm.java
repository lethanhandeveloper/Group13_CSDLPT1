package guis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controls.Database;
import thread.AddAccountInfoThread;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AccountInfoForm extends JFrame {

	private JPanel contentPane;
	public JTextField txtCMND;
	public JTextField txtSTK;
	public MainForm mainForm;
	public JComboBox cbbChiNhanh;
	
	public AccountInfoForm(MainForm mainForm) {
		this.mainForm =  mainForm;

		
		setBounds(100, 100, 486, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblThngTinTi = new JLabel("Thông tin tài khoản");
		lblThngTinTi.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThngTinTi.setBounds(177, 10, 212, 51);
		contentPane.add(lblThngTinTi);
		
		JLabel lblNewLabel = new JLabel("Số CMND");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 138, 80, 29);
		contentPane.add(lblNewLabel);
		
		txtCMND = new JTextField();
		txtCMND.setBounds(118, 135, 316, 32);
		contentPane.add(txtCMND);
		txtCMND.setColumns(10);
		
		txtSTK = new JTextField();
		txtSTK.setColumns(10);
		txtSTK.setBounds(118, 198, 316, 32);
		contentPane.add(txtSTK);
		
		JLabel lblSTiKhon = new JLabel("Số Tài Khoản");
		lblSTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSTiKhon.setBounds(10, 201, 98, 29);
		contentPane.add(lblSTiKhon);
		
		JButton btnSave = new JButton("Lưu lại");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveListener();
			}

			
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSave.setBounds(323, 240, 111, 32);
		contentPane.add(btnSave);
		
		JLabel lblChiNhanhs = new JLabel("Chi nhánh");
		lblChiNhanhs.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChiNhanhs.setBounds(10, 74, 80, 29);
		contentPane.add(lblChiNhanhs);
		
		cbbChiNhanh = new JComboBox(mainForm.chinhanh);
		cbbChiNhanh.setBounds(118, 71, 316, 30);
		contentPane.add(cbbChiNhanh);
	}
	
	private void btnSaveListener() {
		new AddAccountInfoThread(this.mainForm, this).start();
		
	}
}
