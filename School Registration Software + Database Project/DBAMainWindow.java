package UniversitySystemGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DBAMainWindow
	//All code is written by Jared unless stated otherwise
{
	;
	private static JButton MODIFY_STUDENT_TABLES, MODIFY_PROFESSOR_TABLES, MODIFY_SCHOOL_ADMIN_TABLES,
			MODIFY_COURSE_TABLES, MODIFY_DEPARTMENT_TABLES, ENTER_TERMINAL;
	private String MESSAGE = "Please Select an Option";
	private String id;
	private String CURRENT_Student;

	//
	public DBAMainWindow(String id)
	{

		JPanel panel = new JPanel();
		panel.setLayout(null);

		ButtonListener listener = new ButtonListener();



		ENTER_TERMINAL = new JButton("Enter Terminal");
		ENTER_TERMINAL.setBounds(150, 280, 193, 25);
		ENTER_TERMINAL.setForeground(Color.WHITE);
		ENTER_TERMINAL.setBackground(Color.DARK_GRAY);
		ENTER_TERMINAL.addActionListener(listener);
		panel.add(ENTER_TERMINAL);

		JFrame frame = new JFrame();
		frame.setTitle("UNIVERSITY: DATABASE ADMINISTRATOR");
		frame.setLocation(new Point(500, 300));
		frame.add(panel);
		frame.setSize(new Dimension(500, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setAlwaysOnTop(true);
		frame.setVisible(true);
	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) throws IllegalArgumentException
		{
			Object source = event.getSource();
			DBASQL SQL = new DBASQL();

			Connection conn = UserLogin.New_Login("DataAdmin", "DataAdminPassword");

			if (source == MODIFY_STUDENT_TABLES)
			{
				///
				///
				///

				Object[] options1 =
						{ "Add Student", "Delete Student", "Modify Student Information", "Modify Student Registration",
								"Modify Student Grades" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify Student Tables",
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

					case 2:
						Object[] option_modify_student_info =
								{ "Cancel", "Go" };

						JTextField student_ID_modify = new JTextField();

						student_ID_modify.setDocument(new JTextFieldLimit(20));

						Object[] student_modify_info =
								{ "Student ID", student_ID_modify };

						int selection_modify_student_info = JOptionPane.showOptionDialog(null, student_modify_info,
								"Enter Student ID", JOptionPane.CANCEL_OPTION, 3, null, option_modify_student_info, null);
						// ================
						//
						// what needs to be done: sql function to get student information
						// and generate a window/box/frame to display this information and allow it to
						// be modified. along with submit and cancel buttons to finalize changes to the
						// database
						//
						// ================

						break;
					case 3:

						Object[] option_modify_student_registration =
								{ "Cancel", "Go" };

						JTextField student_ID_registration = new JTextField();

						student_ID_registration.setDocument(new JTextFieldLimit(20));

						Object[] student_modify_registration =
								{ "Student ID", student_ID_registration };

						int selection_modify_student_registration = JOptionPane.showOptionDialog(null,
								student_modify_registration, "Enter Student ID", JOptionPane.CANCEL_OPTION, 3, null,
								option_modify_student_registration, null);

						// ====================================
						//
						// what needs to be done: sql function to find students registered courses and
						// generate a window/box/frame to display the course with option to allow each
						// course to be dropped
						//
						// =====================================

						break;
					case 4:

						Object[] option_modify_student_grades =
								{ "Cancel", "Go" };

						JTextField student_ID_grades = new JTextField();

						student_ID_grades.setDocument(new JTextFieldLimit(20));

						Object[] student_modify_grades =
								{ "Student ID", student_ID_grades };

						int selection_modify_student_grades = JOptionPane.showOptionDialog(null, student_modify_grades,
								"Enter Student ID", JOptionPane.CANCEL_OPTION, 3, null, option_modify_student_grades, null);

						// =============================
						//
						// what needs to be done: sql function to get students courses and their grades
						// for each course. generate a window/box/frame with each course listed and a
						// box to modify the letter grade of each course. along with a submit button to
						// finalie the changes and send them to the database
						//
						// =================================

						break;

				}

				///
				///
				///
			}

			if (source == MODIFY_PROFESSOR_TABLES)
			{
				Object[] options =
						{ "Add Professor", "Delete Professor", "Modify Professor" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify Professor Tables",
						JOptionPane.CLOSED_OPTION, 3, null, options, null);

				switch (selection)
				{
					case 0:

						Object[] options_add_professor =
								{ "Cancel", "Add" };

						JTextField professor_first_name = new JTextField();
						JTextField professor_last_name = new JTextField();
						JTextField professor_faculty_ID = new JTextField();
						JTextField professor_department_ID = new JTextField();

						professor_first_name.setDocument(new JTextFieldLimit(20));
						professor_last_name.setDocument(new JTextFieldLimit(20));
						professor_faculty_ID.setDocument(new JTextFieldLimit(20));
						professor_department_ID.setDocument(new JTextFieldLimit(20));

						Object[] professor_fields_add =
								{ "First Name", professor_first_name, "Last Name", professor_last_name, "Faculty ID",
										professor_faculty_ID, "Department ID", professor_department_ID };

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

								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO add professor here - DONE - Liz
								//
								// ========================================================

								boolean success  = true; // BOOLEAN TO VERIFY professor IS ADDED/DELETED

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

						JTextField professor_faculty_ID_delete = new JTextField();

						professor_faculty_ID_delete.setDocument(new JTextFieldLimit(20));

						Object[] professor_fields_delete =
								{ "Faculty ID", professor_faculty_ID_delete };

						int selection_delete_professor = JOptionPane.showOptionDialog(null, professor_fields_delete,
								"Enter Professor ID", JOptionPane.CANCEL_OPTION, 3, null, options_delete_professor, null);

						switch (selection_delete_professor)
						{
							case 1:
								String pfID = professor_faculty_ID_delete.getText();

								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO delete professor here - DONE - Liz
								//
								// ========================================================

								boolean success = true; // BOOLEAN TO VERIFY professor IS ADDED/DELETED

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

					case 2:

						Object[] options_modify_professor =
								{ "Cancel", "Go" };

						JTextField professor_faculty_ID_modify = new JTextField();

						professor_faculty_ID_modify.setDocument(new JTextFieldLimit(20));

						Object[] professor_fields_modify =
								{ "Faculty ID", professor_faculty_ID_modify };

						int selection_modify_professor = JOptionPane.showOptionDialog(null, professor_fields_modify,
								"Enter Professor ID", JOptionPane.CANCEL_OPTION, 3, null, options_modify_professor, null);

						/*
						 *
						 *
						 * what needs to be done: window generated to display professors information and
						 * allow modifcation. along with cancel/submit buttons in to finalize the
						 * changes in the db
						 *
						 *
						 */

						break;

				}

			}
			if (source == MODIFY_SCHOOL_ADMIN_TABLES)
			{
				Object[] options1 =
						{ "Add Administrator", "Delete Admnistrator", "Modify Administrator" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify School Administrator Tables",
						JOptionPane.CLOSED_OPTION, 3, null, options1, null);

			}
			if (source == MODIFY_COURSE_TABLES)
			{
				Object[] options =
						{ "Add Course", "Delete Course", "Modify Course" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify Course Tables",
						JOptionPane.CLOSED_OPTION, 3, null, options, null);

				switch (selection)
				{
					case 0:

						Object[] options_add_course =
								{ "Cancel", "Add" };

						JTextField course_department = new JTextField();
						JTextField course_ID = new JTextField();
						JTextField course_name = new JTextField();
						JTextField course_description = new JTextField();

						course_department.setDocument(new JTextFieldLimit(20));
						course_ID.setDocument(new JTextFieldLimit(20));
						course_name.setDocument(new JTextFieldLimit(20));
						course_description.setDocument(new JTextFieldLimit(50));

						Object[] course_fields_add =
								{ "Course Department", course_department, "Course ID", course_ID, "Course Name", course_name,
										"Course Desciption", course_description };

						int selection_add_course = JOptionPane.showOptionDialog(null, course_fields_add,
								"Enter Course Information", JOptionPane.CANCEL_OPTION, 3, null, options_add_course, null);

						switch (selection_add_course)
						{
							case 1:
								String cid = course_department.getText();
								String cdept = course_ID.getText();
								String cname = course_name.getText();
								String cdesc = course_description.getText();

								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO add course here - DONE - Liz
								//
								// ========================================================
								SchoolAdminSQL sql = new SchoolAdminSQL();
								boolean success = true; // SQL BOOLEAN TO VERIFY if IS ADDED/DELETED

								if (success)
								{
									JOptionPane.showMessageDialog(null, "Course added.", "Success",
											JOptionPane.WARNING_MESSAGE);
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
								{ "Course ID", course_ID_delete, "Course Department", course_department_delete };

						int selection_delete_course = JOptionPane.showOptionDialog(null, course_fields_delete,
								"Enter Course Information", JOptionPane.CANCEL_OPTION, 3, null, options_delete_course,
								null);

						switch (selection_delete_course)
						{
							case 1:
								String cid = course_ID_delete.getText();
								String cdept = course_department_delete.getText();

								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO delete course here - DONE - Liz
								//
								// ========================================================

								boolean success = true; // SQL BOOLEAN TO VERIFY if IS ADDED/DELETED

								if (success)
								{
									JOptionPane.showMessageDialog(null, "Course deleted.", "Success",
											JOptionPane.WARNING_MESSAGE);
								} else
								{
									JOptionPane.showMessageDialog(null, "An error occured. Course not deleted.", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								}
						}

						break;

					case 2:
						Object[] options_modify_course =
								{ "Cancel", "Go" };

						JTextField course_ID_modify = new JTextField();
						JTextField course_department_modify = new JTextField();

						course_ID_modify.setDocument(new JTextFieldLimit(20));
						course_department_modify.setDocument(new JTextFieldLimit(20));

						Object[] course_fields_modify =
								{ "Course ID", course_ID_modify, "Course Department", course_department_modify };

						int selection_modify_course = JOptionPane.showOptionDialog(null, course_fields_modify,
								"Enter Course Information", JOptionPane.CANCEL_OPTION, 3, null, options_modify_course,
								null);

						/*
						 *
						 *
						 * what needs to be done: SQL function to collect course information and display
						 * it in a window/box/frame and allow modification to information. along with a
						 * cancel and submit button to finalize changes to database
						 *
						 *
						 */

						break;

				}

			}
			if (source == MODIFY_DEPARTMENT_TABLES)
			{
				Object[] options1 =
						{ "Add Department", "Delete Department", "Modify Department" };

				int selection = JOptionPane.showOptionDialog(null, MESSAGE, "Modify Department Tables",
						JOptionPane.CLOSED_OPTION, 3, null, options1, null);

				switch (selection)
				{
					case 0:
						Object[] options_add_department =
								{ "Cancel", "Add" };

						JTextField department_ID_add = new JTextField();
						JTextField department_name = new JTextField();
						department_ID_add.setDocument(new JTextFieldLimit(20));
						department_name.setDocument(new JTextFieldLimit(20));
						Object[] department_fields_add =
								{ "Department ID", department_ID_add, "Department Name", department_name }; // "Course Department", course_department_delete };

						int selection_add_department = JOptionPane.showOptionDialog(null, department_fields_add,
								"Enter Department Information", JOptionPane.CANCEL_OPTION, 3, null, options_add_department,
								null);

						switch (selection_add_department)
						{
							case 1:
								String deptid = department_ID_add.getText();
								String deptn = department_name.getText();
								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO add department here - DONE - Liz
								//
								// ========================================================
								DBASQL sql = new DBASQL();
								boolean success = sql.AddDept(conn, deptid, deptn); // SQL BOOLEAN TO VERIFY if IS ADDED/DELETED

								if (success)
								{
									JOptionPane.showMessageDialog(null, "Department added.", "Success",
											JOptionPane.WARNING_MESSAGE);
								} else
								{
									JOptionPane.showMessageDialog(null, "An error occured. Department not added.", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								}
						}
						break;

					case 1:
						Object[] options_delete_department =
								{ "Cancel", "Delete" };

						JTextField department_ID_delete = new JTextField();

						department_ID_delete.setDocument(new JTextFieldLimit(20));

						Object[] department_fields_delete =
								{ "Department ID", department_ID_delete };

						int selection_delete_department = JOptionPane.showOptionDialog(null, department_fields_delete,
								"Enter Department Information", JOptionPane.CANCEL_OPTION, 3, null,
								options_delete_department, null);

						switch (selection_delete_department)
						{
							case 1:
								String deptid = department_ID_delete.getText();

								// ========================================================
								//
								// what needs to be done: SQL FUNCTION TO delete department here - DONE - Liz
								//
								// ========================================================
								DBASQL sql = new DBASQL();
								boolean success = sql.DelDept(conn, deptid); // SQL BOOLEAN TO VERIFY if IS ADDED/DELETED

								if (success)
								{
									JOptionPane.showMessageDialog(null, "Department deleted.", "Success",
											JOptionPane.WARNING_MESSAGE);
								} else
								{
									JOptionPane.showMessageDialog(null, "An error occured. Department not deleted.", "ERROR",
											JOptionPane.ERROR_MESSAGE);
								}
						}

						break;

					case 2:
						Object[] options_modify_department =
								{ "Cancel", "Go" };

						JTextField department_ID_modify = new JTextField();
						// JTextField course_department_modify = new JTextField();

						department_ID_modify.setDocument(new JTextFieldLimit(20));
						// course_department_modify.setDocument(new JTextFieldLimit(20));

						Object[] department_fields_modify =
								{ "Department ID", department_ID_modify };// "Course Department", course_department_modify };

						int selection_modify_department = JOptionPane.showOptionDialog(null, department_fields_modify,
								"Enter Department Information", JOptionPane.CANCEL_OPTION, 3, null,
								options_modify_department, null);
						/*
						 *
						 *
						 * what needs to be done: SQL function to collect department information and
						 * display it in a window/box/frame and allow modification to information. along
						 * with a cancel and submit button to finalize changes to database
						 *
						 *
						 */

						break;
				}

			}
			if (source == ENTER_TERMINAL)
			{
				// ?

				CustomOutputStream.main("DBA: TERMINAL", true);
				System.out.println("hellow");

			}
		}
	}

	public static void main(String args[])
	{
		new DBAMainWindow(args[0]);
	}
}