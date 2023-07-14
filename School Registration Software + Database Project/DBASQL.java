package UniversitySystemGUI;

import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

//all code written by caroline + Liz
public class DBASQL {
    // Raw SQL Command Enter
    public void RawSql(Connection conn, Scanner scan, String COMMAND) throws SQLException, IOException {
        try {
            Statement st = conn.createStatement();
            // Enter SQL Command
            // String q = kdjflaksdf;
            String query = COMMAND;
            st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }


    // ********************** STUDENT **********************
    //Add a student
    //Liz+Ethan edit: Adding student to student table & users table
    public boolean AddStudent(Connection conn, String F_NAME, String L_NAME, String MAJOR, String Password) {
        SchoolAdminSQL sql = new SchoolAdminSQL();
        return sql.AddStudent(conn, F_NAME, L_NAME, MAJOR, Password);
    }

    //Delete a student - Caroline Original, Liz & Ethan New
    public boolean DeleteStudent(Connection conn, String Fname, String Lname, String Major) {


        SchoolAdminSQL sql = new SchoolAdminSQL();
        return sql.DeleteStudent(conn, Fname, Lname, Major);

    }

    //Update student info - Caroline original, Liz & Ethan Update

    // This will get and display information when "modify student registration" is selected


    //Add Student to Course -


    // Update grade

    // ******************** PROFESSOR *********************
    // Add professor
    //Liz: Adding professor to professor table & users table

    public boolean AddProfessor(Connection conn, String PROF_NAME, String DEPT_KEY, String Password) {
        try {
            Statement st = conn.createStatement();

            //checks for prof first
            String query = "SELECT * FROM PROFESSOR WHERE PROFNAME = '" + PROF_NAME + "' AND DEPTKEY = '" + DEPT_KEY + "';";

            ResultSet rs1 = st.executeQuery(query);

            if (!rs1.next()) {

                String addprof = "INSERT INTO PROFESSOR(PROFNAME, DEPTKEY) VALUES ('" + PROF_NAME + "', '" + DEPT_KEY + "');";

                st.executeUpdate(addprof);

            } else {
                //uncomment if boolean instead of void
                return false;
            }

            String query2 = "SELECT PROFID FROM PROFESSOR WHERE PROFNAME= '" + PROF_NAME + "' AND DEPTKEY = '" + DEPT_KEY + "';";


            ResultSet rs2 = st.executeQuery(query2);

            if (rs2.next()) {
                String userid = rs2.getString("PROFID");
                String adduse = "INSERT INTO USERS(USERID, PASSWORD, ROLE) VALUES (" + userid + ", '" + Password + "', 'Professor');";


                st.executeUpdate(adduse);
                //uncomment if returning true.
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    //Delete professor - Original Caroline, Updated version Liz & Ethan
    public boolean DeleteProfessor(Connection conn, String Fname, String Lname, String Depart) {
        try {
            Statement st = conn.createStatement();
            String stud = "SELECT PROFID FROM PROFESSOR WHERE FNAME = '" + Fname + "' AND LNAME='" + Lname + "' AND DEPTKEY='" + Depart + "';";
            ResultSet rs1 = st.executeQuery(stud);
            while (rs1.next()) {
                String profid = rs1.getString("PROFID");
                String query = "select * from course where PROFID =" + profid + ";";
                ResultSet rs2 = st.executeQuery(query);
                if (rs2.next()) {
                    String crn = rs2.getString("CRN");
                    ResultSet rs3 = st.executeQuery("Select CRN FROM REGISTEREDFOR WHERE CRN =" + crn + ";");

                    if (rs3.next()) {
                        String dele_reg = "Delete FROM REGISTEREDFOR WHERE CRN =" + crn + ";";
                        st.executeUpdate(dele_reg);
                        String dele_course = "DELETE FROM COURSE WHERE PROFID =" + profid + ";";
                        st.executeUpdate(dele_course);
                        String del_prof = "DELETE FROM Professor WHERE STUDID =" + profid + ";";
                        st.executeUpdate(del_prof);
                        String dele_pruser = "DELETE FROM USERS WHERE PROFID = " + profid + ";";
                        st.executeUpdate(dele_pruser);
                        return true;
                    } else {
                        String dele_course = "DELETE FROM COURSE WHERE PROFID =" + profid + ";";
                        st.executeUpdate(dele_course);
                        String del_student = "DELETE FROM PROFESSOR WHERE PROFID =" + profid + ";";
                        st.executeUpdate(del_student);
                        String dele_pruser = "DELETE FROM USERS WHERE PROFID = " + profid + ";";
                        st.executeUpdate(dele_pruser);
                        return true;
                    }


                } else {
                    String del_prof3 = "DELETE FROM PROFESSOR WHERE PROFID =" + profid + ";";
                    st.executeUpdate(del_prof3);
                    String dele_pruser = "DELETE FROM USERS WHERE PROFID = " + profid + ";";
                    st.executeUpdate(dele_pruser);
                    return true;
                }


            }


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return false;
    }

    //Edit Professor Information - Original Caroline, Update Liz & Ethan


// Delete student from course


    // ************************* DEPARTMENT ******************************
// Add a department
    public boolean AddDept(Connection conn, String DEPT_KEY, String DEPT_NAME) {
        try {
            Statement st = conn.createStatement();
            String insert_query = "INSERT INTO DEPARTMENT(DEPTKEY, DEPTNAME) VALUES ('" + DEPT_KEY + "', '" + DEPT_NAME + "');";
            st.executeUpdate(insert_query);
            return true;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

    // Delete a department
    public boolean DelDept(Connection conn, String DEPT_KEY) {
        try {
            Statement st = conn.createStatement();
            // Prompt for dept key
            String delete_query = "DELETE * FROM DEPARTMENT WHERE DEPTKEY = '" + DEPT_KEY + "';";
            st.executeUpdate(delete_query);
            return true;
            // Check and see if values are still in department table


        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }

// Modify department


// ********************** ADMINISTRATOR ****************************
// Add administrator
//Liz: Adding school administrator to schooladmin table & users table

    public boolean AddSchAdmin(Connection conn, String F_NAME, String L_NAME, String DEPT_KEY, String Password) {
        try {
            Statement st = conn.createStatement();

            //checks for schadmin first
            String query = "SELECT * FROM SCHADMIN WHERE FNAME = '" + F_NAME + "' AND LNAME = '" + L_NAME + "' AND DEPTKEY = '" + DEPT_KEY + "';";

            ResultSet rs1 = st.executeQuery(query);

            if (!rs1.next()) {

                String addschad = "INSERT INTO SCHADMIN(FNAME, LNAME, DEPTKEY) VALUES ('" + F_NAME + "', '" + L_NAME + "', '" + DEPT_KEY + "');";

                st.executeUpdate(addschad);

            } else {
                //uncomment if boolean instead of void
                return false;
            }

            String query2 = "SELECT SCHADID FROM SCHOOLADMIN WHERE FNAME= '" + F_NAME + "' AND LNAME = '" + L_NAME + "' AND DEPTKEY = '" + DEPT_KEY + "';";


            ResultSet rs2 = st.executeQuery(query2);

            if (rs2.next()) {
                String userid = rs2.getString("SCHADID");
                String adduse = "INSERT INTO USERS(USERID, PASSWORD, ROLE) VALUES (" + userid + ", '" + Password + "', 'SchoolAdmin');";


                st.executeUpdate(adduse);
                //uncomment if returning true.
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    // Delete administrator - Updated 12/3 by Liz
    public boolean DelAdmin(Connection conn, String ADMIN_ID) {
        try {
            Statement st = conn.createStatement();

            String delete_query = "DELETE * FROM SCHADMIN WHERE SCHADID = '" + ADMIN_ID + "';";
            st.executeUpdate(delete_query);
            String delete_uquery = "DELETE * FROM USERS WHERE USERID = '" + ADMIN_ID + "';";
            st.executeUpdate(delete_uquery);
            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

    }
}

// Update administrator
