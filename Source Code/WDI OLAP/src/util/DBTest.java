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
		
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[i].length; j++)
				System.out.print(temp[i][j] + " ");
			System.out.println();
		}
	}

}
