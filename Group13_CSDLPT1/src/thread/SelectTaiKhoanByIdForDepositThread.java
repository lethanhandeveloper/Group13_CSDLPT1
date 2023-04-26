package thread;

import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

import guis.MainForm;

public class SelectTaiKhoanByIdForDepositThread extends Thread {
	
	MainForm mainform;
	
	
	public SelectTaiKhoanByIdForDepositThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			System.out.println("VOOOOOOOO");
			ResultSet rst = mainform.database.selectAccountInfoById(mainform.txtAccount_Deposit.getText().toString(),
					mainform.currentBranch);
			
			
			while(rst.next()) {
				String sotk = rst.getString("SOTK");
				
				String cmnd = rst.getString("CMND");
				ResultSet rst1 = mainform.database.selectReceivePersonalInfoById(cmnd, mainform.currentBranch);
				
				while(rst1.next()) {
					String tenkh = rst1.getString("HO")+ " "+rst1.getString("TEN");
					System.out.println(tenkh);
					int sodu = rst.getInt("SODU");
					
					mainform.currentBalanceforTransfer = sodu;
					mainform.lblAccount_Deposit.setText(sotk);
					mainform.lblNameAccount_Deposit.setText(tenkh);
					
					Locale locale = new Locale("vi", "VN");
				    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
				    String tienDaTach = currencyFormatter.format(sodu);
					
					mainform.lblCurrentBalance_Deposit.setText(tienDaTach);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
