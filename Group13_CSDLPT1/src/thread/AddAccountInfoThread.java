package thread;

import guis.AccountInfoForm;
import guis.MainForm;

public class AddAccountInfoThread extends Thread {
	MainForm mainForm;
	AccountInfoForm accountInfoForm;
	
	
	
	public AddAccountInfoThread(MainForm mainForm, AccountInfoForm accountInfoForm) {
		super();
		this.mainForm = mainForm;
		this.accountInfoForm = accountInfoForm;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String cmnd = accountInfoForm.txtCMND.getText().toString();
		String sotk = accountInfoForm.txtSTK.getText().toString();
		String macn;
		if(accountInfoForm.cbbChiNhanh.getSelectedIndex() == 0) {
			macn = "HANOI";
		}else if(accountInfoForm.cbbChiNhanh.getSelectedIndex() == 1) {
			macn = "DANANG";
		}else {
			macn = "HCM";
		}
		
		try {
			mainForm.database.insertAccountInfo(sotk, cmnd, macn);
			mainForm.showMessage("Cập nhật thành công");
			accountInfoForm.dispose();
		} catch (Exception e) {
			mainForm.showMessage("Có lỗi xảy ra");
			e.printStackTrace();
		}
	}
}
