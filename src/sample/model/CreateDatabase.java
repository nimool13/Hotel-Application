package sample.model;

import java.sql.*;

public class CreateDatabase {
    static final String DB_URL = "jdbc:mysql://localhost:3306/HOTEL_02";
    String url = "jdbc:mysql://localhost";

    static final String USER = "root";
    static final String PASS = "admin";
    public void createDB() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String sql = "CREATE DATABASE IF NOT EXISTS HOTEL_02";

        try (Connection conn = DriverManager.getConnection(url, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void createTables(){
        Connection conn = null;
        Statement stmt = null;
        try {




            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");


            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql_create_roles = "CREATE TABLE IF NOT EXISTS Roles " +
                    "(role_ID INTEGER , " +
                    " role_name VARCHAR(255), " +
                    " PRIMARY KEY ( role_ID ))";

            stmt.executeUpdate(sql_create_roles);
            String sql_create_admin = "CREATE TABLE IF NOT EXISTS Admin " +
                    "(admin_ID INTEGER auto_increment , " +
                    " admin_Fname VARCHAR(255), " +
                    " role_ID INTEGER, " +
                    " admin_Lname VARCHAR(255), " +
                    " admin_Password VARCHAR(255), " +
                    "foreign key (role_ID) references roles(role_ID)," +
                    " PRIMARY KEY ( admin_ID ))";

            stmt.executeUpdate(sql_create_admin);

            String sql_create_user = "CREATE TABLE IF NOT EXISTS user " +
                    "(user_ID INTEGER auto_increment , " +
                    " user_Fname VARCHAR(255), " +
                    " user_Lname VARCHAR(255), " +
                    "user_name VARCHAR(255), " +
                    " role_ID INTEGER, " +

                    " user_Password VARCHAR(255), " +
                    "foreign key (role_ID) references roles(role_ID)," +
                    " PRIMARY KEY ( user_ID ))";
            stmt.executeUpdate(sql_create_user);

            String sql_create_room= "CREATE TABLE IF NOT EXISTS room " +
                    "(room_NR INTEGER auto_increment , " +
                    " size double, " +
                    " bed_NR double, " +
                    " location VARCHAR(255), " +
                    " room_type VARCHAR(255), " +
                    " status enum('Available','Booked'), " +
                    " details VARCHAR(450), " +
                    " PRIMARY KEY ( room_NR ))";

            stmt.executeUpdate(sql_create_room);
            String sql_create_reservation = "CREATE TABLE IF NOT EXISTS reservation  " +
                    "(res_NR INTEGER auto_increment , " +
                    " user_name VARCHAR(255), " +
                    " room_NR INTEGER, " +
                    " date_reserve timestamp not null default now(), " +

                    " res_status enum('Paid','Not paid'), " +
                    "foreign key (room_NR) references room(room_NR)," +
                    " PRIMARY KEY ( res_NR ))";

            stmt.executeUpdate(sql_create_reservation);
            String sql_create_customer = "CREATE TABLE IF NOT EXISTS customer " +
                    "(customer_ID INTEGER auto_increment , " +
                    " customer_Fname VARCHAR(255), " +
                    " customer_Lname VARCHAR(255), " +

                    " customer_address VARCHAR(255), " +
                    " payment_methode enum('Credit/Debit card payments','Bank transfers','Cash'), " +
                    "foreign key (customer_ID) references reservation(res_NR)," +
                    " PRIMARY KEY ( customer_ID ))";

            stmt.executeUpdate(sql_create_customer);



            System.out.println("Created table in given database...");
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void createRoomPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating stored procedure in given database...");
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE if not exists add_room(in room_size double, in bed_number double, in room_location varchar(255),in type_room varchar(255),in stat int,in det varchar(450)) " +
                    "BEGIN "+
                    "insert into room(size, bed_NR, location,room_type, status, details) VALUES (room_size, bed_number, room_location,type_room,stat,det); "+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createUserPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating stored procedure in given database...");
            stmt = connection.createStatement();

            String query_user = "CREATE PROCEDURE if not exists add_user(in user_F varchar(255), in user_L varchar(255), in userName varchar(255),in user_Pass varchar(255),in roleID int(11)) " +
                    "BEGIN "+
                    "insert into user (user_Fname, user_Lname, user_name, user_Password, role_ID) VALUES (user_F, user_L, userName,user_Pass,roleID); "+
                    "END";

            stmt.execute(query_user);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
