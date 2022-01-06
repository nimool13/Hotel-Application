package sample.model;

import sample.model.poco.Booking;
import sample.model.poco.Customer;
import sample.model.poco.Reservation;
import sample.model.poco.Room;

import java.sql.*;
import java.util.ArrayList;

public class DBM2 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/HOTEL_18?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "magekhari2";
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;

    public void CreatUser() {
        String sql_user = "INSERT INTO user(user_Fname, user_Lname, user_name,role_ID, user_Password) " +
                "value ('Admin','AdminL','Adminu',1,'admin123')";
        try {



            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
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
    public void CreateRooms() {

        String sql_roles = "insert into room(roomNumber,size, bed_NR, location, room_type, status, details)  " +
                " VALUES (1001,74, 3, 'Stockholm','king size', 2, 'Our king size four poster provides views over landscaped gardens. It has a seating area, ample storage, digital safe, minibar and luxurious duck down bedding.'), (1002,44, 1, 'Stockholm','king size', 2, 'Our king size four poster provides views over landscaped gardens. It has a seating area, ample storage, digital safe, minibar and luxurious duck down bedding.')";
        try {
            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
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
    public void CreateRoles() {

        String sql_roles = "INSERT INTO roles(role_ID, role_name) " +
                "VALUES(2,'Receptionist'), (1,'Admin')";
        try {
            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
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
    public void changeDetails( String rLocation,int rSize,String rType , String det, int bNumber,int rNumber){

        try {
            String query =   "update room set  location =?  , size = ? , room_type = ? , details = ? ,  bed_NR = ?  where  roomNumber = ?";

            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            ps = conn.prepareStatement(query);
            ps.setString(1, rLocation);
            ps.setInt(2, rSize);
            ps.setString(3, rType);
            ps.setString(4, det);
            ps.setInt(5, bNumber);

            ps.setInt(6,rNumber);
            ps.executeUpdate();





        } catch (SQLException se) {

            se.printStackTrace();
        }

    }
    public Customer getCustomer(int resNR){
        Customer customer = new Customer();
        String query = "select  * from customer join customers_reservations as cr on cr.customerID = customer.customer_ID where  cr.reservationID ='"+resNR+"'";
        try {


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                customer.setFirstName(rs.getString("customer_Fname"));
                customer.setLasName(rs.getString("customer_Lname"));
                customer.setAddress(rs.getString("customer_address"));
                customer.setPayment(rs.getString("payment_methode"));
            }
        } catch (SQLException se) {

            se.printStackTrace();
        }
        return customer;
    }
    public Booking getBooking(int resNR){
        Booking booking = new Booking();
        String query = "select * from reservation where res_NR ='"+resNR+"'";
        try {


            System.out.println("Connecting to a selected database...");
            conn = MyConnection.getConnection();
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                booking.setfName(rs.getString("user_name").split(", ")[1]);
                booking.setlName(rs.getString("user_name").split(", ")[0]);
                booking.setDate_from(rs.getDate("date_reserve"));
                booking.setpStatus(rs.getString("res_status"));
                booking.setDate_to(rs.getDate("reserved_until"));
                booking.setRoomNumber(String.valueOf(rs.getInt("room_NR")));
            }
        } catch (SQLException se) {

            se.printStackTrace();
        }
        return booking;
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
    public ArrayList<Booking> dateSearching(String dateFROM , String dateUNTIL) {
        ArrayList<Booking> arrayList = new ArrayList<>();
        String sql_room = "SELECT * FROM reservation a  WHERE a.reserved_until<='"+dateUNTIL+"' AND a.date_reserve >= '"+dateFROM+"' AND a.res_NR = a.res_NR";
        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_room);

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setRoomNumber(String.valueOf(rs.getInt("room_NR")));
                booking.setpStatus(String.valueOf(rs.getString("res_status")));
                booking.setDate_to(rs.getDate("reserved_until"));
                booking.setDate_from(rs.getDate("date_reserve"));
                booking.setfName(rs.getString("user_name").split(", ")[1]);
                booking.setlName(rs.getString("user_name").split(", ")[0]);
                booking.setResNumber(String.valueOf(rs.getInt("res_NR")));
                arrayList.add(booking);



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
    public ArrayList<Room> returnAvailableRooms(String dateFROM , String dateUNTIL) {
        ArrayList<Room> arrayList = new ArrayList<>();
        String sql_room = "SELECT * FROM room WHERE roomNumber NOT IN( SELECT room_NR FROM reservation WHERE  date_reserve between '"+dateFROM+"' and  '"+dateUNTIL+"' or  reserved_until between  '"+dateFROM+"' and  '"+dateUNTIL+"')";
        try {


            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql_room);

            while (rs.next()) {
                Room room = new Room();
                room.setRoom_NR(rs.getString("roomNumber"));

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

}
