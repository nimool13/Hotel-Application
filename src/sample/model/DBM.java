package sample.model;


import sample.model.poco.Reservation;
import sample.model.poco.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBM {
    static final String DB_URL = "jdbc:mysql://localhost:3306/HOTEL_18";
    static final String USER = "root";
    static final String PASS = "admin  ";
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;

    public void changePass(String pass,String userName){

        try {
            String query =   "update user set user_Password=? where user_name=? ";

            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            ps = conn.prepareStatement(query);
            ps.setString(1, pass);
            ps.setString(2, userName);
            ps.executeUpdate();





        } catch (SQLException se) {

            se.printStackTrace();
        }

    }

    public void reservation(String usNmae, int room_Nu, LocalDate date, int state, LocalDate dateTo){
        try {
            String query =   "insert into reservation(user_name, room_NR, date_reserve, res_status, reserved_until) VALUE (?,?,?,?,?)";

            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            ps = conn.prepareStatement(query);
            ps.setString(1, usNmae);
            ps.setInt(2, room_Nu);
            ps.setDate(3, java.sql.Date.valueOf(date));
            ps.setInt(4, state);
            ps.setDate(5, Date.valueOf(dateTo));


            ps.executeUpdate();





        } catch (SQLException se) {

            se.printStackTrace();
        }
    }

    public void changeDetails( String rLocation,double rSize,String rType , String det, double bNumber,int rNumber){

        try {
            String query =   "update room set  location =?  , size = ? , room_type = ? , details = ? ,  bed_NR = ?  where  roomNumber = ?";

            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            ps = conn.prepareStatement(query);
            ps.setString(1, rLocation);
            ps.setDouble(2, rSize);
            ps.setString(3, rType);
            ps.setString(4, det);
            ps.setDouble(5, bNumber);

            ps.setInt(6,rNumber);
            ps.executeUpdate();





        } catch (SQLException se) {

            se.printStackTrace();
        }

    }

    public String checkCurrentPass(String userName){
        String pass = "";
        String query = "select * from user where user_name = '"+userName+"'";
        try {


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
              pass = rs.getString("user_Password");

            }
        } catch (SQLException se) {

            se.printStackTrace();
        }
        return pass;
    }

    public void deleteRoom(int number){
        try {
            String query =   "delete from room  where room_NR= '"+number+"' ";


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
          stmt.execute(query);





        } catch (SQLException se) {

            se.printStackTrace();
        }
    }

    public void  room(){
        String sql_create_room= "CREATE TABLE IF NOT EXISTS room " +
                "(room_NR INTEGER auto_increment , " +
                " size double, " +
                " bed_NR double, " +
                " location VARCHAR(255), " +
                " room_type VARCHAR(255), " +
                " status enum('Available','Booked'), " +
                " details VARCHAR(450), " +
                " PRIMARY KEY ( room_NR ))";

        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql_create_room);
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
    public void Connect() {

        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void Add_Rooms() {
        String sql_rooms = "INSERT INTO room(size, bed_NR, location,room_type , status, details) values (74, 3, 'Stockholm','king size', 2, 'Our king size four poster provides views over landscaped gardens. It has a seating area, ample storage, digital safe, minibar and luxurious duck down bedding.'),(96, 3, 'Stockholm','king size', 1, 'Our king size sleigh bedded also provides views over landscaped gardens. It has ample storage, a seating area, digital safe, minibar and luxurious duck down bedding.'),(66, 1, 'Stockholm','king size', 2, 'Our king size sleigh bedded also provides views over landscaped gardens. It has ample storage, a seating area, digital safe, minibar and luxurious duck down bedding.')";


        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql_rooms);
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
    public ArrayList<Reservation> allRoomRes(int nr){
        ArrayList<Reservation> arrayList = new ArrayList<>();
        String pass = "";
        String query = "select * from reservation where room_NR ='"+nr+"'";
        try {


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setUser(rs.getString("user_name"));
                reservation.setDate_from(String.valueOf(rs.getDate("date_reserve")));
                reservation.setDate_to(String.valueOf(rs.getDate("reserved_until")));
                reservation.setStatus(rs.getString("res_status"));
                arrayList.add(reservation);

            }
        } catch (SQLException se) {

            se.printStackTrace();
        }
        return arrayList;
    }
    public ArrayList<Room> returnRoom() {
        ArrayList<Room> arrayList = new ArrayList<>();
        String sql_room = "select * from room ";
        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_room);

            while (rs.next()) {
                Room room = new Room();
                room.setRoom_NR(rs.getString("roomNumber"));
                room.setDetails(rs.getString("details"));
                room.setStatus(rs.getString("status"));
                room.setSize(rs.getString("size"));
                room.setLocation(rs.getString("location"));
                room.setBed_NR(rs.getString("bed_NR"));
                room.setType(rs.getString("room_type"));
                arrayList.add(room);

            }
            stmt.close();
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

        return arrayList;
    }

    public void CreatUser() {
        String sql_user = "INSERT INTO user(user_Fname, user_Lname, user_name,role_ID, user_Password) " +
                "value ('Admin','AdminL','Adminu',1,'admin123')";
        try {



            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql_user);
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

    public void CreateRoles() {

        String sql_roles = "INSERT INTO roles(role_ID, role_name) " +
                "VALUES(2,'Receptionist'), (1,'Admin')";
        try {
            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            stmt.executeUpdate(sql_roles);
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
