package thread;

import java.sql.ResultSet;

import guis.MainForm;

public class GetStaffInfoThread extends Thread {
	MainForm mainform;
	
	
	
	public GetStaffInfoThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			ResultSet rst = mainform.database.selectStaffInfo(mainform.currentMaNV, mainform.currentBranch);
			while(rst.next()) {
				mainform.currentPGD = rst.getString("MAPGD");
				mainform.lblCurrentStaffName.setText(mainform.lblCurrentStaffName.getText()+":"+rst.getString("TEN"));
			}
		
			stop();
		} catch (Exception e) {
			e.printStackTrace();
			stop();
		}
		
	}
}
