package sample.model;

import sample.model.poco.Room;

import java.sql.*;

public class StoredProcedure {
    public void createRoomPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE add_room(in room_size double, in bed_number double, in room_location varchar(255),in type_room varchar(255),in stat int,in det varchar(450),int nr int (11)) " +
                    "BEGIN "+
                    "insert into room(size, bed_NR, location,room_type, status, details,roomNumber) VALUES (room_size, bed_number, room_location,type_room,stat,det,nr); "+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    /*public void createReservation(String name, int room , int status, Date until){
        //insert into reservation(user_name, room_NR, res_status, reserved_until) VALUE ('Test',1,1,date(now()))


    }
    */

    public void createReservation(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query_user = "CREATE PROCEDURE createReservation(in name varchar(255), in room int(11),dateRe timestamp, in status int(10),in until date )" +
                    "BEGIN "+
                    " insert into reservation(user_name, room_NR, res_status, reserved_until) VALUE (name,room,dateRe,status,until); "+
                    "END";

            stmt.execute(query_user);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void createUserPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query_user = "CREATE PROCEDURE add_user(in user_F varchar(255), in user_L varchar(255), in userName varchar(255),in user_Pass varchar(255),in roleID int(11)) " +
                    "BEGIN "+
                    "insert into user (user_Fname, user_Lname, user_name, user_Password, role_ID) VALUES (user_F, user_L, userName,user_Pass,roleID); "+
                    "END";

            stmt.execute(query_user);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void createRoom(Room room){
    Connection connection = MyConnection.getConnection();
        double size = Double.parseDouble(room.size);
        int bed = Integer.parseInt(room.bed_NR);
        String location = room.location;
        String type = room.type;
        String stat = room.status;
        String details = room.details;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "INSERT INTO room(size, bed_NR, location,room_type, status, details) values ('"+size+"','"+bed+"','"+location+"','"+type+"','"+stat+"','"+details+"')";
            //Executing the query
            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
