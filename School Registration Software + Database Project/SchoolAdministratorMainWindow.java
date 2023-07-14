package UniversitySystemGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//AUTHOR: JARED KEKLAK (UNLESS ANNOTATED)
public class SchoolAdministratorMainWindow
{

	private static JButton MODIFY_STUDENT_REGISTRATION, ADD_OR_REMOVE_A_COURSE, ADD_OR_REMOVE_PROFESSOR,
			ADD_OR_REMOVE_A_STUDENT, SEE_STUDENT_RECORD;

	private String MESSAGE = "Please Select an Option";
	private String id;

	public static String CURRENT_STUDENT;

	public SchoolAdministratorMainWindow(String id)
	{

		this.id = id;

		JPanel panel = new JPanel();
		panel.setLayout(null);

		ButtonListener listener = new ButtonListener();

		ADD_OR_REMOVE_A_COURSE = new JButton("Add or Remove a Course");
		ADD_OR_REMOVE_A_COURSE.setBounds(150, 130, 193, 25);
		ADD_OR_REMOVE_A_COURSE.setForeground(Color.WHITE);
		ADD_OR_REMOVE_A_COURSE.setBackground(Color.DARK_GRAY);
		ADD_OR_REMOVE_A_COURSE.addActionListener(listener);
		panel.add(ADD_OR_REMOVE_A_COURSE);

		ADD_OR_REMOVE_PROFESSOR = new JButton("Add or Remove a Professor");
		ADD_OR_REMOVE_PROFESSOR.setBounds(150, 160, 193, 25);
		ADD_OR_REMOVE_PROFESSOR.setForeground(Color.WHITE);
		ADD_OR_REMOVE_PROFESSOR.setBackground(Color.DARK_GRAY);
		ADD_OR_REMOVE_PROFESSOR.addActionListener(listener);
		panel.add(ADD_OR_REMOVE_PROFESSOR);

		MODIFY_STUDENT_REGISTRATION = new JButton("Modify Student Registration");
		MODIFY_STUDENT_REGISTRATION.setBounds(150, 190, 193, 25);
		MODIFY_STUDENT_REGISTRATION.setForeground(Color.WHITE);
		MODIFY_STUDENT_REGISTRATION.setBackground(Color.DARK_GRAY);
		MODIFY_STUDENT_REGISTRATION.addActionListener(listener);
		panel.add(MODIFY_STUDENT_REGISTRATION);

		ADD_OR_REMOVE_A_STUDENT = new JButton("Add or Remove a Student");
		ADD_OR_REMOVE_A_STUDENT.setBounds(150, 220, 193, 25);
		ADD_OR_REMOVE_A_STUDENT.setForeground(Color.WHITE);
		ADD_OR_REMOVE_A_STUDENT.setBackground(Color.DARK_GRAY);
		ADD_OR_REMOVE_A_STUDENT.addActionListener(listener);
		panel.add(ADD_OR_REMOVE_A_STUDENT);

		SEE_STUDENT_RECORD = new JButton("See a Student Record");
		SEE_STUDENT_RECORD.setBounds(150, 250, 193, 25);
		SEE_STUDENT_RECORD.setForeground(Color.WHITE);
		SEE_STUDENT_RECORD.setBackground(Color.DARK_GRAY);
		SEE_STUDENT_RECORD.addActionListener(listener);
		panel.add(SEE_STUDENT_RECORD);

		JFrame frame = new JFrame();
		frame.setTitle("UNIVERSITY: ADMINISTRATOR");
		frame.setLocation(new Point(500, 300));
		frame.add(panel);
		frame.setSize(new Dimension(500, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setAlwaysOnTop(true);
		frame.setVisible(true);

	}

	public class ButtonListener implements ActionListener
	{
		Connection conn;
		public void actionPerformed(ActionEvent event) throws IllegalArgumentException
		{
			Object source = event.getSource();
			SchoolAdminSQL SQL = new SchoolAdminSQL();
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/sys", "SchoolAdmin", "SchoolPassword");
			}catch (SQLException e){
				System.out.println(e.getMessage());
			}
			//sql integrated by ethan
			if (source == ADD_OR_REMOVE_A_COURSE)
			{
				Object[] options1 =
				{ "Add course", "Remove course" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify offered courses",
						JOptionPane.DEFAULT_OPTION, 3, null, options1, null);

				switch (selection)
				{
				case 0:

					Object[] options_add_course =
					{ "Cancel", "Add" };

					JTextField course_department = new JTextField();
					JTextField course_name = new JTextField();
					JTextField course_description = new JTextField();
					JTextField course_professor = new JTextField();
					JTextField course_professorID = new JTextField();
					JTextField course_semester = new JTextField();
					JTextField course_year = new JTextField();
					JTextField course_section = new JTextField();
					JTextField course_num = new JTextField();
					course_department.setDocument(new JTextFieldLimit(20));
					course_num.setDocument(new JTextFieldLimit(20));
					course_name.setDocument(new JTextFieldLimit(20));
					course_description.setDocument(new JTextFieldLimit(50));
					course_professor.setDocument(new JTextFieldLimit(20));
					course_professorID.setDocument(new JTextFieldLimit(20));
					course_semester.setDocument(new JTextFieldLimit(20));
					course_year.setDocument(new JTextFieldLimit(20));
					course_section.setDocument(new JTextFieldLimit(20));

					Object[] course_fields_add =
							{ " Course Name", course_name, "Course NUM", course_num,
									"Course Desciption", course_description , "Professor Name", course_professor, "Semester", course_semester, "Year", course_year, "Section", course_section};

					int selection_add_course = JOptionPane.showOptionDialog(null, course_fields_add,
							"Enter Course Information", JOptionPane.CANCEL_OPTION, 3, null, options_add_course, null);

					switch (selection_add_course)
					{
					case 1:
						String adminid = id;
						String cnum = course_num.getText();
						String cname = course_name.getText();
						String cdesc = course_description.getText();
						String pname = course_professor.getText();
						String semes = course_semester.getText();
						String year = course_year.getText();
						String secn = course_section.getText();

						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO add course here
						//
						// ========================================================

						boolean success = SQL.AddCourse(conn, cnum, cname, cdesc, pname, semes, year, secn);  // BOOLEAN TO VERIFY COURSE IS ADDED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Class added.", "Success", JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Course not added.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}

					break;

				case 1:
					Object[] options_delete_course =
					{ "Cancel", "Delete" };

					JTextField course_ID_delete = new JTextField();
					JTextField course_department_delete = new JTextField();

					course_ID_delete.setDocument(new JTextFieldLimit(20));
					course_department_delete.setDocument(new JTextFieldLimit(20));

					Object[] course_fields_delete =
					{ "Course CRN", course_ID_delete, "Course Department", course_department_delete };

					int selection_delete_course = JOptionPane.showOptionDialog(null, course_fields_delete,
							"Enter course information", JOptionPane.CANCEL_OPTION, 3, null, options_delete_course,
							null);

					switch (selection_delete_course)
					{
					case 1:
						String CRN = course_ID_delete.getText();
						String adminid = id;

						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO delete course here
						//
						// ========================================================

						boolean success = SQL.DeleteCourse(conn, CRN, adminid); // BOOLEAN TO VERIFY COURSE IS ADDED/DELETED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Class deleted.", "Success",
									JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Course not deleted.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}

					break;

				}
			}
			//sql integrated by ethan
			if (source == ADD_OR_REMOVE_PROFESSOR)
			{
				Object[] options1 =
				{ "Add Professor", "Remove Professor" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify faculty",
						JOptionPane.DEFAULT_OPTION, 3, null, options1, null);

				switch (selection)
				{
				case 0:

					Object[] options_add_professor =
							{ "Cancel", "Add" };

					JTextField professor_first_name = new JTextField();
					JTextField professor_last_name = new JTextField();
					JTextField professor_faculty_ID = new JTextField();
					JTextField professor_department_ID = new JTextField();
					JTextField professor_password = new JTextField();

					professor_first_name.setDocument(new JTextFieldLimit(20));
					professor_last_name.setDocument(new JTextFieldLimit(20));
					professor_faculty_ID.setDocument(new JTextFieldLimit(20));
					professor_department_ID.setDocument(new JTextFieldLimit(20));
					professor_password.setDocument(new JTextFieldLimit(20));


					Object[] professor_fields_add =
							{ "First Name", professor_first_name, "Last Name", professor_last_name, "Professor Name",
									professor_faculty_ID, "Department ID", professor_department_ID, "Password", professor_password };

					int selection_add_professor = JOptionPane.showOptionDialog(null, professor_fields_add,
							"Enter Professor Information", JOptionPane.CANCEL_OPTION, 3, null, options_add_professor,
							null);


					switch (selection_add_professor)
					{
					case 1:
						String pfname = professor_first_name.getText();
						String plname = professor_last_name.getText();
						String pfID = professor_faculty_ID.getText();
						String pdeptID = professor_department_ID.getText();
						String pass = professor_password.getText();


						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO add professor here
						//
						// ========================================================

						boolean success = SQL.AddProfessor(conn, pfname, plname, pfID, pdeptID, pass); // BOOLEAN TO VERIFY professor IS ADDED/DELETED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Professor added.", "Success",
									JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Professor not added.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}

					break;

				case 1:

					Object[] options_delete_professor =
					{ "Cancel", "Delete" };

					JTextField professor_faculty_first_delete = new JTextField();
					JTextField professor_faculty_last_delete = new JTextField();
					JTextField professor_faculty_depart_delete = new JTextField();
					professor_faculty_first_delete.setDocument(new JTextFieldLimit(20));
					professor_faculty_last_delete.setDocument(new JTextFieldLimit(20));
					professor_faculty_depart_delete.setDocument(new JTextFieldLimit(20));

					Object[] professor_fields_delete =
							{ "First Name: ", professor_faculty_first_delete, "Last Name: ", professor_faculty_last_delete, "Department: ", professor_faculty_depart_delete };


					int selection_delete_professor = JOptionPane.showOptionDialog(null, professor_fields_delete,
							"Enter Professor ID", JOptionPane.CANCEL_OPTION, 3, null, options_delete_professor, null);

					switch (selection_delete_professor)
					{
					case 1:
						String pfID = professor_faculty_first_delete.getText();
						String plnD = professor_faculty_last_delete.getText();
						String pdepd = professor_faculty_depart_delete.getText();
						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO delete professor here
						//
						// ========================================================

						boolean success = SQL.DeleteProfessor(conn,pfID, plnD, pdepd); // BOOLEAN TO VERIFY professor IS ADDED/DELETED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Professor deleted.", "Success",
									JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Professor not deleted.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}

					break;

				}
			}
			//sql integrated by ethan
			if (source == MODIFY_STUDENT_REGISTRATION)
			{

				Object[] options_modify_student_registration =
				{ "Cancel", "Go" };

				JTextField student_ID_modify_registration = new JTextField();

				student_ID_modify_registration.setDocument(new JTextFieldLimit(20));

				Object[] student_modify_registration =
				{ "Student ID", student_ID_modify_registration };

				int selection_modify_student_registration = JOptionPane.showOptionDialog(null,
						student_modify_registration, "Enter student ID", JOptionPane.CANCEL_OPTION, 3, null,
						options_modify_student_registration, null);

				switch (selection_modify_student_registration)
				{
				case 0:
					break;
				case 1:

					String stud_id = student_ID_modify_registration.getText();
					CURRENT_STUDENT = stud_id;

					SchoolAdminSQL sql = new SchoolAdminSQL();
					boolean is_student = sql.ceckstud(conn, CURRENT_STUDENT);// sql boolean to determin if student is found or not

					if (is_student)
					{
						Object[] options =
						{ "Add a Course", "Drop a Course" };
						int stud_selection = JOptionPane.showOptionDialog(null, MESSAGE,
								"STUDENT " + stud_id + " Add or Drop a course", JOptionPane.CLOSED_OPTION, 3, null,
								options, null);

						switch (stud_selection)
						{
						case 0:
							Object[] department_options =
							{ "Cancel", "Go" };

							JTextField department = new JTextField();
							department.setDocument(new JTextFieldLimit(20));

							Object[] dept_fields =
							{ "Department ID", department };

							int department_input = JOptionPane.showOptionDialog(null, dept_fields,
									"Enter Department Information", JOptionPane.CANCEL_OPTION, 3, null,
									department_options, null);

							switch (department_input)
							{
							case 0:
								break;
							case 1:
								String dept_code = department.getText();

								boolean is_dept = true; // ATTENTION TODO <-- sql boolean to check if code is valid
														// department

								if (is_dept)
								{
									List<String> class_list = new ArrayList<String>();
									class_list.add(dept_code);

									try{
										Statement st = conn.createStatement();


										String query = "SELECT  CRN FROM COURSE WHERE DEPTKEY = '"+dept_code+"';";
										ResultSet rs = st.executeQuery(query);

										while(rs.next()){

											String crn = rs.getString("CRN");


											class_list.add(crn);
										}

									} catch (SQLException e) {
										throw new RuntimeException(e);
									}

									ComboBox.main("STUDENT: MODIFY COURSE REGISTRATION", class_list,
											ComboBox.ADMIN_STUDENT_ADD_COURSE, id);
								} else
								{
									JOptionPane.showMessageDialog(null, "An error occured. Department not found.", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								}
							}

							break;
						case 1:
							List<String> classes = new ArrayList<String>();
							classes.add("Select A Course");
							try{
								Statement st = conn.createStatement();


								String query = "SELECT * FROM registeredfor WHERE STUDID = "+id+";";
								ResultSet rs = st.executeQuery(query);

								while(rs.next()){

									String crn = rs.getString("CRN");


									classes.add(crn);
								}

							} catch (SQLException e) {
								throw new RuntimeException(e);
							}
							ComboBox.main("ADMINISTRATOR: MODIFY STUDENT REGISTRATION", classes,
									ComboBox.ADMIN_STUDENT_DROP_COURSE , id);

						}
					} else
					{
						JOptionPane.showMessageDialog(null, "An error occured. Student not found.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			}
			//sql integrated by ethan
			if (source == ADD_OR_REMOVE_A_STUDENT)
			{
				Object[] options1 =
				{ "Add Student", "Remove Student" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Add or Remove student",
						JOptionPane.CLOSED_OPTION, 3, null, options1, null);

				switch (selection)
				{
				case 0:

					Object[] options_add_student =
							{ "Cancel", "Add" };

					JTextField student_first_name = new JTextField();
					JTextField student_last_name = new JTextField();
					JTextField student_ID = new JTextField();
					JTextField student_classification = new JTextField();

					student_first_name.setDocument(new JTextFieldLimit(20));
					student_last_name.setDocument(new JTextFieldLimit(20));
					student_ID.setDocument(new JTextFieldLimit(20));
					student_classification.setDocument(new JTextFieldLimit(20));

					Object[] student_fields_add =
							{ "First Name", student_first_name, "Last Name", student_last_name, "Password", student_ID,
									"Major", student_classification };

					int selection_add_student = JOptionPane.showOptionDialog(null, student_fields_add,
							"Enter student Information", JOptionPane.CANCEL_OPTION, 3, null, options_add_student, null);

					switch (selection_add_student)
					{
					case 1:
						String sfname = student_first_name.getText();
						String slname = student_last_name.getText();
						String pass = student_ID.getText();
						String major = student_classification.getText();

						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO add student here
						//
						// ========================================================

						boolean success = SQL.AddStudent(conn, sfname, slname, major, pass); // SQL BOOLEAN TO VERIFY student IS ADDED/DELETED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Student added.", "Success",
									JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Student not added.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}

					break;

				case 1:

					Object[] options_delete_student =
					{ "Cancel", "Delete" };

					JTextField student_first_delete = new JTextField();
					JTextField student_last_delete = new JTextField();
					JTextField student_major_delete = new JTextField();

					student_first_delete.setDocument(new JTextFieldLimit(20));
					student_last_delete.setDocument(new JTextFieldLimit(20));
					student_major_delete.setDocument(new JTextFieldLimit(20));

					Object[] student_fields_delete =
					{ "Student First Name", student_first_delete, "Student Last Name: ", student_last_delete, "Student Major; ", student_major_delete };

					int selection_delete_student = JOptionPane.showOptionDialog(null, student_fields_delete,
							"Enter student ID", JOptionPane.CANCEL_OPTION, 3, null, options_delete_student, null);

					switch (selection_delete_student)
					{
					case 1:
						String sfirst = student_first_delete.getText();
						String slast = student_last_delete.getText();
						String smajor = student_major_delete.getText();
						// ========================================================
						//
						// what needs to be done: SQL FUNCTION TO delete zstudent here
						//
						// ========================================================

						boolean success = SQL.DeleteStudent(conn,sfirst, slast, smajor );  // SQL BOOLEAN TO VERIFY student IS ADDED/DELETED

						if (success)
						{
							JOptionPane.showMessageDialog(null, "Student deleted.", "Success",
									JOptionPane.WARNING_MESSAGE);
						} else
						{
							JOptionPane.showMessageDialog(null, "An error occured. Student not deleted.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
						break;
					}

					break;

				}
			}
			//sql integrated by ethan
			if (source == SEE_STUDENT_RECORD)
			{

				Object[] options_see_student_record =
				{ "Cancel", "Go" };

				JTextField student_ID_see_student_record = new JTextField();

				student_ID_see_student_record.setDocument(new JTextFieldLimit(20));

				Object[] student_modify_registration =
				{ "Student ID", student_ID_see_student_record };

				int selection_see_student_record = JOptionPane.showOptionDialog(null, student_modify_registration,
						"Enter Student ID", JOptionPane.CANCEL_OPTION, 3, null, options_see_student_record, null);

				switch (selection_see_student_record)
				{
				case 0:
				{
					break;
				}
				case 1:
				{
					String stud_id = student_ID_see_student_record.getText();

					CustomOutputStream.main("ADMINISTRATOR: SEE STUDENT RECORD", false);

					/*
					 * SQL FUNCTION TO GET STUDENT RECORD FROM stud_id HERE IMPORTANT: information
					 * designed to be displayed using System.print... Console Output it directed to
					 * new frame created.
					 */

					System.out
							.println("\n" + "============ Displaying record for student: " + stud_id + " ============");
					try {
						SQL.ViewStudRec(conn, stud_id);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				}

			}
		}
	}

	public static void main(String args[])
	{
		new SchoolAdministratorMainWindow(args[0]);
	}
}
