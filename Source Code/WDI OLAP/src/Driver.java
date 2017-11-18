import java.awt.EventQueue;

import gui.main;
import util.DBHelper;

public class Driver {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBHelper db = new DBHelper();
					new main(db);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
