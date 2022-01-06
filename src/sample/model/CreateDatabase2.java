package sample.model;

import java.sql.*;

public class CreateDatabase2 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/HOTEL_18?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "magekhari2";
    String url = "jdbc:mysql://localhost";

    public void createDB() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String sql = "CREATE DATABASE IF NOT EXISTS HOTEL_18";

        try (Connection conn = DriverManager.getConnection(url, USER, PASS);

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        Connection conn = null;
        Statement stmt = null;
        try {


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");


            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            String sql_create_roles = "CREATE TABLE IF NOT EXISTS roles " +
                    "(role_ID Int(100) , " +
                    " role_name VARCHAR(255), " +
                    " PRIMARY KEY ( role_ID ))";

            stmt.executeUpdate(sql_create_roles);


            String sql_create_user = "CREATE TABLE IF NOT EXISTS user " +
                    "(user_ID INTEGER auto_increment , " +
                    " user_Fname VARCHAR(255), " +
                    " user_Lname VARCHAR(255), " +
                    "user_name VARCHAR(255), " +
                    "user_Password VARCHAR(255), " +
                    "email varchar(300), " +
                    " role_ID INTEGER, " +


                    "foreign key (role_ID) references roles(role_ID)," +
                    " PRIMARY KEY ( user_ID ))";
            stmt.executeUpdate(sql_create_user);

            String sql_create_room = "CREATE TABLE IF NOT EXISTS room " +
                    "(roomNumber int(100)  not null unique , " +
                    " size int(100) not null , " +
                    " bed_NR int(100) not null, " +
                    " location VARCHAR(255) not null, " +
                    " room_type VARCHAR(255), " +
                    " status enum('Available','Booked'), " +
                    " details VARCHAR(450), " +
                    " PRIMARY KEY ( roomNumber ))";

            stmt.executeUpdate(sql_create_room);
            String sql_create_reservation = "CREATE TABLE IF NOT EXISTS reservation  " +
                    "(res_NR INTEGER auto_increment , " +
                    " user_name VARCHAR(255), " +
                    " room_NR INTEGER, " +
                    " date_reserve date   default date , " +

                   " res_status enum('Paid','Not paid'), " +
                    " reserved_until date   , " +

                    " PRIMARY KEY ( res_NR ))";

            stmt.executeUpdate(sql_create_reservation);

            String sql_create_room_reservation = "CREATE TABLE IF NOT EXISTS room_reservation  " +
                    "(room_reservation_id int(100) auto_increment , " +
                    " room_NR   int(100)     not null, " +
                    "  res_NR   int(100)     not null, " +
                    " foreign key (room_NR) references room(roomNumber) on delete cascade," +
                    " foreign key (res_NR) references reservation(res_NR) on delete cascade," +
                    " PRIMARY KEY ( room_reservation_id ))";

            stmt.executeUpdate(sql_create_room_reservation);

            String sql_create_customer = "CREATE TABLE IF NOT EXISTS customer " +
                    "(customer_ID   int(100)     not null auto_increment, " +
                    " customer_Fname VARCHAR(255), " +
                    " customer_Lname VARCHAR(255), " +

                    " customer_address VARCHAR(255), " +
                    " payment_methode enum('Credit/Debit card payments','Bank transfers','Cash'), " +
                    " PRIMARY KEY ( customer_ID ))";

            stmt.executeUpdate(sql_create_customer);

            String sql_create_customers_reservations= "CREATE TABLE IF NOT EXISTS customers_reservations " +
                    "(customers_reservations_ID   int(100)     not null auto_increment, " +
                    "  customerID      int(100)     not null," +
                    "  reservationID      int(100)     not null, " +

                    "   roomID      int(100)     not null," +
                    " primary key (customers_reservations_ID)," +
                    "   foreign key (customerID) references customer(customer_ID) on delete cascade," +
                    "  foreign key (reservationID) references reservation(res_NR) on delete cascade,"+
                    "   foreign key (roomID) references room(roomNumber) on delete cascade)";

            stmt.executeUpdate(sql_create_customers_reservations);

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
}
