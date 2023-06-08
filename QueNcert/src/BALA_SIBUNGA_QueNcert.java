import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.ButtonGroup;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Bala, Justin T.
// Sibunga, Jy James Luke V.
// INF-213

// QueNcert: A Queueing System for Onsite Ticket Sales for Ticket Distributors in Shopping Malls

class QueueCustomer {		//Created a class for customers or users who will enter their information.
	
	public String Surname, FirstName, City, Age, PreferredSec, BackupSec;		//String field for all details
	
	public QueueCustomer (String sn, String fn, String age, String city, String opA, String opB) {
		
		this.Surname = sn;
		this.FirstName = fn;
		this.Age = age;
		this.City = city;
		this.PreferredSec = opA;
		this.BackupSec = opB;
		
	}
	
}

public class BALA_SIBUNGA_QueNcert {

	JFrame frame;
	public static JTable Sec1_Table, Sec2_Table, Sec3_Table, Sec4_Table, Sec5_Table, Sec6_Table;
	private JTextField textFieldSurname, textFieldFirstN, textFieldAge, textFieldCity;
	public final ButtonGroup buttonGroup1 = new ButtonGroup();
	public final ButtonGroup buttonGroup2 = new ButtonGroup();;
	
	//Creates LinkedList for each section to separate customers.
	static LinkedList<QueueCustomer> VIPStanding = new LinkedList<>();
	static LinkedList<QueueCustomer> VIPSeated = new LinkedList<>();
	static LinkedList<QueueCustomer> Lowerbox = new LinkedList<>();
	static LinkedList<QueueCustomer> Upperbox = new LinkedList<>();
	static LinkedList<QueueCustomer> GenAd = new LinkedList<>();
	static LinkedList<QueueCustomer> Generic = new LinkedList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BALA_SIBUNGA_QueNcert window = new BALA_SIBUNGA_QueNcert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BALA_SIBUNGA_QueNcert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 884, 575);
		frame.setTitle("QueNcert (Made by Justin Bala and Jy Sibunga of INF-213)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblQueNcert = new JLabel("QueNcert");
		lblQueNcert.setFont(new Font("Helvetica Neue LT Black", Font.PLAIN, 40));
		lblQueNcert.setBounds(21, 39, 228, 52);
		frame.getContentPane().add(lblQueNcert);
		
		JLabel lblDescrip1 = new JLabel("Welcome! We're here to accompany you");
		lblDescrip1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDescrip1.setBounds(21, 89, 208, 14);
		frame.getContentPane().add(lblDescrip1);
		
		JLabel lblDescrip2 = new JLabel("for today's onsite ticket selling.");
		lblDescrip2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDescrip2.setBounds(21, 102, 158, 14);
		frame.getContentPane().add(lblDescrip2);
		
		JButton btnAdd = new JButton("Add to Queue");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Gets user input and turns them into Strings.
				String surname = textFieldSurname.getText();
				String firstname = textFieldFirstN.getText();
				String age = textFieldAge.getText();
				String city = textFieldCity.getText();
				
				//If text fields are empty, program will prompt user to complete all details.
				if (surname.isEmpty() || firstname.isEmpty() || age.isEmpty() || city.isEmpty()) {
					
					String warning = "Please fill all the blanks.";
					JOptionPane.showMessageDialog(new JFrame(),  warning, "Warning", JOptionPane.WARNING_MESSAGE);
				
				//If there is nothing selected in radio buttons, program will prompt user to select sections.
				} else if (buttonGroup1.getSelection() == null || buttonGroup2.getSelection() == null) {
					
					String warning = "Please select sections.";
					JOptionPane.showMessageDialog(new JFrame(),  warning, "Warning", JOptionPane.WARNING_MESSAGE);
				
				//If selection sections are the same, program will prompt user to select different sections.
				} else if (buttonGroup1.getSelection().getActionCommand() == buttonGroup2.getSelection().getActionCommand()) {
					
					String warning = "Please select different sections.";
					JOptionPane.showMessageDialog(new JFrame(),  warning, "Warning", JOptionPane.WARNING_MESSAGE);
				
				} else {
					
					//Prompts user of all information are correct before proceeding
					int a=JOptionPane.showConfirmDialog(null, "Are your information correct?", "Select", JOptionPane.YES_NO_OPTION);
					
					if (a==1) {		//If NO, prompt will close and all fields will be reset.
						
						textFieldSurname.setText("");
						textFieldFirstN.setText("");
						textFieldAge.setText("");
						textFieldCity.setText("");
						buttonGroup1.clearSelection();
						buttonGroup2.clearSelection();
										
						
					} else {
					
						
						if (buttonGroup1.getSelection().getActionCommand() == "VIP Standing") {
						
							//If VIP Standing is preferred seat, customer profile will be queued to VIP Standing LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							VIPStanding.add(add);
						
							//Information will be ran through adding details to VIP Standing Table.
							AddRowToJTable1(new Object [] { });
						
						
						} else if (buttonGroup1.getSelection().getActionCommand() == "VIP Seated") {
						
							//If VIP Seated is preferred seat, customer profile will be queued to VIP Seated LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							VIPSeated.add(add);
						
							//Information will be ran through adding details to VIP Seated Table.
							AddRowToJTable2(new Object [] {	});
						
						} else if (buttonGroup1.getSelection().getActionCommand() == "Lowerbox") {
						
							//If Lowerbox is preferred seat, customer profile will be queued to Lowerbox LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							Lowerbox.add(add);
						
							//Information will be ran through adding details to Lowerbox.
							AddRowToJTable3(new Object [] { });
							
						} else if (buttonGroup1.getSelection().getActionCommand() == "Upperbox") {
						
							//If Upperbox is preferred seat, customer profile will be queued to Upperbox LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							Upperbox.add(add);
						
							//Information will be ran through adding details to Upperbox.
							AddRowToJTable4(new Object [] { });
						
						} else if (buttonGroup1.getSelection().getActionCommand() == "General Admission") {
						
							//If General Admission is preferred seat, customer profile will be queued to GenAd LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							GenAd.add(add);
						
							//Information will be ran through adding details to GenAd.
							AddRowToJTable5(new Object [] {	});
						
						} else if (buttonGroup1.getSelection().getActionCommand() == "Generic") {
						
							//If Generic is preferred seat, customer profile will be queued to Generic LinkedList.
							QueueCustomer add = new QueueCustomer (surname, firstname, age, city, buttonGroup1.getSelection().getActionCommand(), buttonGroup2.getSelection().getActionCommand());
							Generic.add(add);
						
							//Information will be ran through adding details to Generic.
							AddRowToJTable6(new Object [] {	});						
						
						}
						
						//Once customer details are queued to their respective section, form will reset for next customer.
						textFieldSurname.setText("");
						textFieldFirstN.setText("");
						textFieldAge.setText("");
						textFieldCity.setText("");
						buttonGroup1.clearSelection();
						buttonGroup2.clearSelection();
						
					}
					
				}								
								
			}
		});
		btnAdd.setFont(new Font("Helvetica Neue LT", Font.BOLD, 16));
		btnAdd.setBounds(21, 470, 190, 36);
		frame.getContentPane().add(btnAdd);
		
		//Codes for the table and tabs.
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBounds(356, 26, 489, 480);
		frame.getContentPane().add(tabbedPane);
		
		JScrollPane scrollPane1 = new JScrollPane();
		tabbedPane.addTab("VIP Standing", null, scrollPane1, null);
		
		Sec1_Table = new JTable();
		Sec1_Table.setRowSelectionAllowed(false);
		scrollPane1.setViewportView(Sec1_Table);
		Sec1_Table.setModel(new DefaultTableModel(
			new Object[][] { 
			},
			new String[] {
				"Surname", "First Name", "Age", "City", "Backup"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		Sec1_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		JScrollPane scrollPane2 = new JScrollPane();
		tabbedPane.addTab("VIP Seated", null, scrollPane2, null);
		
		Sec2_Table = new JTable();
		Sec2_Table.setRowSelectionAllowed(false);
		scrollPane2.setViewportView(Sec2_Table);
		Sec2_Table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Surname", "First Name", "Age", "City", "Backup"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			Sec2_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		JScrollPane scrollPane3 = new JScrollPane();
		tabbedPane.addTab("Lowerbox", null, scrollPane3, null);
		
		Sec3_Table = new JTable();
		Sec3_Table.setRowSelectionAllowed(false);
		scrollPane3.setViewportView(Sec3_Table);
		Sec3_Table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Surname", "First Name", "Age", "City", "Backup"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			Sec3_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		JScrollPane scrollPane4 = new JScrollPane();
		tabbedPane.addTab("Upperbox", null, scrollPane4, null);
		
		Sec4_Table = new JTable();
		Sec4_Table.setRowSelectionAllowed(false);
		scrollPane4.setViewportView(Sec4_Table);
		Sec4_Table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Surname", "First Name", "Age", "City", "Backup"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			Sec4_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		JScrollPane scrollPane5 = new JScrollPane();
		tabbedPane.addTab("Gen Ad", null, scrollPane5, null);
		
		Sec5_Table = new JTable();
		Sec5_Table.setRowSelectionAllowed(false);
		scrollPane5.setViewportView(Sec5_Table);
		Sec5_Table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Surname", "First Name", "Age", "City", "Backup"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			Sec5_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		JScrollPane scrollPane6 = new JScrollPane();
		tabbedPane.addTab("Generic", null, scrollPane6, null);
		
		Sec6_Table = new JTable();
		Sec6_Table.setRowSelectionAllowed(false);
		scrollPane6.setViewportView(Sec6_Table);
		Sec6_Table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Surname", "First Name", "Age", "City", "Backup"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			Sec6_Table.getColumnModel().getColumn(2).setPreferredWidth(43);
		
		//Codes for the inputs of the user	
			
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblSurname.setBounds(21, 139, 90, 14);
		frame.getContentPane().add(lblSurname);
		
		textFieldSurname = new JTextField();
		textFieldSurname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {		//Prevents the user from entering numbers in name textfield.
				
				char c = e.getKeyChar();
				
				if(!Character.isLetter(c) && !Character.isWhitespace(c) && !Character.isISOControl(c)) {
					e.consume();
					
				}
				
			}
		});
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(121, 136, 146, 20);
		frame.getContentPane().add(textFieldSurname);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblFirstName.setBounds(21, 167, 90, 14);
		frame.getContentPane().add(lblFirstName);
		
		textFieldFirstN = new JTextField();
		textFieldFirstN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {		//Prevents the user from entering numbers in name textfield.
				
				char c = e.getKeyChar();
				
				if(!Character.isLetter(c) && !Character.isWhitespace(c) && !Character.isISOControl(c)) {
					e.consume();
					
				}
				
			}
		});
		textFieldFirstN.setColumns(10);
		textFieldFirstN.setBounds(121, 164, 146, 20);
		frame.getContentPane().add(textFieldFirstN);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblAge.setBounds(21, 206, 90, 14);
		frame.getContentPane().add(lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar(); 			
				
				//Ensures that user only inputs integers for age
				//and that user only gets to input a maximum of 2 digits for age.
				if(!Character.isDigit(c) == textFieldAge.getText().length()<=1) { 	
					e.consume();
					
				}
								
			}
		});
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(121, 203, 118, 20);
		frame.getContentPane().add(textFieldAge);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblCity.setBounds(21, 234, 90, 14);
		frame.getContentPane().add(lblCity);
		
		textFieldCity = new JTextField();
		textFieldCity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {		//Prevents user from entering numbers in city textfield.
				
				char c = e.getKeyChar(); 
				
				if(!Character.isLetter(c) && !Character.isWhitespace(c) && !Character.isISOControl(c)) {
					e.consume();
					
				}
				
			}
		});
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(121, 231, 118, 20);
		frame.getContentPane().add(textFieldCity);
		
		//Codes for the RadioButtons where customer will select their preferred and backup section.
		
		JLabel lblPreferred = new JLabel("Preferred Section:");
		lblPreferred.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblPreferred.setBounds(21, 279, 142, 14);
		frame.getContentPane().add(lblPreferred);
		
		JRadioButton rdbtn1st_1 = new JRadioButton("VIP Standing");
		buttonGroup1.add(rdbtn1st_1);
		rdbtn1st_1.setActionCommand("VIP Standing");
		rdbtn1st_1.setBounds(21, 300, 142, 23);
		frame.getContentPane().add(rdbtn1st_1);
		
		JRadioButton rdbtn1st_2 = new JRadioButton("VIP Seated");
		buttonGroup1.add(rdbtn1st_2);
		rdbtn1st_2.setActionCommand("VIP Seated");
		rdbtn1st_2.setBounds(21, 326, 142, 23);
		frame.getContentPane().add(rdbtn1st_2);
		
		JRadioButton rdbtn1st_3 = new JRadioButton("Lowerbox");
		buttonGroup1.add(rdbtn1st_3);
		rdbtn1st_3.setActionCommand("Lowerbox");
		rdbtn1st_3.setBounds(21, 352, 142, 23);
		frame.getContentPane().add(rdbtn1st_3);
		
		JRadioButton rdbtn1st_4 = new JRadioButton("Upperbox");
		buttonGroup1.add(rdbtn1st_4);
		rdbtn1st_4.setActionCommand("Upperbox");
		rdbtn1st_4.setBounds(21, 378, 142, 23);
		frame.getContentPane().add(rdbtn1st_4);
		
		JRadioButton rdbtn1st_5 = new JRadioButton("General Admission");
		buttonGroup1.add(rdbtn1st_5);
		rdbtn1st_5.setActionCommand("General Admission");
		rdbtn1st_5.setBounds(21, 404, 142, 23);
		frame.getContentPane().add(rdbtn1st_5);
		
		JRadioButton rdbtn1st_6 = new JRadioButton("Generic");
		buttonGroup1.add(rdbtn1st_6);
		rdbtn1st_6.setActionCommand("Generic");
		rdbtn1st_6.setBounds(21, 430, 142, 23);
		frame.getContentPane().add(rdbtn1st_6);
		
		JLabel lblBackup = new JLabel("Backup Choice:");
		lblBackup.setFont(new Font("Helvetica Neue LT", Font.BOLD, 14));
		lblBackup.setBounds(169, 279, 142, 14);
		frame.getContentPane().add(lblBackup);
		
		JRadioButton rdbtn2nd_1 = new JRadioButton("VIP Standing");
		buttonGroup2.add(rdbtn2nd_1);
		rdbtn2nd_1.setActionCommand("VIP Standing");
		rdbtn2nd_1.setBounds(169, 300, 142, 23);
		frame.getContentPane().add(rdbtn2nd_1);
		
		JRadioButton rdbtn2nd_2 = new JRadioButton("VIP Seated");
		buttonGroup2.add(rdbtn2nd_2);
		rdbtn2nd_2.setActionCommand("VIP Seated");
		rdbtn2nd_2.setBounds(169, 326, 142, 23);
		frame.getContentPane().add(rdbtn2nd_2);
		
		JRadioButton rdbtn2nd_3 = new JRadioButton("Lowerbox");
		buttonGroup2.add(rdbtn2nd_3);
		rdbtn2nd_3.setActionCommand("Lowerbox");
		rdbtn2nd_3.setBounds(169, 352, 142, 23);
		frame.getContentPane().add(rdbtn2nd_3);
		
		JRadioButton rdbtn2nd_4 = new JRadioButton("Upperbox");
		buttonGroup2.add(rdbtn2nd_4);
		rdbtn2nd_4.setActionCommand("Upperbox");
		rdbtn2nd_4.setBounds(169, 378, 142, 23);
		frame.getContentPane().add(rdbtn2nd_4);
		
		JRadioButton rdbtn2nd_5 = new JRadioButton("General Admission");
		buttonGroup2.add(rdbtn2nd_5);
		rdbtn2nd_5.setActionCommand("General Admission");
		rdbtn2nd_5.setBounds(169, 404, 142, 23);
		frame.getContentPane().add(rdbtn2nd_5);
		
		JRadioButton rdbtn2nd_6 = new JRadioButton("Generic");
		buttonGroup2.add(rdbtn2nd_6);
		rdbtn2nd_6.setActionCommand("Generic");
		rdbtn2nd_6.setBounds(169, 430, 142, 23);
		frame.getContentPane().add(rdbtn2nd_6);
		
		Sec6_Table.getTableHeader().setReorderingAllowed(false);
		Sec6_Table.getTableHeader().setResizingAllowed(false);
		Sec5_Table.getTableHeader().setReorderingAllowed(false);
		Sec5_Table.getTableHeader().setResizingAllowed(false);
		Sec4_Table.getTableHeader().setReorderingAllowed(false);
		Sec4_Table.getTableHeader().setResizingAllowed(false);
		Sec3_Table.getTableHeader().setReorderingAllowed(false);
		Sec3_Table.getTableHeader().setResizingAllowed(false);
		Sec2_Table.getTableHeader().setReorderingAllowed(false);
		Sec2_Table.getTableHeader().setResizingAllowed(false);
		Sec1_Table.getTableHeader().setReorderingAllowed(false);
		Sec1_Table.getTableHeader().setResizingAllowed(false);
		
	}
	
	//Codes that will allow the different LinkedLists to reflect and to populate on their corresponding tables.
	//These codes will allow each customer to visually see their queue depending on their preferred section.
	
	public static void AddRowToJTable1(Object[] dataRow) {
		// VIP Standing Table
		
		DefaultTableModel model = (DefaultTableModel) Sec1_Table.getModel();
		Object[] row;

			row = new Object[5];
			row[0] = VIPStanding.getLast().Surname;				//SectionName.getLast() was used so that program will only add the rear input on the table
			row[1] = VIPStanding.getLast().FirstName;			//every time these codes are called by the "Add to Queue" button.
			row[2] = VIPStanding.getLast().Age;
			row[3] = VIPStanding.getLast().City;
			row[4] = VIPStanding.getLast().BackupSec;
			model.addRow(row);
	
	}
	
	public static void AddRowToJTable2(Object[] dataRow) {
		// VIP Seated Table
		
		DefaultTableModel model = (DefaultTableModel) Sec2_Table.getModel();
		Object[] row;

			row = new Object[5];
			row[0] = VIPSeated.getLast().Surname;
			row[1] = VIPSeated.getLast().FirstName;
			row[2] = VIPSeated.getLast().Age;
			row[3] = VIPSeated.getLast().City;
			row[4] = VIPSeated.getLast().BackupSec;
			model.addRow(row);
	
	}
	
	public static void AddRowToJTable3(Object[] dataRow) {
		// Lowerbox Table
		
		DefaultTableModel model = (DefaultTableModel) Sec3_Table.getModel();
		Object[] row;

		row = new Object[5];
		row[0] = Lowerbox.getLast().Surname;
		row[1] = Lowerbox.getLast().FirstName;
		row[2] = Lowerbox.getLast().Age;
		row[3] = Lowerbox.getLast().City;
		row[4] = Lowerbox.getLast().BackupSec;
		model.addRow(row);

}
	
	public static void AddRowToJTable4(Object[] dataRow) {
		// Upperbox Table
		
		DefaultTableModel model = (DefaultTableModel) Sec4_Table.getModel();
		Object[] row;

		row = new Object[5];
		row[0] = Upperbox.getLast().Surname;
		row[1] = Upperbox.getLast().FirstName;
		row[2] = Upperbox.getLast().Age;
		row[3] = Upperbox.getLast().City;
		row[4] = Upperbox.getLast().BackupSec;
		model.addRow(row);

}
	
	public static void AddRowToJTable5(Object[] dataRow) {
		// GenAd Table
		
		DefaultTableModel model = (DefaultTableModel) Sec5_Table.getModel();
		Object[] row;

		row = new Object[5];
		row[0] = GenAd.getLast().Surname;
		row[1] = GenAd.getLast().FirstName;
		row[2] = GenAd.getLast().Age;
		row[3] = GenAd.getLast().City;
		row[4] = GenAd.getLast().BackupSec;
		model.addRow(row);

}
	
	public static void AddRowToJTable6(Object[] dataRow) {
		// Generic Table
		
		DefaultTableModel model = (DefaultTableModel) Sec6_Table.getModel();
		Object[] row;

		row = new Object[5];
		row[0] = Generic.getLast().Surname;
		row[1] = Generic.getLast().FirstName;
		row[2] = Generic.getLast().Age;
		row[3] = Generic.getLast().City;
		row[4] = Generic.getLast().BackupSec;
		model.addRow(row);

}
	
}