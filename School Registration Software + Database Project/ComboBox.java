package UniversitySystemGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//AUTHOR: JARED KEKLAK (UNLESS ANNOTATED)
public class ComboBox extends JFrame implements ActionListener
{

	private static JLabel DropDownLabel;
	private JComboBox msglist;
	private String CURRENT_ITEM;

	JTextArea textArea;

	public static int STUDENT_ADD_COURSE = 1;
	private JButton register;

	public static int STUDENT_DROP_COURSE = 2;
	private JButton unregister;

	public static int PROFESSOR_MODIFY_COURSE_GRADES = 3;
	private JButton save;
	private JButton assign_grade;

	public static int PROFESSOR_MODIFY_COURSE_DESCRIPTION = 4;
	private JButton update;
	private JLabel title;

	public static int ADMINISTRATOR = 5;

	public static int ADMIN_STUDENT_ADD_COURSE = 9;
	public static int ADMIN_STUDENT_DROP_COURSE = 10;

	public static int DBA_MODIFY_STUDENT_GRADES = 6;
	public static int DBA_STUDENT_DROP_COURSE = 7;
	public static int DBA_STUDENT_ADD_COURSE = 8;

	private int role = 0;
	private String class_des;
	public String first_item;
	private String id;
	private String studen;
//ethan added id to pass through combo
	public ComboBox(String Title, List<String> StringList, int role, String id )
	{
		this.role = role;
		this.id = id;


		setLayout(null);
		setSize(450, 350);
		setTitle(Title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		first_item = StringList.get(0);

		msglist = new JComboBox(StringList.toArray());
		msglist.setSelectedIndex(0);
		msglist.addActionListener(this);
		msglist.setSize(120, 25);
		msglist.setLocation(10, 20);

		add(msglist);

		// testlabel.setSize(120, 25);
		// testlabel.setLocation(10, 200);
		// add(testlabel);

		if (role == STUDENT_ADD_COURSE)
		{
			this.role = role;
			register = new JButton("Add course");
			register.setBounds(170, 280, 110, 25);
			register.setForeground(Color.WHITE);
			register.setBackground(Color.DARK_GRAY);
			register.addActionListener(this);

			add(register);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to add");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);

		}

		if (role == STUDENT_DROP_COURSE)
		{

			unregister = new JButton("Drop course");
			unregister.setBounds(170, 280, 110, 25);
			unregister.setForeground(Color.WHITE);
			unregister.setBackground(Color.DARK_GRAY);
			unregister.addActionListener(this);

			add(unregister);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to drop");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);

		}
		if (role == PROFESSOR_MODIFY_COURSE_GRADES)
		{

			assign_grade = new JButton("Assign grade");
			assign_grade.setBounds(170, 280, 110, 25);
			assign_grade.setForeground(Color.WHITE);
			assign_grade.setBackground(Color.DARK_GRAY);
			assign_grade.addActionListener(this);

			add(assign_grade);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course roster");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);

		}

		if (role == ComboBox.PROFESSOR_MODIFY_COURSE_DESCRIPTION)
		{
			update = new JButton("Update");
			update.setBounds(170, 275, 110, 25);
			update.setForeground(Color.WHITE);
			update.setBackground(Color.DARK_GRAY);
			update.addActionListener(this);

			add(update);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(true);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course description");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);

		}
		if (role == ComboBox.ADMIN_STUDENT_ADD_COURSE)
		{
			this.role = role;
			register = new JButton("Add course");
			register.setBounds(170, 280, 110, 25);
			register.setForeground(Color.WHITE);
			register.setBackground(Color.DARK_GRAY);
			register.addActionListener(this);

			add(register);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to add");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);
		}
		if (role == ComboBox.ADMIN_STUDENT_DROP_COURSE)
		{
			unregister = new JButton("Drop course");
			unregister.setBounds(170, 280, 110, 25);
			unregister.setForeground(Color.WHITE);
			unregister.setBackground(Color.DARK_GRAY);
			unregister.addActionListener(this);

			add(unregister);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to drop");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);
		}

		if (role == ComboBox.DBA_MODIFY_STUDENT_GRADES)
		{
			assign_grade = new JButton("Assign grade");
			assign_grade.setBounds(170, 280, 110, 25);
			assign_grade.setForeground(Color.WHITE);
			assign_grade.setBackground(Color.DARK_GRAY);
			assign_grade.addActionListener(this);

			add(assign_grade);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course Information");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);
		}
		if (role == DBA_STUDENT_ADD_COURSE)
		{
			this.role = role;
			register = new JButton("Add course");
			register.setBounds(170, 280, 110, 25);
			register.setForeground(Color.WHITE);
			register.setBackground(Color.DARK_GRAY);
			register.addActionListener(this);

			add(register);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to add");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);
		}
		if (role == DBA_STUDENT_DROP_COURSE)
		{
			unregister = new JButton("Drop course");
			unregister.setBounds(170, 280, 110, 25);
			unregister.setForeground(Color.WHITE);
			unregister.setBackground(Color.DARK_GRAY);
			unregister.addActionListener(this);

			add(unregister);

			textArea = new JTextArea(50, 40);
			textArea.setBackground(Color.gray);
			textArea.setForeground(Color.BLACK);
			Font f = new Font("Consolas", Font.PLAIN, 12);
			textArea.setFont(f);
			textArea.setEditable(false);
			textArea.setSize(410, 220);
			textArea.setLocation(10, 50);

			JInternalFrame internal_frame = new JInternalFrame("Course to drop");
			internal_frame.setResizable(false);
			internal_frame.setVisible(true);
			internal_frame.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			internal_frame.setSize(410, 220);
			internal_frame.setResizable(false);
			internal_frame.setLocation(10, 50);

			JScrollPane scroll_panel = new JScrollPane(textArea);
			scroll_panel.setBackground(Color.darkGray);

			internal_frame.add(scroll_panel);
			// prevent user from moving internal frame, disable mouse listener for frame
			for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI())
					.getNorthPane().getMouseListeners())
			{
				((javax.swing.plaf.basic.BasicInternalFrameUI) internal_frame.getUI()).getNorthPane()
						.removeMouseListener(listener);
			}

			add(internal_frame);
		}

	}

	public static void main(String Title, List<String> StringList, int role, String id)
	{
		ComboBox fr = new ComboBox(Title, StringList, role, id);

		fr.setLocation(new Point(500, 300));
		fr.setAlwaysOnTop(false);
		fr.setVisible(true);
		fr.setResizable(false);

	}

	//implented by ethan  Student Add, Student Drop, Professor Modify Grade, Professor Modify Course. All frames of gui done by Jared. SQL implentation completed by ethan. Bug checking ethan.
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//implented by ethan marked with /e
		if (role == STUDENT_ADD_COURSE)
		{
			//e
			StudentSQL sql = new StudentSQL();
			Connection conn = UserLogin.New_Login("Student", "StudentPassword");
			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:
					//e + jared frame
					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "course" contains the course name as string.
																	// Run an SQL query to get the rest of its
																	// information.
					textArea.setText(""); // <--keep this line, it resets the text area after each selection
					textArea.setText("\n" + " " + cb.getSelectedItem().toString() + " selected: " + "\n");
					try {
						assert conn != null;
						Statement st = conn.createStatement();


						String query = "SELECT  coursename,COURSENUM, SECTIONNUM, PROFNAME, DES  FROM COURSE WHERE CRN= '" + CURRENT_ITEM + "';";
						ResultSet rs = st.executeQuery(query);
						textArea.setText("testing for more");
						while (rs.next()) {

							String coursename = rs.getString("COURSENAME");
							String coursen = rs.getString("COURSENUM");
							String secn = rs.getString("SECTIONNUM");
							String prof = rs.getString("PROFNAME");
							String desc = rs.getString("DES");
							textArea.setText("\n" + "CRN: "  + CURRENT_ITEM +  "\n" +"Course Number:"+coursen+ "-"+secn +"\n"+ "Course Name: " + coursename +"\n"+"Professor: "+prof+"\n"+"Description: "+desc);


						}

					}catch (SQLException m){
						System.out.println(m.getMessage());
					}

				}
			} //end of e bug checking
			if (e.getSource() == register)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// RUN SQL FUNCTION TO ADD COURSE

				boolean success = sql.add_Course(conn, id, CURRENT_ITEM ); // SQL boolean to check if course is sucessfully added

				if (success)
				{
					JOptionPane.showMessageDialog(null, "Class added.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Course not added.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}//e + bug checking
		if (role == STUDENT_DROP_COURSE)
		{
			StudentSQL sql = new StudentSQL();
			Connection conn = UserLogin.New_Login("Student", "StudentPassword");
			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:

					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "CURRENT_ITEM" contains the course name as
					try {
						assert conn != null;
						Statement st = conn.createStatement();


						String query = "SELECT  COURSE.coursename,COURSE.COURSENUM, REGISTEREDFOR.GRADE  FROM COURSE, REGISTEREDFOR WHERE COURSE.CRN= " + CURRENT_ITEM + " AND REGISTEREDFOR.STUDID ="+id+" AND REGISTEREDFOR.CRN = COURSE.CRN;";
						ResultSet rs = st.executeQuery(query);

						while (rs.next()) {

							String coursename = rs.getString("coursename");
							String coursen = rs.getString("COURSENUM");
							String grade = rs.getString("GRADE");
							textArea.setText("\n" + "CRN: " + CURRENT_ITEM + "\n" + "Course Number:" + coursen + "\n" + "Course Name: " + coursename + "\n" + "Grade: " + grade +"");


						}
					}catch (SQLException m){
						System.out.println(m.getMessage());
					}
					// string.
																	// Run an SQL query to get the rest of its
																	// information. put it in a variable.

					// ^ use .append(String s) to add course info to text area. start each append
					// with .append(" " + {STRING}); for formating.
					break;
				}
			}//implented by ethan marked
			if (e.getSource() == unregister)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// RUN SQL FUNCTION TO unregister COURSE for student

				boolean success = sql.student_Drop(conn, CURRENT_ITEM, id); // SQL boolean to check if course is sucessfully dropped

				if (success)
				{
					JOptionPane.showMessageDialog(null, "Class dropped.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Course not dropped.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		//implented by ethan marked with //e
		if (role == PROFESSOR_MODIFY_COURSE_GRADES)
		{
			Connection conn = UserLogin.New_Login("Professor", "ProfessorPassword");
			if (e.getSource() == msglist)

			//e
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:
					textArea.setText("");

					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-contains class name
					try{

						Statement st = conn.createStatement();
						String query1 = "select CRN, COURSENUM, SECTIONNUM, coursename from Course where PROFID = '" + id+"' AND CRN = "+CURRENT_ITEM +";";

						ResultSet rs1 = st.executeQuery(query1);
						while(rs1.next()){
							Statement st2 = conn.createStatement();
							String crn = rs1.getString("CRN");
							String coursenum = rs1.getString("COURSENUM");
							String sectionnum = rs1.getString("SECTIONNUM");
							String name = rs1.getString("coursename");
							textArea.setText("|CRN:"+crn+ " |COURSENUM:"+coursenum+ " |Section:"+sectionnum +" |Name:"+name);
							String query2 = "select STUDENT.studid, STUDENT.FNAME, STUDENT.LNAME, REGISTEREDFOR.GRADE from STUDENT, REGISTEREDFOR WHERE REGISTEREDFOR.STUDID =STUDENT.STUDID AND STUDENT.STUDID = (SELECT STUDID GRADE FROM REGISTEREDFOR WHERE CRN =" +crn+") AND REGISTEREDFOR.CRN IN (SELECT CRN FROM COURSE WHERE PROFID = "+id+");";
							ResultSet rs2 = st2.executeQuery(query2);
							textArea.setText("|Students Registered:");
							while(rs2.next()){
								String stud = rs2.getString("STUDID");


								String firstn= rs2.getString("FNAME");
								String lname= rs2.getString("LNAME");
								String grade = rs2.getString("GRADE");
								textArea.setText("|NAME:"+firstn+ " "+lname+" |Grade " +grade+" ID:"+ stud+" ");

							}




						}



					}catch (SQLException m){
						System.out.println(m.getMessage());
					}

					break;

				}
			}
			if (e.getSource() == assign_grade)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No class selected.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Object[] assign_student_grade =
				{ "Cancel", "Go" };

				JTextField student_ID = new JTextField();

				student_ID.setDocument(new JTextFieldLimit(20));

				Object[] student_modify_registration =
				{ "Student ID", student_ID };

				int selection_student_id = JOptionPane.showOptionDialog(null, student_modify_registration,
						"Enter student ID", JOptionPane.CANCEL_OPTION, 3, null, assign_student_grade, null);

				switch (selection_student_id)
				{
				case 0:
					break;
				case 1:

						Object[] grade =
						{ "Cancel", "Submit" };

						JTextField number_grade = new JTextField();

						number_grade.setDocument(new JTextFieldLimit(3));

						Object[] student_grade =
						{ "Grade", number_grade };

						int selection_student_grade = JOptionPane.showOptionDialog(null, student_grade,
								"Enter student grade", JOptionPane.CANCEL_OPTION, 3, null, grade, null);

						switch (selection_student_grade)
						{
						case 1:
							String stud= (student_ID.getText());
							String newgrade = (number_grade.getText());
							ProfessorSQL psql = new ProfessorSQL();
							boolean gup;
							try{


								String query = "update registeredfor SET GRADE = ? where STUDID = ? AND CRN = ?";
								PreparedStatement st = conn.prepareStatement(query);
								st.setString(1, newgrade);
								st.setString(2, stud);
								st.setString(3, CURRENT_ITEM);

								st.executeUpdate();
								gup = true;
							//end of e
							}catch (SQLException m){
								System.out.println("Message: "+m.getMessage());
								gup = false;
							}
							 // sql boolean to verify grade is updated

							if (gup)
							{
								JOptionPane.showMessageDialog(null, "Grade has been updated.", "SUCCESS",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							} else
							{
								JOptionPane.showMessageDialog(null, "An error occurred. Grade not updated", "ERROR",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}



				}
			}

		}
		//implented by ethan marked with //e
		if (role == PROFESSOR_MODIFY_COURSE_DESCRIPTION)
		{
			Connection conn = UserLogin.New_Login("Professor", "ProfessorPassword");


			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:
					try {
						textArea.setText("");
						CURRENT_ITEM = cb.getSelectedItem().toString();
						ProfessorSQL sql = new ProfessorSQL();// <-- contains course name.
						Statement st = conn.createStatement();

						String query = "Select * FROM COURSE WHERE CRN =" + CURRENT_ITEM + ";";

						ResultSet rs = st.executeQuery(query);


						if (rs.next()) {
							String des = rs.getString("DES");

							// <-- run SQL query to put course
							// description in this
							// variable
							// textArea.setText("");
							textArea.append(des);


							// run SQL function to update description with this variable
						}
						class_des = textArea.getText();
						break;
					}catch (SQLException m){
						System.out.println(m.getMessage());
					}

				}
			}
			//e continues
			if (e.getSource() == update)
			{

				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				ProfessorSQL sql = new ProfessorSQL();
				boolean gup;
				try{


				String query = "update course SET DES =  ?  WHERE CRN = ?";
				PreparedStatement st = conn.prepareStatement(query);
				st.setString(1, class_des);
				st.setString(2, CURRENT_ITEM);


				st.executeUpdate();
				gup = true;

			}catch (SQLException m){
				System.out.println("Message: "+m.getMessage());
				gup = false;
			}
			Boolean sucess = gup;
			//end of e
				if (sucess)
				{
					JOptionPane.showMessageDialog(null, "Description updated.", "SUCCESS",
							JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Description not updated.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		//implented by ethan marked with //e
		if (role == ADMIN_STUDENT_ADD_COURSE)
		{
			String student_id = SchoolAdministratorMainWindow.CURRENT_STUDENT;
			{
				 // "student_id" variable contains
				Connection conn = UserLogin.New_Login("SchoolAdmin", "SchoolPassword");																			// current student id, use
				// for
				// updates
				if (e.getSource() == msglist)
				{
					JComboBox cb = (JComboBox) e.getSource();
					int selection = cb.getSelectedIndex();
					switch (selection)
					{
					case 0:
						textArea.setText("");
						CURRENT_ITEM = null;
						break;

					default:

						CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "CURRENT_ITEM" contains the course name
						try {
							assert conn != null;
							Statement st = conn.createStatement();


							String query = "SELECT  coursename,COURSENUM, SECTIONNUM, PROFNAME, DES  FROM COURSE WHERE CRN= '" + CURRENT_ITEM + "';";
							ResultSet rs = st.executeQuery(query);
							textArea.setText("testing for more");
							while (rs.next()) {

								String coursename = rs.getString("COURSENAME");
								String coursen = rs.getString("COURSENUM");
								String secn = rs.getString("SECTIONNUM");
								String prof = rs.getString("PROFNAME");
								String desc = rs.getString("DES");
								textArea.setText("\n" + "CRN: "  + CURRENT_ITEM +  "\n" +"Course Number:"+coursen+ "-"+secn +"\n"+ "Course Name: " + coursename +"\n"+"Professor: "+prof+"\n"+"Description: "+desc);


							}

						}catch (SQLException m){
							System.out.println(m.getMessage());
						}
					}
				} //e continues
				if (e.getSource() == register)
				{
					if (CURRENT_ITEM == null)
					{
						JOptionPane.showMessageDialog(null, "No course selected", "ERROR",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					// RUN SQL FUNCTION TO ADD COURSE

					SchoolAdminSQL sql = new SchoolAdminSQL();

					boolean success = sql.AddStudToCourse(conn, student_id, CURRENT_ITEM);// SQL boolean to check if course is sucessfully added
					//end of e
					if (success)
					{
						JOptionPane.showMessageDialog(null, "Class added.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					} else
					{
						JOptionPane.showMessageDialog(null, "An error occured. Course not added.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		//implented by ethan marked with //e
		if (role == ADMIN_STUDENT_DROP_COURSE)
		{
			String student_id = SchoolAdministratorMainWindow.CURRENT_STUDENT;
			Connection conn = UserLogin.New_Login("SchoolAdmin", "SchoolPassword");
			StudentSQL sql = new StudentSQL();

			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
					case 0:
						textArea.setText("");
						CURRENT_ITEM = null;
						break;

					default:

						CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "CURRENT_ITEM" contains the course name as
						try {
							assert conn != null;
							Statement st = conn.createStatement();


							String query = "SELECT  COURSE.coursename,COURSE.COURSENUM, REGISTEREDFOR.GRADE  FROM COURSE, REGISTEREDFOR WHERE COURSE.CRN= " + CURRENT_ITEM + " AND REGISTEREDFOR.STUDID ="+id+" AND REGISTEREDFOR.CRN = COURSE.CRN;";
							ResultSet rs = st.executeQuery(query);

							while (rs.next()) {

								String coursename = rs.getString("coursename");
								String coursen = rs.getString("COURSENUM");
								String grade = rs.getString("GRADE");
								textArea.setText("\n" + "CRN: " + CURRENT_ITEM + "\n" + "Course Number:" + coursen + "\n" + "Course Name: " + coursename + "\n" + "Grade: " + grade +"");


							}
						}catch (SQLException m){
							System.out.println(m.getMessage());
						}
						// string.
						// Run an SQL query to get the rest of its
						// information. put it in a variable.

						// ^ use .append(String s) to add course info to text area. start each append
						// with .append(" " + {STRING}); for formating.
						break;
				}
			} //e continues
			if (e.getSource() == unregister)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// RUN SQL FUNCTION TO unregister COURSE for student
				StudentSQL sqls = new StudentSQL();
				boolean success = sqls.student_Drop(conn, CURRENT_ITEM, student_id); // SQL boolean to check if course is sucessfully dropped
				//end of e
				if (success)
				{
					JOptionPane.showMessageDialog(null, "Class dropped.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Course not dropped.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (role == DBA_MODIFY_STUDENT_GRADES)
		{
			String current_student_id = first_item; // <--this variable contains the student ID
			// USE FOR UPDATES

			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:
					textArea.setText("");

					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-contains class name
					// run SQL query to get class information and students current grades for the
					// class.
					// use textArea.append to add information to text area
					textArea.append(current_student_id + " " + CURRENT_ITEM);

					break;

				}
			} //implented by ethan marked with //e
			if (e.getSource() == assign_grade)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No class selected.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object[] grade =
				{ "Cancel", "Submit" };

				JTextField number_grade = new JTextField();

				number_grade.setDocument(new JTextFieldLimit(3));

				Object[] student_grade =
				{ "Grade", number_grade };

				int selection_student_grade = JOptionPane.showOptionDialog(null, student_grade, "Enter student grade",
						JOptionPane.CANCEL_OPTION, 3, null, grade, null);

				switch (selection_student_grade)
				{
				case 1:

					String ng = number_grade.getText(); // CONTAINS THE NUMBER GRADE

					boolean updated = true; // sql boolean to verify grade is updated
					//end of e
					if (updated)
					{
						JOptionPane.showMessageDialog(null, "Grade has been updated.", "SUCCESS",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else
					{
						JOptionPane.showMessageDialog(null, "An error occurred. Grade not updated", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

			}

		}

		if (role == DBA_STUDENT_ADD_COURSE)
		{
			 // "student_id" variable contains current student id, use
																// for
																// updates
			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:

					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "CURRENT_ITEM" contains the course name as
																	// string.
																	// Run an SQL query to get the rest of its
																	// information.
					textArea.setText(""); // <--keep this line, it resets the text area after each selection
					textArea.setText("\n" + " " + cb.getSelectedItem().toString() + " selected: " + "\n");
					// ^ use .append(String s) to add course info to text area. start each append
					// with .append(" " + {STRING}); for formating.
				}
			}

			if (e.getSource() == register)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// RUN SQL FUNCTION TO ADD COURSE

				boolean success = false; // SQL boolean to check if course is sucessfully added
				//end of e
				if (success)
				{
					JOptionPane.showMessageDialog(null, "Class added.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Course not added.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		if (role == DBA_STUDENT_DROP_COURSE)
		{
			//String current_student_id = first_item; // <--this variable contains the student ID
			// USE FOR UPDATES

			if (e.getSource() == msglist)
			{
				JComboBox cb = (JComboBox) e.getSource();
				int selection = cb.getSelectedIndex();
				switch (selection)
				{
				case 0:
					textArea.setText("");
					CURRENT_ITEM = null;
					break;

				default:

					CURRENT_ITEM = cb.getSelectedItem().toString(); // <-- "CURRENT_ITEM" contains the course name as
																	// string.
																	// Run an SQL query to get the rest of its
																	// information. put it in a variable.
					textArea.setText(""); // <--keep this line, it resets the text area after each selection
					textArea.setText("\n" + " " + cb.getSelectedItem().toString() + " selected: " + "\n");
					// ^ use .append(String s) to add course info to text area. start each append
					// with .append(" " + {STRING}); for formating.
					break;
				}
			}
			//elizandrews
			if (e.getSource() == unregister)
			{
				if (CURRENT_ITEM == null)
				{
					JOptionPane.showMessageDialog(null, "No course selected", "ERROR", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// RUN SQL FUNCTION TO unregister COURSE for student

				boolean success = false; // SQL boolean to check if course is sucessfully dropped

				if (success)
				{
					JOptionPane.showMessageDialog(null, "Class dropped.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "An error occured. Course not dropped.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
