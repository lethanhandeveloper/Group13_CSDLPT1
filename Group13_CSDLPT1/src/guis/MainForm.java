package guis;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controls.Database;
import models.PhongGiaoDich;
import thread.AccountInfoTableReloadThread;
import thread.DepositAndWithdrawThread;
import thread.GetStaffInfoThread;
import thread.PGDReloadThread;
import thread.PersonalInfoTableReloadThread;
import thread.SelectReceiveTaiKhoanByIdThread;
import thread.SelectTaiKhoanByIdForDepositThread;
import thread.SelectTaiKhoanByIdThread;
import thread.TransferMoneyThread;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
/*dùng update chứ ko dùng edit

khachhang:edit, delete, select -> select by id, tblKhachHang_select_all -> tblTaiKhoan_select_all, tblKhachHang_select -> tblKhachHang_select_all
tblKhachHang_select_byid
nhanvien : select -> select_byid
taikhoan: tblTaiKhoan_update, tblTaiKhoan_insert,
tblTaiKhoan_delete, sp_tblTaiKhoan_select_bysotk
giaodichguirut:tbGDGoiRut_insert, tblGDGoiRut_select
giaodichgoirut: tbGDGoiRut_insert, tblGDGoiRut_select
giaodichchuyentien: tbGDGoiRut_insert, tblGDGoiRut_select

ngày giao dịch -> thời gian giao dịch

 * 
 * 
 * 
 * 
 * 
 * 
 */
public class MainForm extends JFrame {
	
	public Database database;
	private JPanel contentPane;

//	String personalInfoRows[][] = {
//            { "Kundan Kumar Jha", "4031", "CSE", "4031", "4031", "4031", "4031" }
//        };
//	String personalInfoColumns[] = {"CMND", "Họ", "Tên", "Địa chỉ", "Giới tính", "Ngày cấp", "Số điện thoại"};
	//table personalInfo
	public Vector personalInfoColumn = new Vector();
	public Vector personalInfoRow = new Vector();
	public DefaultTableModel personalInfoDefaultTable;
	public int selectedPersonalInfoRow;
	
	//table account info
	public Vector accountInfoColumn = new Vector();
	public Vector accountInfoRow = new Vector();
	public DefaultTableModel accountInfoDefaultTable;
	
	
	public String currentBranch;
	public String currentPGD;
	public String currentMaNV;
	
	String chinhanh[] = {"Hà Nội", "Đà Nẵng", "Hồ Chí Minh"};
	private JTable tblPersonalInfo;
	public JTextField txtSoTK;
	public JTextField txtToAccountNumber;
	public JComboBox cbbCN_personalInfo;
	public JComboBox cbbPGD_PersonalInfo;
	
	public JLabel txtCurrentBalance;
	public JLabel txtAccountName;
	public JLabel txtAccountNumber;
	public JButton btnNext;
	
	public JLabel lblCurrentStaffName;
	public JLabel lblCurrenRole;
	public JLabel lblCurrentPGD;
	public JLabel lblcurrentBranch;
	public JLabel lblToName;
	public JLabel lblNameAccount_Deposit;
	public JLabel lblCurrentBalance_Deposit;
	public JComboBox cbbTypeTrans;
	public JButton btnDepositNext;
	public JLabel lblAccount_Deposit;
	public JButton btnCheckAccount_Deposit;
	
	public JButton btnRefreshData;

	public ArrayList<PhongGiaoDich> phonggiaodichList = new ArrayList<PhongGiaoDich>();
	public JTable tblAccountInfo;
	public JTextField txtToBalance;
	
	public int currentBalanceforTransfer = 0;
	public JTextField txtAccount_Deposit;
	public JTextField txtDepositBalance;
	/**
	 * Create the frame.
	 */
	public MainForm(Database database, String currentBranch, String currentMANV) {
		this.database = database;
		this.currentBranch = currentBranch;
		this.currentMaNV = currentMANV;
		
		setFont(new Font("Dialog", Font.PLAIN, 6));
		setTitle("Hệ thống quản lý tài khoản và giao dịch Agribank");
		setBounds(100, 100, 1060, 691);
		contentPane = new JPanel();
		contentPane.setForeground(Color.GREEN);
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 48, 1026, 557);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.info);
		tabbedPane.addTab("Tài khoản hệ thống", null, panel, null);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("   Thêm");
		btnNewButton.setIcon(new ImageIcon(MainForm.class.getResource("/icon/plus.png")));
		btnNewButton.setBounds(155, 451, 137, 32);
		panel.add(btnNewButton);
		
		JButton btnChnhSa = new JButton("   Chỉnh sửa");
		btnChnhSa.setIcon(new ImageIcon(MainForm.class.getResource("/icon/edit.png")));
		btnChnhSa.setBounds(302, 451, 137, 32);
		panel.add(btnChnhSa);
		
		JButton btnXa = new JButton("   Xóa");
		btnXa.setIcon(new ImageIcon(MainForm.class.getResource("/icon/delete.png")));
		btnXa.setBounds(449, 451, 137, 32);
		panel.add(btnXa);
		

		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.control);
		panel_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.addTab("Thông tin khách hàng", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reloadAccountInfoTable();
			}
		});
		tabbedPane_2.setBounds(10, 10, 1001, 510);
		panel_1.add(tabbedPane_2);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(SystemColor.info);
		tabbedPane_2.addTab("Cá nhân", null, panel_7, null);
		panel_7.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Thêm");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openSignUpPersonalInfoForm();
			}

			
		});
		btnNewButton_2.setIcon(new ImageIcon(MainForm.class.getResource("/icon/plus.png")));
		btnNewButton_2.setBounds(711, 446, 85, 28);
		panel_7.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 976, 426);
		panel_7.add(scrollPane);
		
		
		//add personal info title
		personalInfoColumn.add("CMND");
		personalInfoColumn.add("Họ");
		personalInfoColumn.add("Tên");
		personalInfoColumn.add("Địa Chỉ");
		personalInfoColumn.add("Giới Tính");
		personalInfoColumn.add("Ngày Cấp");
		personalInfoColumn.add("Số điện thoại");
		
		
		personalInfoDefaultTable = new DefaultTableModel(personalInfoRow, personalInfoColumn);
		
		tblPersonalInfo = new JTable(personalInfoDefaultTable);
		tblPersonalInfo.getTableHeader().setPreferredSize(new Dimension(20,30));
		tblPersonalInfo.setRowHeight(30);
		scrollPane.setViewportView(tblPersonalInfo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(SystemColor.info);
		tabbedPane_2.addTab("Tài khoản", null, panel_6, null);
		tblPersonalInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnNewButton_2_1 = new JButton("Sửa");
		btnNewButton_2_1.setIcon(new ImageIcon(MainForm.class.getResource("/icon/edit.png")));
		btnNewButton_2_1.setBounds(806, 446, 85, 28);
		panel_7.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Xóa");
		btnNewButton_2_1_1.setIcon(new ImageIcon(MainForm.class.getResource("/icon/delete.png")));
		btnNewButton_2_1_1.setBounds(901, 446, 85, 28);
		panel_7.add(btnNewButton_2_1_1);
		
		JButton btnLmMi = new JButton("Làm mới");
		btnLmMi.setIcon(new ImageIcon(MainForm.class.getResource("/icon/refresh.png")));
		btnLmMi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadPersonalInfoTable();
			}
		});
		btnLmMi.setBounds(601, 446, 100, 27);
		panel_7.add(btnLmMi);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 10, 976, 421);
		panel_6.add(scrollPane2);
		
		
		//add account info title
		accountInfoColumn.add("Số CMND");
		accountInfoColumn.add("Số tài khoản");
		accountInfoColumn.add("Số dư");
		
		accountInfoDefaultTable = new DefaultTableModel(accountInfoRow, accountInfoColumn);
		
		tblAccountInfo = new JTable(accountInfoDefaultTable);
		tblAccountInfo.getTableHeader().setPreferredSize(new Dimension(20,30));
		tblAccountInfo.setRowHeight(30);
		scrollPane2.setViewportView(tblAccountInfo);
		
		JButton btnNewButton_2_1_1_1 = new JButton("Xóa");
		btnNewButton_2_1_1_1.setIcon(new ImageIcon(MainForm.class.getResource("/icon/delete.png")));
		btnNewButton_2_1_1_1.setBounds(901, 441, 85, 28);
		panel_6.add(btnNewButton_2_1_1_1);
		
		JButton btnNewButton_2_1_2 = new JButton("Sửa");
		btnNewButton_2_1_2.setIcon(new ImageIcon(MainForm.class.getResource("/icon/edit.png")));
		btnNewButton_2_1_2.setBounds(806, 441, 85, 28);
		panel_6.add(btnNewButton_2_1_2);
		
		JButton btnNewButton_2_2 = new JButton("Thêm");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAccountInfoForm();
			}
		});
		btnNewButton_2_2.setIcon(new ImageIcon(MainForm.class.getResource("/icon/plus.png")));
		btnNewButton_2_2.setBounds(711, 441, 85, 28);
		panel_6.add(btnNewButton_2_2);
		
		btnRefreshData = new JButton("Làm mới");
		btnRefreshData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRefreshDataListener();
			}

			
		});
		btnRefreshData.setIcon(new ImageIcon(MainForm.class.getResource("/icon/refresh.png")));
		btnRefreshData.setBounds(579, 441, 122, 27);
		panel_6.add(btnRefreshData);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Nghiệp vụ", null, panel_2, null);
		panel_2.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBackground(SystemColor.info);
		tabbedPane_1.setBounds(10, 10, 1001, 463);
		panel_2.add(tabbedPane_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.info);
		tabbedPane_1.addTab("Chuyển tiền", null, panel_4, null);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Tài khoản");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(198, 8, 87, 29);
		panel_4.add(lblNewLabel_7);
		
		txtSoTK = new JTextField();
		txtSoTK.setBounds(293, 10, 273, 27);
		panel_4.add(txtSoTK);
		txtSoTK.setColumns(10);
		
		JButton btnCheckAccountInfo = new JButton("Kiểm tra");
		btnCheckAccountInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheckAccountInfoListener();
			}

			
		});
		btnCheckAccountInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckAccountInfo.setBounds(576, 8, 164, 29);
		panel_4.add(btnCheckAccountInfo);
		
		JLabel lblNewLabel_8_1 = new JLabel("Tên khách hàng");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1.setBounds(20, 183, 145, 28);
		panel_4.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_8 = new JLabel("Số tài khoản");
		lblNewLabel_8.setBounds(20, 145, 145, 28);
		panel_4.add(lblNewLabel_8);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Số dư hiện tại");
		lblNewLabel_8_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_1.setBounds(20, 233, 145, 28);
		panel_4.add(lblNewLabel_8_1_1);
		
		JLabel lblNewLabel_8_1_1_1 = new JLabel("Chuyển tiền đến");
		lblNewLabel_8_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_8_1_1_1.setBounds(378, 182, 124, 31);
		panel_4.add(lblNewLabel_8_1_1_1);
		
		txtToAccountNumber = new JTextField();
		txtToAccountNumber.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtToAccountNumberListener();
				
			}

			
		});
		txtToAccountNumber.setBounds(691, 141, 246, 27);
		panel_4.add(txtToAccountNumber);
		txtToAccountNumber.setColumns(10);
		
		btnNext = new JButton("Tiếp tục");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNextListener();
			}

			
		});
		btnNext.setEnabled(false);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNext.setBounds(836, 380, 112, 29);
		panel_4.add(btnNext);
		
		txtAccountNumber = new JLabel("");
		txtAccountNumber.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtAccountNumber.setBounds(128, 145, 218, 23);
		panel_4.add(txtAccountNumber);
		
		txtAccountName = new JLabel("");
		txtAccountName.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtAccountName.setBounds(141, 188, 205, 23);
		panel_4.add(txtAccountName);
		
		txtCurrentBalance = new JLabel("");
		txtCurrentBalance.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtCurrentBalance.setBounds(141, 233, 151, 23);
		panel_4.add(txtCurrentBalance);
		
		JLabel lblNewLabel_8_2 = new JLabel("Số tài khoản");
		lblNewLabel_8_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_2.setBounds(536, 145, 145, 28);
		panel_4.add(lblNewLabel_8_2);
		
		JLabel lblNewLabel_8_1_2 = new JLabel("Tên khách hàng");
		lblNewLabel_8_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_2.setBounds(536, 182, 145, 28);
		panel_4.add(lblNewLabel_8_1_2);
		
		JLabel lblNewLabel_8_1_1_2 = new JLabel("Số tiền");
		lblNewLabel_8_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_1_2.setBounds(536, 233, 145, 28);
		panel_4.add(lblNewLabel_8_1_1_2);
		
		txtToBalance = new JTextField();
		txtToBalance.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				txtToBalanceListener();
			}

			

			
		});
		
		
		
		
		txtToBalance.setColumns(10);
		txtToBalance.setBounds(691, 233, 246, 28);
		panel_4.add(txtToBalance);
		
		lblToName = new JLabel("");
		lblToName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblToName.setBounds(691, 183, 246, 28);
		panel_4.add(lblToName);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(SystemColor.info);
		panel_8.setForeground(SystemColor.info);
		tabbedPane_1.addTab("Rút tiền/Gửi tiền", null, panel_8, null);
		panel_8.setLayout(null);
		
		JLabel lblNewLabel_8_3 = new JLabel("Số tài khoản");
		lblNewLabel_8_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_3.setBounds(294, 110, 145, 28);
		panel_8.add(lblNewLabel_8_3);
		
		lblAccount_Deposit = new JLabel("");
		lblAccount_Deposit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAccount_Deposit.setBounds(402, 110, 218, 23);
		panel_8.add(lblAccount_Deposit);
		
		JLabel lblNewLabel_8_1_3 = new JLabel("Tên khách hàng");
		lblNewLabel_8_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_3.setBounds(294, 148, 145, 28);
		panel_8.add(lblNewLabel_8_1_3);
		
		lblNameAccount_Deposit = new JLabel("");
		lblNameAccount_Deposit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameAccount_Deposit.setBounds(415, 153, 205, 23);
		panel_8.add(lblNameAccount_Deposit);
		
		JLabel lblNewLabel_8_1_1_3 = new JLabel("Số dư hiện tại");
		lblNewLabel_8_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_1_3.setBounds(294, 198, 145, 28);
		panel_8.add(lblNewLabel_8_1_1_3);
		
		lblCurrentBalance_Deposit = new JLabel("");
		lblCurrentBalance_Deposit.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCurrentBalance_Deposit.setBounds(415, 198, 151, 23);
		panel_8.add(lblCurrentBalance_Deposit);
		
		txtAccount_Deposit = new JTextField();
		txtAccount_Deposit.setColumns(10);
		txtAccount_Deposit.setBounds(325, 12, 273, 27);
		panel_8.add(txtAccount_Deposit);
		
		JLabel lblNewLabel_7_1 = new JLabel("Tài khoản");
		lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7_1.setBounds(230, 10, 87, 29);
		panel_8.add(lblNewLabel_7_1);
		
		btnCheckAccount_Deposit = new JButton("Kiểm tra");
		btnCheckAccount_Deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCheckAccount_DepositListener();
			}

			
		});
		btnCheckAccount_Deposit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCheckAccount_Deposit.setBounds(608, 10, 164, 29);
		panel_8.add(btnCheckAccount_Deposit);
		
		JLabel lblNewLabel_8_1_1_3_1 = new JLabel("Nhập số tiền");
		lblNewLabel_8_1_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_1_3_1.setBounds(292, 256, 104, 28);
		panel_8.add(lblNewLabel_8_1_1_3_1);
		
		txtDepositBalance = new JTextField();
		txtDepositBalance.setBounds(401, 256, 326, 28);
		panel_8.add(txtDepositBalance);
		txtDepositBalance.setColumns(10);
		
		JLabel lblNewLabel_8_1_1_3_1_1 = new JLabel("Loại giao dịch");
		lblNewLabel_8_1_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_8_1_1_3_1_1.setBounds(290, 301, 104, 28);
		panel_8.add(lblNewLabel_8_1_1_3_1_1);
		
		String typetransarr[] = {"Nạp tiền", "Rút tiền"};
		cbbTypeTrans = new JComboBox(typetransarr);
		cbbTypeTrans.setBounds(402, 300, 325, 28);
		panel_8.add(cbbTypeTrans);
		
		btnDepositNext = new JButton("Tiếp tục");
		btnDepositNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDepositNextListener();
			}

			
		});
		btnDepositNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDepositNext.setBounds(563, 377, 164, 29);
		panel_8.add(btnDepositNext);
		
		JPanel panel_9 = new JPanel();
		tabbedPane_1.addTab("Gửi tiết kiệm", null, panel_9, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Báo cáo", null, panel_3, null);
		
		JButton btnLogOut = new JButton("Đăng xuất");
		
		
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleLogout();
			}

			
		});
		btnLogOut.setBounds(943, 613, 93, 31);
		contentPane.add(btnLogOut);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 613, 508, 31);
		contentPane.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblcurrentBranch = new JLabel("Chi nhánh");
		lblcurrentBranch.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblcurrentBranch);
		
		lblCurrentPGD = new JLabel("Phòng giao dịch");
		lblCurrentPGD.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCurrentPGD);
		
		lblCurrenRole = new JLabel("Vai trò");
		lblCurrenRole.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCurrenRole);
		
		lblCurrentStaffName = new JLabel("Tên nhân viên");
		lblCurrentStaffName.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_5.add(lblCurrentStaffName);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setForeground(Color.RED);
		progressBar.setBounds(541, 613, 349, 31);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Chi nhánh");
		lblNewLabel.setBounds(145, 9, 85, 28);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		cbbCN_personalInfo = new JComboBox(chinhanh);
		cbbCN_personalInfo.setBounds(240, 9, 224, 28);
		contentPane.add(cbbCN_personalInfo);
		
		JLabel lblPhngGiaoDch = new JLabel("Phòng giao dịch");
		lblPhngGiaoDch.setBounds(493, 9, 110, 28);
		contentPane.add(lblPhngGiaoDch);
		lblPhngGiaoDch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		cbbPGD_PersonalInfo = new JComboBox();
		cbbPGD_PersonalInfo.setBounds(610, 10, 224, 28);
		contentPane.add(cbbPGD_PersonalInfo);
		cbbPGD_PersonalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				reloadPersonalInfoTable();
				cbbPGD_PersonalInfoListener();
			}

			

			
		});
		cbbCN_personalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbCN_personalInfoListener();
				
//				updateComboBoxPGD();
//				updatePersonalInfoData();
//				cbbCN_personalInfoListener();
			}

			

			
		});
		
	
		
//		
		if(currentBranch == "HANOI") {
//			comboBox.setSelectedIndex(0);
			cbbCN_personalInfo.setSelectedIndex(0);
		}else if(currentBranch == "DANANG") {
//			comboBox.setSelectedIndex(1);
			cbbCN_personalInfo.setSelectedIndex(1);
		}else {
//			comboBox.setSelectedIndex(2);
			cbbCN_personalInfo.setSelectedIndex(2);
		}
		
		//update table
		try {
			GetStaffInfoThread getStaffInfoThread = new GetStaffInfoThread(this);
			getStaffInfoThread.start();
			getStaffInfoThread.join();
			
			PGDReloadThread pgdReloadThread = new PGDReloadThread(this);
			pgdReloadThread.start();
		
			reloadPersonalInfoTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	private void reloadAccountInfoTable() {
		try {
			AccountInfoTableReloadThread accountInfoTableReloadThread = new AccountInfoTableReloadThread(this, database);
			accountInfoTableReloadThread.start();
		} catch (Exception e) {
			e.printStackTrace();
			showMessage("Có lỗi xảy ra");
			
		}
		
		
	}


	private void openSignUpPersonalInfoForm() {
		new SignUpPersonalInfoForm(this).setVisible(true);
		
	}
	
	private void openAccountInfoForm() {
		new AccountInfoForm(this).setVisible(true);
		
	}
	
	private void reloadPersonalInfoTable() {
		try {
			PersonalInfoTableReloadThread personalInfoTableReloadThread =  new PersonalInfoTableReloadThread(this);
			personalInfoTableReloadThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnRefreshDataListener() {
		new AccountInfoTableReloadThread(this, database).start();
		
	}
	
	private void cbbCN_personalInfoListener() {
		try {
			if(cbbCN_personalInfo.getSelectedIndex() == 0) {
				currentBranch = "HANOI";
			}else if(cbbCN_personalInfo.getSelectedIndex() == 1) {
				currentBranch = "DANANG";
			}else {
				currentBranch = "HCM";
			}
			
			PGDReloadThread pgdReloadThread = new PGDReloadThread(this);
			pgdReloadThread.start();
			
			
//			
//			PersonalInfoTableReloadThread personalInfoTableReloadThread = new PersonalInfoTableReloadThread(this);
//			personalInfoTableReloadThread.start();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		new PersonalInfoTableReloadThread(this).start();
		
	}
	
	private void cbbPGD_PersonalInfoListener() {
		currentPGD = phonggiaodichList.get(cbbPGD_PersonalInfo.getSelectedIndex()).getMapgd();
		
	}
	
	private void btnCheckAccountInfoListener() {
		new SelectTaiKhoanByIdThread(this).start();
		
	}
	
	private void insertPersonalInfo() {
		
	}
	
	private void handleLogout() {
		new LoginForm().setVisible(true);;
		dispose();
		
	}
	
	private void btnNextListener() {
		int confirm  = JOptionPane.showConfirmDialog(this, "Xác nhận chuyển "+txtToBalance.getText().toString()+
				" từ "+txtAccountNumber.getText().toString()+ " đến "+txtToAccountNumber.getText().toString());
		if(confirm == 0) {
			new TransferMoneyThread(this).start();
		}
	}
	
	public void txtToAccountNumberListener() {
		new SelectReceiveTaiKhoanByIdThread(this).start();
		
	}
	
	private void txtToBalanceListener() {
		if(!checkBalance()) {
			showMessage("Số tiền chuyển không đủ hoặc không hợp lệ");
			return;
		}
		
		btnNext.setEnabled(true);
	}
	
	public boolean checkBalance() {
		try {
			int sotienchuyen = Integer.parseInt(txtToBalance.getText().toString());
			
			if(sotienchuyen == 0 || currentBalanceforTransfer - sotienchuyen < 0) {
				return false;
			}

			return true;
		} catch (Exception e) {
			return false;
			
		}
		
	}
	
	private void btnDepositNextListener() {
		int confirm  = JOptionPane.showConfirmDialog(this, "Xác nhận");
		if(confirm == 0) {
			new DepositAndWithdrawThread(this).start();
		}
		
	}
	
	private void btnCheckAccount_DepositListener() {
		System.out.println("voopp");
		new SelectTaiKhoanByIdForDepositThread(this).start();
		
	}
	
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
