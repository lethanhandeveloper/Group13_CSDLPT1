package thread;

import guis.MainForm;

public class TransferMoneyThread extends Thread	{
	MainForm mainform;
	
	
	
	public TransferMoneyThread(MainForm mainform) {	
		super();
		this.mainform = mainform;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			mainform.database.transferMoney(mainform.txtAccountNumber.getText().toString()
					, mainform.txtToAccountNumber.getText().toString(), Integer.parseInt(mainform.txtToBalance.getText().toString())
					, mainform.currentMaNV);
			
			mainform.showMessage("Đã chuyển tiền");
		} catch (Exception e) {
			mainform.showMessage("Có lỗi xảy ra");
			e.printStackTrace();
		}
	}
}
