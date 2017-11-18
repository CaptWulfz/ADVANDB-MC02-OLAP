package util;

public class DBTest {

	public static void main(String[] args) {
		DBHelper db = new DBHelper();

		//		db.connectToDatabase();
		String[][] temp = db.getSumByRegion();
		String[] colNames = db.getColNames();

		for(int i = 0; i < colNames.length; i++) {
			System.out.println(colNames[i]);
		}
	}

}
