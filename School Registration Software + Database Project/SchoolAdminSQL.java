package UniversitySystemGUI;// Caroline
// WIP
// School admin methods

import java.io.*;
import java.sql.*;
//import java.util.Scanner;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//code written by ethan + caroline. Ethan mostly error checked and made revisements some functions are old code.
public class SchoolAdminSQL {
    // Allow admin to view student record info
    public void ViewStudRec(Connection conn, String STUDID) throws SQLException, IOException {
        try {
            Statement st = conn.createStatement();

            String query1 = "CREATE OR REPLACE VIEW STUDENTRE (RECORDID, STUDID, DEPTKEY, COURSENUM, GRADE, LETTERGRADE, SEMESTER, YEAR) AS SELECT \n" +
                    "STUDENTRECORD.RECORDID, STUDENTRECORD.STUDID, STUDENTRECORD.DEPTKEY, COURSE.COURSENUM, " +
                    "STUDENTRECORD.GRADE, STUDENTRECORD.LETTERGRADE,STUDENTRECORD.SEMESTER, STUDENTRECORD.YEAR\n" +
                    "FROM COURSE, STUDENTRECORD WHERE STUDENTRECORD.STUDID = " + STUDID + " AND COURSE.CRN = STUDENTRECORD.CRN;";
            st.executeUpdate(query1);

            String query2= "Select * from STUDENTRE;";
            ResultSet rs = st.executeQuery(query2);
            while(rs.next()) {
                String record_id = rs.getString("RECORDID");
                String stud_id = rs.getString("STUDID");
                String dept_key = rs.getString("DEPTKEY");
                String course_num = rs.getString("COURSENUM");
                String grade = rs.getString("GRADE");
                String letter_grade = rs.getString("LETTERGRADE");
                String semester = rs.getString("SEMESTER");
                String year = rs.getString("YEAR");
                System.out.println("Record ID: " + record_id);
                System.out.println("Student ID: " + stud_id);
                System.out.println("Department: " + dept_key);
                System.out.println("Course Number: " + course_num);
                System.out.println("Grades: " + grade);
                System.out.println("Letter Grade: " + letter_grade);
                System.out.println("Semester: " + semester);
                System.out.println("Year: " + year);
            }
        }
        catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    // Allow admin to view professor info
    public void ViewProfInfo(Connection conn, String PROF_ID){
        try {
            Statement st = conn.createStatement();

            String query = "SELECT PROFESSOR.DEPTKEY, PROFESSOR.PROFNAME, COURSE.COURSENUM, FROM PROFESSOR, COURSE WHERE PROFID = '" + PROF_ID + "';";
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                //String f_name = rs.getString("FNAME");
                //String l_name = rs.getString("LNAME");
                String department = rs.getString("DEPTKEY");
                String name = rs.getString("PROFNAME");
                String coursen = rs.getString("COURSENUM");
                System.out.println("Professor ID:" + PROF_ID);
                //System.out.println("First Name: " + f_name);
                //System.out.println("Last Name: " + l_name);
                System.out.print("Professor Name:"+name);
                System.out.println("Department:" + department);
                System.out.println("Course: "+coursen);
            }
        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

    // Allow admin to add student
    public boolean ceckstud(Connection conn, String studid) {
        try {
            Statement st = conn.createStatement();
            String query = "Select STUDID from STUDENT where STUDID =" + studid + ";";

            ResultSet rs = st.executeQuery(query);
            if (rs.next() == true) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }


    }




    public boolean AddStudent(Connection conn, String F_NAME, String L_NAME, String MAJOR, String Password){
        try{
            Statement st = conn.createStatement();

            //checks for student first
            String query = "SELECT * FROM STUDENT WHERE FNAME= '"+F_NAME+"' AND LNAME ='"+L_NAME+"' AND MAJOR = '"+MAJOR+"';";

            ResultSet rs1 = st.executeQuery(query);

            if(!rs1.next()){

                String addstu = "INSERT INTO STUDENT(FNAME, LNAME, MAJOR) VALUES ('"+F_NAME+"', '"+L_NAME+"', '"+MAJOR+"');";

                st.executeUpdate(addstu);

            }else
            {
                //uncomment if boolean instead of void
                return false;
            }

            String query2 = "SELECT STUDID FROM STUDENT WHERE FNAME= '"+F_NAME+"' AND LNAME ='"+L_NAME+"' AND MAJOR = '"+MAJOR+"';";


            ResultSet rs2 = st.executeQuery(query2);

            if(rs2.next()){
                String userid = rs2.getString("STUDID");
                String adduse = "INSERT INTO USERS(USERID, PASSWORD, ROLE) VALUES ("+userid+", '"+Password+"', 'Student');";


                st.executeUpdate(adduse);
                //uncomment if returning true.
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
    // Allow admin to update professor info


    // Allow admin to add student to course - add button is selected (testing)
    public boolean AddStudToCourse(Connection conn, String STUD_ID, String CRN) {
        try {
            Statement st = conn.createStatement();

            // Display registered courses first
            String select_query = "SELECT * FROM REGISTEREDFOR WHERE STUDID = " + STUD_ID + " AND CRN = "+CRN+";";
            ResultSet rs1 = st.executeQuery(select_query);
            if(!rs1.next()) {
                String insert_student="Insert into REGISTEREDFOR(STUDID, CRN) VALUES("+STUD_ID+","+CRN+");";
                st.executeUpdate(insert_student);
                return true;
            }
        }catch (SQLException e ) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }


    public boolean DeleteProfessor(Connection conn, String Fname, String Lname, String Depart){
        try {
            Statement st = conn.createStatement();
            String stud = "SELECT PROFID FROM PROFESSOR WHERE FNAME = '"+Fname+"' AND LNAME='"+Lname+"' AND DEPTKEY='"+Depart+"';";
            ResultSet rs1 = st.executeQuery(stud);
            while(rs1.next()){
                String profid = rs1.getString("PROFID");
                String query = "select * from course where PROFID ="+profid+";";
                ResultSet rs2=st.executeQuery(query);
                if(rs2.next()){
                    String crn = rs2.getString("CRN");
                    ResultSet rs3=st.executeQuery("Select CRN FROM REGISTEREDFOR WHERE CRN ="+crn+";");

                    if(rs3.next()){
                        String dele_reg = "Delete FROM REGISTEREDFOR WHERE CRN ="+crn+";";
                        st.executeUpdate(dele_reg);
                        String dele_course ="DELETE FROM COURSE WHERE PROFID ="+profid+";";
                        st.executeUpdate(dele_course);
                        String del_prof = "DELETE FROM Professor WHERE STUDID ="+profid+";";
                        st.executeUpdate(del_prof);
                        return true;
                    }else{
                        String dele_course ="DELETE FROM COURSE WHERE PROFID ="+profid+";";
                        st.executeUpdate(dele_course);
                        String del_student = "DELETE FROM PROFESSOR WHERE PROFID ="+profid+";";
                        st.executeUpdate(del_student);
                        return true;
                    }


                }else{
                    String del_prof3 = "DELETE FROM PROFESSOR WHERE PROFID ="+profid+";";
                    st.executeUpdate(del_prof3);
                    return true;
                }



            }


        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return false;
    }
    public boolean AddProfessor(Connection conn,String F_NAME, String L_NAME, String PROF_NAME, String DEPT_KEY, String Password){
        try{
            Statement st = conn.createStatement();

            //checks for prof first
            String query = "SELECT * FROM PROFESSOR WHERE PROFNAME = '"+PROF_NAME+"' AND DEPTKEY = '"+DEPT_KEY+"';";

            ResultSet rs1 = st.executeQuery(query);

            if(!rs1.next()){

                String addprof = "INSERT INTO PROFESSOR(PROFNAME, DEPTKEY, FNAME, LNAME) VALUES ('"+PROF_NAME+"', '"+DEPT_KEY+"', '"+F_NAME+"', '"+L_NAME+"');";

                st.executeUpdate(addprof);

            }else
            {
                //uncomment if boolean instead of void
                return false;
            }

            String query2 = "SELECT PROFID FROM PROFESSOR WHERE PROFNAME= '"+PROF_NAME+"' AND DEPTKEY = '"+DEPT_KEY+"';";


            ResultSet rs2 = st.executeQuery(query2);

            if(rs2.next()){
                String userid = rs2.getString("PROFID");
                String adduse = "INSERT INTO USERS(USERID, PASSWORD, ROLE) VALUES ("+userid+", '"+Password+"', 'Professor');";


                st.executeUpdate(adduse);
                //uncomment if returning true.
                return true;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public boolean DeleteStudent(Connection conn, String Fname, String Lname, String Major){
        try {
            Statement st = conn.createStatement();
            String stud = "SELECT STUDID FROM STUDENT WHERE FNAME = '"+Fname+"' AND LNAME='"+Lname+"' AND MAJOR='"+Major+"';";
            ResultSet rs1 = st.executeQuery(stud);
            while(rs1.next()){
                String studid = rs1.getString("STUDID");
                String query = "select * from registeredfor where studid ="+studid+";";
                ResultSet rs2=st.executeQuery(query);
                if(rs2.next()){
                    String dele_course ="DELETE FROM REGISTEREDFOR WHERE STUDID ="+studid+";";
                    st.executeUpdate(dele_course);
                    String del_student = "DELETE FROM STUDENT WHERE STUDID ="+studid+";";
                    st.executeUpdate(del_student);
                    return true;

                }else{
                    String del_student = "DELETE FROM STUDENT WHERE STUDID ="+studid+";";
                    st.executeUpdate(del_student);
                    return true;
                }



            }


        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
        return false;
    }

    // Add a course WORKS -- TESTED
    public  boolean AddCourse(Connection conn,  String COURSE_NUM, String COURSE_NAME, String DES, String PROF_NAME, String Semester, String Year, String Section_NUM ){
        try {
            Statement st = conn.createStatement();


            String adept ="Select DEPTKEY, PROFID FROM PROFESSOR WHERE PROFNAME = '"+PROF_NAME+"';";
            ResultSet r2 = st.executeQuery(adept);

            if(r2.next()) {
                String deptk = r2.getString("DEPTKEY");
                String profi = r2.getString("PROFID");
                System.out.println(deptk);
                String add_course_query = "INSERT INTO COURSE(DEPTKEY, coursename, COURSENUM, SECTIONNUM, PROFID, PROFNAME, DES, SEMESTER, YEAR) VALUES('" + deptk + "', '" + COURSE_NAME + "', '" + COURSE_NUM + "', '" + Section_NUM + "', " + profi + ", '"+PROF_NAME+"', '"+DES+"', '"+Semester+"', '"+Year+"');";
                st.executeUpdate(add_course_query);
                return true;
            }


            //String select_query = "SELECT * FROM COURSE WHERE COURSENUM = '" + COURSE_NUM + "';";
            //ResultSet rs = st.executeQuery(select_query);

            //if(rs.next() == true) {
            //  System.out.println("This course is in the system.");
            // return true;
            //}


        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage() +"Please try to add course again");
            return false;
        }
        return  false;
    }

    // Delete a course WORKS -- TESTED
    public boolean DeleteCourse(Connection conn, String CRN, String ID) {
        try {
            Statement st = conn.createStatement();
            String dept = "Select DEPTKEY FROM COURSE WHERE CRN ="+CRN+";";
            st.executeQuery(dept);
            ResultSet r1=st.executeQuery(dept);

            while(r1.next()){
                String deptk = r1.getString("DEPTKEY");
                String adept ="Select DEPTKEY FROM SCHOOLADMIN WHERE SCHADID=" +ID+";";
                ResultSet r2 = st.executeQuery(adept);

                while(r2.next()){
                    if(!Objects.equals(deptk, r2.getString("DEPTKEY"))){
                        System.out.println("THIS CLASS IS OUTSIDE OF YOUR DEPARTMENT");
                        return false;

                    }
                    else{
                        String deleter = "DELETE FROM REGISTEREDFOR WHERE CRN = "+CRN+";";
                        st.executeUpdate(deleter);
                        String delete_query = "DELETE FROM COURSE WHERE CRN ="+CRN+";";
                        st.executeUpdate(delete_query);
                        System.out.println("Course was deleted");
                        return true;
                    }
                }

            }


        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    public boolean dropStudent(Connection conn, String CRN,  String studid){
        try{
            Statement st = conn.createStatement();


            String query ="Delete From RegisteredFor WHERE CRN = " + CRN+ " AND STUDID = "+studid+";";



            st.executeUpdate(query);
            return true;

        }catch(SQLException e){
            System.out.println("message: "+e.getMessage());
            return false;
        }

    }
    public void EditStud(Connection conn, Scanner scan, String STUD_ID, String F_NAME, String L_NAME, String MAJOR) throws SQLException, IOException {
        try {
            Statement st = conn.createStatement();

            String update_query = "UPDATE STUDENT SET FNAME = '" + F_NAME + "', LNAME = '" + L_NAME + "', MAJOR = '" + MAJOR + "' WHERE STUDID = '" + STUD_ID + "';";
            st.executeUpdate(update_query);

            String select_query = "SELECT * FROM STUDENT WHERE STUDID = '" + STUD_ID + "';";
            ResultSet rs = st.executeQuery(select_query);

            if(rs.next() == true) {
                System.out.println("The input information is already current.");
                return;
            }

            else {
                System.out.println("Information updated successfully.");
            }
        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void EditProf(Connection conn, Scanner scan, String PROF_ID, String F_NAME, String L_NAME, String DEPARTMENT) throws SQLException, IOException {
        try {
            Statement st = conn.createStatement();

            String update_query = "UPDATE PROFESSOR SET FNAME = '" + F_NAME + "', LNAME = '" + F_NAME + "', DEPARTMENT = '" + DEPARTMENT + "' WHERE PROFID = '" + PROF_ID + "';";
            st.executeUpdate(update_query);

            String select_query = "SELECT * FROM PROFESSOR WHERE PROF_ID = '" + PROF_ID + "';";
            ResultSet rs = st.executeQuery(select_query);
            if(rs.next() == true) {
                System.out.println("Professor added.");
                return;
            }
            else {
                System.out.println("Try again.");
            }
        }
        catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

}