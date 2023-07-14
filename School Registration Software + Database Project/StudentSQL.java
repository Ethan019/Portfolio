package UniversitySystemGUI;

//WIP

import java.io.*;
import java.sql.*;
import java.util.Scanner;



//all code by ethan
public class StudentSQL {

    //student views (needs return objects)

    public void create_Student_View(Connection conn, String STUDID) {
        try {
            Statement st = conn.createStatement();

            String query = "CREATE OR REPLACE VIEW STUDENTCOR (COURSENUM, LETTERGRADE) AS SELECT COURSE.COURSENUM, STUDENTRECORD.LETTERGRADE FROM COURSE, STUDENTRECORD WHERE STUDENTRECORD.STUDID = '" + STUDID + "' AND COURSE.CRN = STUDENTRECORD.CRN;";
            st.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void student_View(Connection conn, String STUDID) {
        try {
            Statement st = conn.createStatement();

            String query = "CREATE OR REPLACE VIEW STUDENTCOR (COURSENUM, GRADE) AS SELECT COURSE.COURSENUM, REGISTEREDFOR.GRADE FROM COURSE, REGISTEREDFOR WHERE REGISTEREDFOR.STUDID = '" + STUDID + "' AND COURSE.CRN = REGISTEREDFOR.CRN;";
            st.executeUpdate(query);

            String query2 = "Select * from STUDENTCOR;";


            ResultSet rs = st.executeQuery(query2);
            System.out.println("Course | Grade");
            while (rs.next()) {
                System.out.println("--------------");
                String course = rs.getString("COURSENUM");
                String grade = rs.getString("GRADE");
                System.out.println(course + "         " + grade);


            }
            System.out.println("--------------");
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("Message: " + e.getMessage());

        }

    }

    //student display courses (not complete)
    public void show_Courses(Connection conn, String DEPART) throws SQLException, IOException {
        try (
                Statement st = conn.createStatement();
        ) {
            String query = "SELECT CRN, COURSENUM, SECTIONNUM, PROFNAME, DES FROM COURSE WHERE DEPTKEY IN (SELECT DEPTKEY FROM DEPARTMENT WHERE DEPTNAME = '" + DEPART + "');";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String crn = rs.getString("CRN");
                String coursenum = rs.getString("COURSENUM");
                String sectionnum = rs.getString("SECTIONNUM");
                String profname = rs.getString("PROFNAME");
                String desc = rs.getString("DES");

                System.out.println("CRN: " + crn + " Course Number: " + coursenum + " Section Number: " + sectionnum + " Professors Name: " + profname + " Course Description: " + desc);
            }

        }
    }


    //student add course (not complete)
    public boolean add_Course(Connection conn, String STUDID, String CRN) {


        try {
            Statement st = conn.createStatement();

            String query1 = "select* from REGISTEREDFOR Where STUDID = " + STUDID + " AND CRN = " + CRN + ";";

            ResultSet rs = st.executeQuery(query1);
            if (rs.next()) {
                System.out.println("You are already registered for this course");


                return false;
            } else {
                String addc = "Insert into Registeredfor(STUDID, CRN) values(" + STUDID + ", " + CRN + ");";
                st.executeUpdate(addc);
                return true;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



    //student drop class (not finished)
    public boolean student_Drop(Connection conn, String CRN, String STUDID) {
        try{
        Statement st = conn.createStatement();


           String query ="Delete From RegisteredFor WHERE CRN = " + CRN+ " AND STUDID = "+STUDID+";";

        

           st.executeUpdate(query);
            return true;

        }catch(SQLException e){
            System.out.println("message: "+e.getMessage());
            return false;
        }


    }




}
