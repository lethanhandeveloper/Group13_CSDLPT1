package thread;

import java.sql.ResultSet;
import java.util.Vector;

import controls.Database;
import guis.MainForm;

public class PersonalInfoTableReloadThread extends Thread {
	MainForm mainform;
	
	public PersonalInfoTableReloadThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}



	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		super.run();
		mainform.personalInfoRow.clear();
		
		try {
			ResultSet rst = mainform.database.selectPersonalUserInfo(mainform.currentBranch, mainform.currentPGD);
			while(rst.next()) {
				
				 Vector row = new Vector(7);
				 String cmnd = rst.getString("CMND");
				 String ho = rst.getString("HO");
				 String ten = rst.getString("TEN");
				 String diachi = rst.getString("DIACHI");
				 String phai = rst.getString("PHAI");
				 String ngaycap = rst.getString("NGAYCAP");
				 String sodt = rst.getString("SODT");
				 
				 System.out.println(mainform.currentBranch);
				 System.out.println(cmnd);
				 
				 row.add(cmnd);
				 row.add(ho);
				 row.add(ten);
				 row.add(diachi);
				 row.add(phai);
				 row.add(ngaycap);
				 row.add(sodt);
				 
				 
				 
				 mainform.personalInfoRow.add(row);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			mainform.showMessage("Có lỗi xảy ra");
			stop();
		}
		
		mainform.personalInfoDefaultTable.fireTableDataChanged();
		stop();
	}
}
