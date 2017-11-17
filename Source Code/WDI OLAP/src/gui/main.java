package gui;


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
	private JTextField displayNameField;
	
	/**
	 * Create the application.
	 */
	public main() {
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 935, 658);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("OLAP");
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(286, 80, 619, 518);
		tabbedPane.setName("TabPane");
		frame.getContentPane().add(tabbedPane);
		
		scrollPane = new JScrollPane();
		tabbedPane.addTab("main", null, scrollPane, null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"as", "as"},
				{"asas", "asasa"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 107, 262, 491);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblActions = new JLabel("OLAP Operations:");
		lblActions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblActions.setBounds(12, 13, 117, 26);
		panel.add(lblActions);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-- Select --", "Roll Up", "Slice", "Dice", "Drill Down", "Pivot"}));
		comboBox.setBounds(12, 47, 117, 22);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(12, 82, 238, 396);
		panel.add(panel_1);
		
		JLabel lblDisplayName = new JLabel("Display Name:");
		
		displayNameField = new JTextField();
		displayNameField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblTables = new JLabel("Tables Selected: ");
		
		JButton tablesBtn = new JButton("Add / Remove Tables");
		
		JLabel lblNone = new JLabel("None");
		lblNone.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblColumnsSelected = new JLabel("Columns Selected:");
		
		JLabel label = new JLabel("None");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton columnsBtn = new JButton("Add / Remove Columns");
		
		JSeparator separator_2 = new JSeparator();
		
		JLabel lblConditions = new JLabel("Conditions:");
		
		JLabel label_1 = new JLabel("None");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton conditionsBtn = new JButton("Add / Remove Conditions");
		
		JSeparator separator_3 = new JSeparator();
		
		JButton generateBtn = new JButton("Generate Display");
		generateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO Get Columns and Rows
				String[] columns = new String[] {"Masamune", "Murasaki"};
				Object[][] rows = new Object[][] {
					{"here", "there"},
					{"more", "lore"}
				};
				
				createNewTab(displayNameField.getText(), columns, rows);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDisplayName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(displayNameField, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
							.addGap(12))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(94)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(98)
					.addComponent(lblNone)
					.addContainerGap(106, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(63)
					.addComponent(lblColumnsSelected)
					.addContainerGap(66, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(68)
					.addComponent(lblTables)
					.addContainerGap(68, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(78)
					.addComponent(lblConditions)
					.addContainerGap(94, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(96)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(108, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(30)
					.addComponent(conditionsBtn)
					.addContainerGap(29, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(54)
					.addComponent(generateBtn)
					.addContainerGap(53, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(35)
					.addComponent(columnsBtn)
					.addContainerGap(34, Short.MAX_VALUE))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(40)
					.addComponent(tablesBtn)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDisplayName)
						.addComponent(displayNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTables)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNone)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tablesBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblColumnsSelected)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(columnsBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblConditions)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(conditionsBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(generateBtn)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		createComponentMap();
		
		// Testing the new tab
		System.out.println("Creating new tab... ");
		
		String[] columns = new String[]{"Column 1", "Column 2", "Column 3"};
		Object[][] rows = new Object[][] {
			{"holy", "moly", "lowly"},
			{"shit", "mate", "late"}
		};
		
		createNewTab("New Tab 2", columns, rows);
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
