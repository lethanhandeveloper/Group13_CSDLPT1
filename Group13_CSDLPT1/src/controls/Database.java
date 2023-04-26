package controls;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import guis.LoginForm;

public class Database extends Thread {
	Connection con;
	PreparedStatement pst;
	Statement stt;
	ResultSet rs;
	String serverName;
	String dbLoginName;
	String dbPassword;
	LoginForm lgForm;
	CallableStatement cstm;
	
	
//	public Database(LoginForm lgForm, String serverName, String dbLoginName, String dbPassword) {
//		try {
//			con = DriverManager.getConnection(
//					"jdbc:sqlserver://LAPTOPHPOFAN\\"+serverName+";databaseName=NGANHANG;encrypt=false", dbLoginName, dbPassword);
//			stt = con.createStatement();
//			pst = con.prepareStatement(
//					"insert into dbo.KHACHHANG (CMND, HO, TEN, DIACHI, PHAI, NGAYCAP, SODT, MACN) values(?, ? , ?, ?, ?, ?, ?, ?)");
//			if (con != null) {
//				DatabaseMetaData dm = (DatabaseMetaData) con.getMetaData();
//				System.out.println("Tên Driver: " + dm.getDriverName());
//				System.out.println("Phiên bản Driver: " + dm.getDriverVersion());
//				System.out.println("Tên Cơ sở dữ liệu: " + dm.getDatabaseProductName());
//				System.out.println("Phiên bản Cơ sở dữ liệu: " + dm.getDatabaseProductVersion());
//				lgForm.showMessage("Đăng nhập thành công");
//			}
//		} catch (Exception e) {
//			lgForm.showMessage("Sai thông tin đăng nhập");
//		}
		
//	}
	
	public Database(String serverName, String dbLoginName, String dbPassword, LoginForm lgForm) {
		super();
		this.serverName = serverName;
		this.dbLoginName = dbLoginName;
		this.dbPassword = dbPassword;
	}

	
	
	public synchronized void connect() throws Exception{
		con = DriverManager.getConnection(
				"jdbc:sqlserver://LAPTOPHPOFAN\\"+serverName+";databaseName=NGANHANG;encrypt=false", dbLoginName, dbPassword);
		stt = con.createStatement();
		pst = con.prepareStatement(
				"insert into dbo.KHACHHANG (CMND, HO, TEN, DIACHI, PHAI, NGAYCAP, SODT, MACN) values(?, ? , ?, ?, ?, ?, ?, ?)");
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}
	
	public synchronized ResultSet getsearchUser(String number_account) {
		ResultSet rst=null;
		try {
			rst=stt.executeQuery("select * from user where account_number=\""+number_account+"\"");
		} catch (Exception e) {
			return rst;
		}
		return rst;
	}
	
	public synchronized int checkPersonalInfoExists(String cmnd, String sodienthoai) throws Exception{
		String sql = "{call sp_tblKhachHang_checkExists(?, ?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, cmnd);
		cstm.setString(2, sodienthoai);
		cstm.registerOutParameter(3, java.sql.Types.INTEGER);
		cstm.executeUpdate();
		
		if(cstm.getInt(3) == 1) {
			return 1;
		}
		
		return 0;
	}
	
	
	
	public synchronized ResultSet getPGDList(String macn) throws Exception{
		String sql = "{call sp_tblPhongGiaoDich_select(?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, macn);
		
		cstm.execute();
		return cstm.getResultSet();
		
	} 
	
	public synchronized void insertPersonalInfo(String cmnd,String ho,String ten,String diachi,String phai,Date ngaycap,String sodt,String macn, String mapgd) throws Exception {
		String sql = "{call sp_tblKhachHang_insert(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, cmnd);
		cstm.setString(2, ho);
		cstm.setString(3, ten);
		cstm.setString(4, diachi);
		cstm.setString(5, phai);
		cstm.setDate(6, ngaycap);
		cstm.setString(7, sodt);
		cstm.setString(8, macn);
		cstm.setString(9, mapgd);
		
		cstm.execute();
	}
	
	public synchronized ResultSet selectPersonalUserInfo(String macn, String pgd) throws Exception {
		String sql = "{call sp_tblKhachHang_select_all(?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, macn);
		cstm.setString(2, pgd);
		
		cstm.execute();
		
		return cstm.getResultSet();
		
	}
	
	public synchronized ResultSet selectStaffInfo(String manv, String macn) throws Exception{
		String sql = "{call sp_tblNhanVien_select_byid(?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, manv);
		cstm.setString(2, macn);
		
		cstm.execute();
		
		return cstm.getResultSet();
	}

	public synchronized ResultSet selectAccountInfo() throws Exception{
//		String sql = "{call sp_tblTaiKhoan_select_all(?, ?)}";
//		cstm = con.prepareCall(sql);
//		cstm.setString(1, macn);
//		cstm.setString(2, mapgd);
//		
//		cstm.execute();
//		
//		return cstm.getResultSet();
		PreparedStatement preparedStatement = con.prepareStatement("select * from TaiKhoan");
		return preparedStatement.executeQuery();
	}
	
	public synchronized ResultSet selectAccountInfoById(String sotk, String macn) throws Exception {
		String sql = "{call sp_tblTaiKhoan_select_bysotk(?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, sotk);
		cstm.setString(2, macn);
		
		cstm.execute();
		return cstm.getResultSet();

	}
	
	public synchronized ResultSet selectReceivePersonalInfoById(String cmnd, String macn) throws Exception {
		String sql = "{call sp_tblKhachHang_select_byid(?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, cmnd);
		cstm.setString(2, macn);
		
		cstm.execute();
		return cstm.getResultSet();

	}
	
	public synchronized void insertAccountInfo(String sotk, String cmnd, String macn) throws Exception{
		String sql = "{call sp_tblTaiKhoan_insert(?, ?, ?, ?)}";
		cstm = con.prepareCall(sql);
		cstm.setString(1, sotk);
		cstm.setString(2, cmnd);
		cstm.setInt(3, 0);
		cstm.setString(4, macn);
	
		cstm.execute();
	}
	
	public synchronized void transferMoney(String sotkchuyen, String sotknhan, int sotienchuyen, String manv) throws Exception{
		
		try {
			con.setAutoCommit(false);
			String sql = "update TaiKhoan set SODU = SODU - (?)  where SOTK = (?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sotienchuyen);
			pst.setString(2, sotkchuyen);
			pst.executeUpdate();
			
			sql = "update TaiKhoan set SODU = (?) + SODU where SOTK = (?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sotienchuyen);
			pst.setString(2, sotknhan);
			pst.executeUpdate();
			
			sql = "insert into GD_CHUYENTIEN(SOTK_CHUYEN, NGAYGD, SOTIEN, SOTK_NHAN, MANV) values(?, GETDATE(), ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, sotkchuyen);
			pst.setInt(2, sotienchuyen);
			pst.setString(3, sotknhan);
			pst.setString(4, manv);
			pst.executeUpdate();
			
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		}
	}
	
	public synchronized void depositMoney(String sotk, int sotiennap, String manv) throws Exception{
		try {
			con.setAutoCommit(false);
			
			String sql = "update TaiKhoan set SODU = (?) + SODU where SOTK = (?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sotiennap);
			pst.setString(2, sotk);
			pst.executeUpdate();
			
			sql = "insert into GD_GOIRUT(SOTK, LOAIGD, NGAYGD, SOTIEN, MANV) values(?, 'GT', GETDATE() , ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, sotk);
			pst.setInt(2, sotiennap);
			pst.setString(3, manv);
			pst.executeUpdate();
			
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		}
	}
	
	public synchronized void withdraw(String sotk, int sotienrut, String manv) throws Exception{
		try {
			con.setAutoCommit(false);
			
			String sql = "update TaiKhoan set SODU = SODU - (?) where SOTK = (?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, sotienrut);
			pst.setString(2, sotk);
			pst.executeUpdate();
			
			sql = "insert into GD_GOIRUT(SOTK, LOAIGD, NGAYGD, SOTIEN, MANV) values(?, 'RT', GETDATE() , ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, sotk);
			 BigDecimal moneyValue = new BigDecimal(sotienrut);
			pst.setBigDecimal(2, moneyValue);
			pst.setString(3, manv);
			pst.executeUpdate();
			
			
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		}
	}
}
