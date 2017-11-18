package gui;

import util.DBHelper;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Component;

import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main {

	private HashMap<String, Component> componentMap;
	
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	
	private DBHelper db;
	
	/**
	 * Create the application.
	 */
	public main(DBHelper db) {
		this.db = db;
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 959, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("OLAP");
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(327, 51, 602, 518);
		tabbedPane.setName("TabPane");
		frame.getContentPane().add(tabbedPane);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("Data By Year", null, scrollPane, null);
		
		table = new JTable();
		/*
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"as", "as"},
				{"asas", "asasa"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		*/
		
		Object[][] rows = db.getDataByYear();
		String[] columns = db.getColNames();
		
		table.setModel(createModel(columns, rows));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 78, 199, 491);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblActions = new JLabel("OLAP Operations:");
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblActions.setBounds(12, 13, 117, 26);
		panel.add(lblActions);
		
		JComboBox operationBox = new JComboBox();
		operationBox.setModel(new DefaultComboBoxModel(new String[] {"-- Select --", "Roll Up", "Slice", "Dice", "Drill Down", "Pivot"}));
		operationBox.setBounds(12, 47, 117, 22);
		panel.add(operationBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 82, 174, 396);
		panel.add(panel_1);
		panel_1.setEnabled(false);
		
		JPanel rollUpPanel = new JPanel();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(rollUpPanel, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(rollUpPanel, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addGap(93))
		);
		
		JButton countryBtn = new JButton("Sum By Country");
		countryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] rows = db.getSumByCountry();
				String[] columns = db.getColNames();
				
				if (operationBox.getSelectedItem().equals("Roll Up"))
					rows = db.getSumByCountry();
				
				columns = db.getColNames();
				
				createNewTab("Sum By Country", columns, rows);
			}
		});
		
		JButton incomeBtn = new JButton("Sum By Income");
		incomeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] rows = db.getSumByIncome();
				String[] columns = db.getColNames();
				
				createNewTab("Sum By Income", columns, rows);
			}
		});
		
		JButton yearBtn = new JButton("Sum By Year");
		yearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] rows = db.getSumByYear();
				String[] columns = db.getColNames();
				
				createNewTab("Sum By Year", columns, rows);
			}
		});
		
		JButton seriesCodeBtn = new JButton("Sum By Series Code");
		seriesCodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] rows = db.getSumBySeriesCode();
				String[] columns = db.getColNames();
				
				createNewTab("Sum By Series Code", columns, rows);
			}
		});
		
		JButton regionBtn = new JButton("Sum By Region");
		regionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[][] rows = db.getSumByRegion();
				String[] columns = db.getColNames();
				
				createNewTab("Sum By Region", columns, rows);
			}
		});
		
		JButton categoryBtn = new JButton("Sum By Category");
		categoryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[][] rows = db.getSumByCategory();
				String[] columns = db.getColNames();
				
				createNewTab("Sum By Category", columns, rows);
			}
		});
		GroupLayout gl_rollUpPanel = new GroupLayout(rollUpPanel);
		gl_rollUpPanel.setHorizontalGroup(
			gl_rollUpPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_rollUpPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rollUpPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(categoryBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(seriesCodeBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(incomeBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
						.addComponent(regionBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(yearBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.addComponent(countryBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.addGap(120))
		);
		gl_rollUpPanel.setVerticalGroup(
			gl_rollUpPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rollUpPanel.createSequentialGroup()
					.addGap(22)
					.addComponent(countryBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(yearBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(regionBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(incomeBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(seriesCodeBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(categoryBtn)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		rollUpPanel.setLayout(gl_rollUpPanel);
		panel_1.setLayout(gl_panel_1);
		
		createComponentMap();
		
	}
	
	//XXX Beginning of Functions for use
	
	private void createNewTab(String TabName, String[] columns, Object[][] rows) {
		
		JTabbedPane tabbedPane = (JTabbedPane) getComponentByName("TabPane");
		
		JTable newTable = new JTable();
		newTable.setModel(createModel(columns, rows));
		
		tabbedPane.addTab(TabName, null, new JScrollPane(newTable), null);
		
		createComponentMap();
	}
	
	private void createComponentMap() {
		if (componentMap == null)
			componentMap = new HashMap<String, Component>();
		else
			componentMap.clear();
		
		Component[] components = frame.getContentPane().getComponents();
		for (int i = 0; i < components.length; i++)
			componentMap.put(components[i].getName(), components[i]);
	}
	
	private Component getComponentByName(String name) {
		if (componentMap.containsKey(name))
			return (Component) componentMap.get(name);
		else
			return null;
	}
	
	public void enableCustom() {
		
		
	}
	
	/** This function gets gets the model for the table to use for displaying
	 * @param columns
	 * @param rows
	 * @return DefaultTableModel
	 */
	public DefaultTableModel createModel(String[] columns, Object[][] rows) {
		DefaultTableModel tableModel = new DefaultTableModel();
		
		//Add Columns
		for (int i = 0; i < columns.length; i++)
			tableModel.addColumn(columns[i]);
		
		//Add Rows
		for (int i = 0; i < rows.length; i++)
			tableModel.addRow(rows[i]);
		
		return tableModel;
	}
}
