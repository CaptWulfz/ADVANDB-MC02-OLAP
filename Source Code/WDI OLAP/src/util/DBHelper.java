package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBHelper {

	private Connection connection;
	
	private String[] colNames = null; /* to get column names, call getColNames() */
	private int row = 0;
	private int col = 0;
	
	public DBHelper() {
		connectToDatabase();
	}

	public void connectToDatabase() {
		String driver = "com.mysql.jdbc.Driver";
		String database = "wdi_db";
		String url = "jdbc:mysql://localhost:3306/";
		String username = "root";
		String password = "1234";  

		try {
			Class.forName(driver);   
			connection = DriverManager.getConnection(url + database, username, password);
			System.out.println("Successfully connected to WDI Database");
		} catch (Exception ex) {
			System.out.println("Cannot connect to Database " + url + database);
			ex.printStackTrace();
		}
	}

	/* returns a 2d string array with all the contents of data by year table */
	public String[][] getDataByYear() {

		String[][] result = null;
		colNames = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) as RowCount FROM data_by_year;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);

			while(rs.next())			
				row = rs.getInt("RowCount");

			/* prepare actual query */
			statement = "SELECT * FROM data_by_year;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			/* get number of col */			
			col = rs.getMetaData().getColumnCount();
			colNames = new String[col];

			result = new String[row][col];

			row = 0;
			
			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}
			
			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;

	}

	/* roll up; get sum of data per country; # of rows: 218, # of columns: 2*/ 
	public String[][] getSumByCountry(){
		String[][] result = null;
		colNames = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) AS RowCount\r\n" + 
					"FROM (SELECT CountryCode, SUM(Data) AS SumByCountry\r\n" + 
					"FROM data_by_year \r\n" + 
					"GROUP BY CountryCode WITH ROLLUP) AS SumByCountryTable;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);

			int row = 0;
			while(rs.next())			
				row = rs.getInt("RowCount");

			System.out.println("row: " + row);

			statement = "SELECT CountryCode, SUM(Data) as SumByCountry\r\n" + 
					"FROM data_by_year\r\n" + 
					"group by CountryCode with rollup;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			int col;
		
			/* get number of columns */			
			col = rs.getMetaData().getColumnCount();
			
			result = new String[row][col];
			colNames = new String[col];
			
			
			System.out.println("col: " + col);

			row = 0;

			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}

			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/* roll up; get sum of data per year; # of rows: 10, # of columns: 2 */ 
	public String[][] getSumByYear(){
		String[][] result = null;
		String[] colNames = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) AS RowCount\r\n" + 
					"FROM (SELECT YearC, SUM(Data) AS SumByYear\r\n" + 
					"FROM data_by_year \r\n" + 
					"GROUP BY YearC WITH ROLLUP) AS SumByCountryTable;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);

			while(rs.next())			
				row = rs.getInt("RowCount");

			/* prepare actual query */
			statement = "SELECT YearC, SUM(Data) as SumByYear\r\n" + 
					"FROM data_by_year\r\n" + 
					"group by YearC with rollup;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			/* get number of col */			
			col = rs.getMetaData().getColumnCount();

			result = new String[row][col];
			colNames = new String[col];

			row = 0;

			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}

			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/* roll up; get sum of data per series code; # of rows: 373, # of columns: 2 */
	public String[][] getSumBySeriesCode(){
		String[][] result = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) AS RowCount\r\n" + 
					"FROM (SELECT SeriesCode, SUM(Data) AS SumBySeriesCode\r\n" + 
					"FROM data_by_year \r\n" + 
					"GROUP BY SeriesCode WITH ROLLUP) AS SumByCountryTable;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);

			while(rs.next())			
				row = rs.getInt("RowCount");

			statement = "SELECT SeriesCode, SUM(Data) as SumBySeriesCode\r\n" + 
					"FROM data_by_year\r\n" + 
					"group by SeriesCode with rollup;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			/* get number of col */			
			col = rs.getMetaData().getColumnCount();

			result = new String[row][col];
			colNames = new String[col];

			row = 0;

			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}

			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}

	/* roll up; get sum of data per region */
	public String[][] getSumByRegion(){
		String[][] result = null;
		colNames = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) AS RowCount\r\n" + 
					"FROM (SELECT cr.Region, SUM(d.Data) AS SumByRegion\r\n" + 
					"FROM data_by_year d, country_region cr\r\n" + 
					"WHERE d.CountryCode = cr.CountryCode\r\n" + 
					"GROUP BY cr.Region WITH ROLLUP) AS SumByRegionTable;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);
			
			while(rs.next())			
				row = rs.getInt("RowCount");

			statement = "SELECT cr.Region, SUM(d.Data) AS SumByRegion\r\n" + 
					"FROM data_by_year d, country_region cr\r\n" + 
					"WHERE d.CountryCode = cr.CountryCode\r\n" + 
					"GROUP BY cr.Region WITH ROLLUP;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			/* get number of columns */			
			col = rs.getMetaData().getColumnCount();

			result = new String[row][col];
			colNames = new String[col];
			
			row = 0;

			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}

			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}


	public String[][] getSumByCategory(){
		String[][] result = null;
		colNames = null;

		try {
			PreparedStatement ps = null;
			ResultSet rs = null;

			/* get number of rows */ 
			String statement = "SELECT COUNT(*) AS RowCount\r\n" + 
					"FROM (SELECT sc.SeriesCategory, SUM(d.Data) AS SumByCategory\r\n" + 
					"FROM data_by_year d, series_category sc\r\n" + 
					"WHERE d.SeriesCode = sc.SeriesCode\r\n" + 
					"GROUP BY sc.SeriesCategory WITH ROLLUP;) AS SumByCategory;";
			ps = connection.prepareStatement(statement);	
			rs = ps.executeQuery(statement);
			
			while(rs.next())			
				row = rs.getInt("RowCount");

			statement = "SELECT sc.SeriesCategory, SUM(d.Data) AS SumByCategory\r\n" + 
					"FROM data_by_year d, series_category sc\r\n" + 
					"WHERE d.SeriesCode = sc.SeriesCode\r\n" + 
					"GROUP BY sc.SeriesCategory WITH ROLLUP;";	
			ps = connection.prepareStatement(statement);
			rs = ps.executeQuery(statement);	

			/* get number of columns */			
			col = rs.getMetaData().getColumnCount();

			result = new String[row][col];
			colNames = new String[col];
			
			row = 0;

			while(rs.next()) {
				for(int i = 0; i < col; i++) {
					result[row][i] = rs.getString(i+1);
					colNames[i] = rs.getMetaData().getColumnLabel(i+1);
				}

				row++;
			}

			rs.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return result;
	}
	
	/* call to after every query get column names */
	public String[] getColNames() {
		return colNames;
	}


}
