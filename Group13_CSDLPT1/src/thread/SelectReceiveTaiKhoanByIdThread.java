package thread;

import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

import guis.MainForm;

public class SelectReceiveTaiKhoanByIdThread extends Thread{
	MainForm mainform;
	
	
	
	public SelectReceiveTaiKhoanByIdThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			
			ResultSet rst = mainform.database.selectAccountInfoById(mainform.txtToAccountNumber.getText().toString(),
					mainform.currentBranch);
			
			
			while(rst.next()) {

				String cmnd = rst.getString("CMND");
				ResultSet rst1 = mainform.database.selectReceivePersonalInfoById(cmnd, mainform.currentBranch);
				
				while(rst1.next()) {
					mainform.lblToName.setText(rst1.getString("HO")+" "+rst1.getString("TEN"));
				}
			}
			
			stop();
		}
		catch (Exception e) {
			e.printStackTrace();
			stop();
		}
	}
}
