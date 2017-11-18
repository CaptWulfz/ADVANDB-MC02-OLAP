package util;

import java.util.ArrayList;

public class DBTest {

	public static void main(String[] args) {
		DBHelper db = new DBHelper();

		//		db.connectToDatabase();
		ArrayList<String> tempList = new ArrayList<String>();
		tempList.add("Region");
		tempList.add("Income");
		String[][] temp = db.getSumDrillDown(tempList);
		String[] colNames = db.getColNames();
		

		for(int i = 0; i < temp.length; i++) {
			for(int j = 0; j < 3; j++)
				System.out.println(temp[i][j]);
		}


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
