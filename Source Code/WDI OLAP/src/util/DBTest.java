package util;

public class DBTest {

	public static void main(String[] args) {
		DBHelper db = new DBHelper();

		//		db.connectToDatabase();
		String[][] temp = db.getSumSlice("Income", "Lower middle");
		String[] colNames = db.getColNames();
		
		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < 2; j++)
				System.out.println(temp[i][j]);
		}

		for(int i = 0; i < colNames.length; i++) {
			System.out.println(colNames[i]);
		}
	}

}
