package UniversitySystemGUI;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

//all code by ethan
public class UserLogin{

    public static  String UserLogin(String ID, String Password) throws SQLException{
        Connection conn;
        String url = "jdbc:mysql://localhost/sys";  //test is the database name
        String user = "root"; //username
        String password = "Password12345"; //root password, you set it when you install the DBMS

        conn = DriverManager.getConnection(url, user, password);
        //print menu
        Statement st = conn.createStatement();
        String query = "select* from users where USERID= '"+ID +"' AND Password= '"+Password+"';";

        ResultSet rs = st.executeQuery(query);
        String role = null;
        while(rs.next()){
            role = rs.getString("role");
        }
        return role;
    }
    public static  Connection New_Login(String USER, String PASSWORD){
        Connection conn;
        String url = "jdbc:mysql://localhost/sys";

        try {
            conn = DriverManager.getConnection(url, USER, PASSWORD);
            return conn;
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static  String AdminLogin(String ID, String Password, String DBAPASS) throws SQLException{
        Connection conn;
        String url = "jdbc:mysql://localhost/sys";  //test is the database name
        String user = "root"; //username
        String password = "Password12345"; //root password, you set it when you install the DBMS

        conn = DriverManager.getConnection(url, user, password);
        //print menu
        Statement st = conn.createStatement();
        String query = "select* from DATABASEADMIN where DBADMINID= '"+ID +"' AND PASS= '"+Password+"' AND DBAPASS = '"+DBAPASS+"';";

        ResultSet rs = st.executeQuery(query);
        String role = null;
        while(rs.next()){
            role = "DBADMIN";
        }
        return role;
    }

}
