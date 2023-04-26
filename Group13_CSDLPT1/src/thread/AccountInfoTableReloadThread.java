package thread;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

import controls.Database;
import guis.MainForm;

public class AccountInfoTableReloadThread extends Thread {
	MainForm mainform;
	Database database;
	
	public AccountInfoTableReloadThread(MainForm mainform, Database database) {
		super();
		this.mainform = mainform;
		this.database = database;
	};
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			mainform.accountInfoRow.clear();
			
			ResultSet rst = mainform.database.selectAccountInfo();
			while(rst.next()) {
				
				 Vector row = new Vector(3);
				 String cmnd = rst.getString("CMND");
				 String sotk = rst.getString("SOTK");
				 int sodu = rst.getInt("SODU");
				 
				 System.out.println(cmnd);
				 row.add(cmnd);
				 row.add(sotk);
				 
				 Locale locale = new Locale("vi", "VN");
			     NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
			     String tienDaTach = currencyFormatter.format(sodu);
			     row.add(tienDaTach);
			     
				 mainform.accountInfoRow.add(row);
				 mainform.accountInfoDefaultTable.fireTableDataChanged();
				 
			}
			
			stop();
		} catch (Exception e) {
			mainform.showMessage("Có lỗi xảy ra");
			e.printStackTrace();
			stop();
		}
		
		
		stop();
	}
}
