package sample.model;
import java.sql.*;
public class MyConnection {
    static final String DB_URL = "jdbc:mysql://localhost:3306/HOTEL_18?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "magekhari2";
    public static Connection getConnection(){
        Connection con = null;
        try {

            System.out.println("Connecting to a selected database...");


            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connected database successfully...");
            con =DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return con;
    }
    }

