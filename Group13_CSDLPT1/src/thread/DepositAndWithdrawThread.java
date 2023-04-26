package thread;

import guis.MainForm;

public class DepositAndWithdrawThread extends Thread {
	MainForm mainform;
	
	
	public DepositAndWithdrawThread(MainForm mainform) {
		super();
		this.mainform = mainform;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	
		try {
			String sotk = mainform.lblAccount_Deposit.getText().toString();
			int sotien = Integer.parseInt(mainform.txtDepositBalance.getText().toString());
			String loaigd;
			if(mainform.cbbTypeTrans.getSelectedIndex() == 0) {
				mainform.database.depositMoney(sotk, sotien, mainform.currentMaNV);
			}else {
				mainform.database.withdraw(sotk, sotien, mainform.currentMaNV);
			}
			
			mainform.showMessage("Giao dịch thành công");
		} catch (Exception e) {
			e.printStackTrace();
			mainform.showMessage("Có lỗi xảy ra");
		}
		
	}
}
