package thread;

import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;

import controls.Database;
import guis.MainForm;
import models.PhongGiaoDich;

public class PGDReloadThread extends Thread {
	MainForm mainform;
	
	
	public PGDReloadThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		mainform.phonggiaodichList.clear();

		try {
			ResultSet rst = mainform.database.getPGDList(mainform.currentBranch);
			while(rst.next()) {
				String mapgd = rst.getString("MAPGD");
				String tencn = rst.getString("TENPGD");
				PhongGiaoDich pgd = new PhongGiaoDich(mapgd, tencn);
				mainform.phonggiaodichList.add(pgd);
			}
			
			String[] pgdNameArr = new String[mainform.phonggiaodichList.size()]; 
			
			for(int i=0; i< mainform.phonggiaodichList.size(); i++) {
				pgdNameArr[i] = mainform.phonggiaodichList.get(i).getTenpgd();
			}
			
			
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(pgdNameArr);
			
			mainform.cbbPGD_PersonalInfo.setModel(model);
			
			mainform.currentPGD = mainform.phonggiaodichList.get(mainform.cbbPGD_PersonalInfo.getSelectedIndex()).getMapgd();
			
			stop();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			mainform.showMessage("Có lỗi xảy ra");
			stop();
		}
	}
}
