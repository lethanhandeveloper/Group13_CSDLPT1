package guis;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import models.KhachHang;
import models.PhongGiaoDich;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpPersonalInfoForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtCMND;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtDIACHI;
	private JTextField txtSDT;
	private JDateChooser jDateChooser;
	private JComboBox cbbGT;
	private JComboBox cbbCN;
	private JComboBox cbbPGD;
	
	ArrayList<PhongGiaoDich> phonggiaodichList =  new ArrayList<PhongGiaoDich>();
	
	String chinhanh[] = {"Hà Nội", "Đà Nẵng", "Hồ Chí Minh"};
	String phai[] = {"Nam", "Nữ"};
	MainForm mainform;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SignUpPersonalInfoForm(MainForm mainform) {
		this.mainform = mainform;
		setBounds(100, 100, 489, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đăng ký khách hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(140, 6, 212, 51);
		contentPane.add(lblNewLabel);
		
		txtCMND = new JTextField();
		txtCMND.setBounds(10, 152, 212, 28);
		contentPane.add(txtCMND);
		txtCMND.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CMND");
		lblNewLabel_1.setBounds(10, 128, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		txtHo = new JTextField();
		txtHo.setColumns(10);
		txtHo.setBounds(10, 214, 212, 28);
		contentPane.add(txtHo);
		
		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(244, 214, 212, 28);
		contentPane.add(txtTen);
		
		txtDIACHI = new JTextField();
		txtDIACHI.setColumns(10);
		txtDIACHI.setBounds(10, 331, 446, 73);
		contentPane.add(txtDIACHI);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ");
		lblNewLabel_1_1.setBounds(10, 190, 45, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Tên");
		lblNewLabel_1_1_1.setBounds(244, 190, 212, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Địa chỉ");
		lblNewLabel_1_1_1_1.setBounds(10, 305, 45, 13);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		cbbGT = new JComboBox(phai);
		cbbGT.setBounds(10, 271, 212, 28);
		
		contentPane.add(cbbGT);
		
		
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(244, 271, 212, 28);
		contentPane.add(txtSDT);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1_1_1.setBounds(244, 252, 90, 13);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Chi nhánh");
		lblNewLabel_1_1_1_1_1_1.setBounds(8, 58, 90, 22);
		
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		cbbCN = new JComboBox(chinhanh);
		cbbCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatePhongGiaoDichList();
			}

			
		});
		cbbCN.setBounds(10, 90, 212, 28);
		
		contentPane.add(cbbCN);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Giới tính");
		lblNewLabel_1_1_1_1_2.setBounds(10, 256, 212, 13);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("Ngày cấp");
		lblNewLabel_1_1_1_1_2_1.setBounds(243, 136, 213, 13);
		contentPane.add(lblNewLabel_1_1_1_1_2_1);
		
		JButton btnSignUp = new JButton("Đăng ký");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerCustomer();
				
			}

			

			
		});
		
		
		btnSignUp.setBounds(10, 428, 446, 38);
		contentPane.add(btnSignUp);
		
		
		jDateChooser= new JDateChooser();
		jDateChooser.setBounds(243, 152, 213, 28);
		
		contentPane.add(jDateChooser);
		
		cbbPGD = new JComboBox(new Object[]{});
		cbbPGD.setBounds(244, 90, 212, 28);
		contentPane.add(cbbPGD);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Phòng giao dịch");
		lblNewLabel_1_1_1_1_1_1_1.setBounds(242, 58, 90, 22);
		contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
		
		updatePhongGiaoDichList();
	}
	
	private void updatePhongGiaoDichList() {
		phonggiaodichList.clear();
		
		
		String macn;
		if(cbbCN.getSelectedIndex() == 0) {
			macn = "HaNoi";
		}else if(cbbCN.getSelectedIndex() == 1) {
			macn = "DaNang";
		}else {
			macn = "HCM";
		}
		
		try {
			ResultSet rst = mainform.database.getPGDList(macn);
			
			while (rst.next()){  
				
				String mapgd = rst.getString("MAPGD");
				String tencn = rst.getString("TENPGD");
				PhongGiaoDich pgd = new PhongGiaoDich(mapgd, tencn);
				phonggiaodichList.add(pgd);
				String[] pgdNameArr = new String[phonggiaodichList.size()]; 
				
				for(int i=0; i< phonggiaodichList.size(); i++) {
					pgdNameArr[i] = phonggiaodichList.get(i).getTenpgd();
					System.out.println(phonggiaodichList.get(i));
				}
				
				
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(pgdNameArr);
				
				cbbPGD.setModel(model);
				
            }
			
		} catch (Exception e) {
			this.showMessage("Có lỗi xảy ra");
			e.printStackTrace();
		}
		
	}
	
	private void registerCustomer() {
		String cmnd = txtCMND.getText().toString();
		String sodt = txtSDT.getText().toString();
		
		try {
			if(mainform.database.checkPersonalInfoExists(cmnd, sodt) == 1) {
				showMessage("Thông tin khách hàng đã tồn tại");
				dispose();
				return;
			}
		} catch (Exception e) {
			showMessage("Có lỗi xảy ra");
			return;
		}
		
		String ho = txtHo.getText().toString();
		String ten = txtTen.getText().toString();
		
		
		String diachi = txtDIACHI.getText().toString();
		
		String phai = "Nam";
		if(cbbCN.getSelectedIndex() == 1) {
			phai = "Nữ";
		}
		
		Date selectedDate = jDateChooser.getDate();
		java.sql.Date ngaycap = new java.sql.Date(selectedDate.getTime());
		
		String macn;
		
		if(cbbCN.getSelectedIndex() == 0) {
			macn = "HANOI";
		}else if(cbbCN.getSelectedIndex() == 1) {
			macn = "DANANG";
		}else {
			macn = "HCM";
		}
		
		int pgdIndex = cbbPGD.getSelectedIndex();
		PhongGiaoDich pgd = phonggiaodichList.get(pgdIndex);
		
		String mapgd = pgd.getMapgd();
		
		try {
			mainform.database.insertPersonalInfo(cmnd, ho, ten, diachi, phai, ngaycap, sodt, macn, mapgd);
			showMessage("Đã cập nhật khách hàng");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			showMessage("Có lỗi xảy ra");
			dispose();
		}
		
		
		dispose();
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
