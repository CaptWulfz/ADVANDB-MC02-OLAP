import java.awt.EventQueue;

import gui.main;
import util.DBHelper;

public class Driver {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
