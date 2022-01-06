package sample.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StoredProcedure2 {


        public void createAddUserPres(){
            Connection connection = MyConnection.getConnection();
            Statement stmt = null;
            try {
                stmt = connection.createStatement();

                String query = "CREATE PROCEDURE add_user(in Fname varchar(100), in Lname varchar(100), in username varchar(100),in pass varchar(100),in UEmail varchar(300) ,in rol int(100)) " +
                        "BEGIN "+
                        "   insert into user(user_Fname, user_Lname, user_name, user_Password,email, role_ID) values (Fname, Lname, username,pass,UEmail,rol);"+
                        "END";

                stmt.execute(query);
                System.out.println("Procedure Created......");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

    public void createAddRoomPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE  add_room(in rNumber int(100),in siz int(100), in bedsNR int(100), in loca varchar(100),in rType varchar(100) ,in stat int(100),in deta varchar(450)) " +
                    "BEGIN "+
                    " insert into room(roomNumber,size, bed_NR, location, room_type, status, details) values (rNumber,siz, bedsNR, loca,rType,stat,deta); "+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void createSearch_bookingPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE search_booking(IN lastName varchar(100)) " +
                    "BEGIN "+
                    "  SELECT res.res_NR,res.room_NR , res.user_name , res.date_reserve , res.reserved_until , res.res_status,cust.payment_methode from reservation as res JOIN customers_reservations as custRes ON custRes.customerID = res.res_NR JOIN customer as cust ON cust.customer_ID = custRes.customerID where cust.customer_Lname = lastName;"+

                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void createShow_bookingPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE show_booking(IN room_id INT)  " +
                    "BEGIN "+
                    "START TRANSACTION;"+
                    "  SELECT res.res_NR ,res.room_NR , res.user_name , res.date_reserve , res.reserved_until , res.res_status,cust.payment_methode from reservation as res JOIN customers_reservations as custRes ON custRes.customerID = res.res_NR JOIN customer as cust ON cust.customer_ID = custRes.customerID where res.room_NR = room_id;"+
                    "COMMIT;"+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void createChange_reservation(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE change_reservation(in uLName varchar(255),in uFName varchar(255), in rNumber int(11) , in dFrom date , in rStat int,in dTo date,in rsNUMBER int,in methode varchar(100) , in location varchar(100)) " +
                    "BEGIN "+
                    "declare test int; "+
                    "START TRANSACTION; "+
                    "select customerID into test from customer join customers_reservations on customer.customer_ID = customers_reservations.customerID where reservationID = rsNUMBER;"+
                    " update reservation set user_name = concat(uLName, ', ', uFName), room_NR = rNumber , date_reserve = dFrom, res_status = rStat , reserved_until = dTo where res_NR = rsNUMBER; "+
                    " update reservation set user_name = concat(uLName, ', ', uFName), room_NR = rNumber , date_reserve = dFrom, res_status = rStat , reserved_until = dTo where res_NR = rsNUMBER;"+
                    "update customer set customer_Lname = uLName , customer_Fname = uFName, payment_methode = methode , customer_address = location where customer_ID = test;"+
                    "COMMIT;"+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void createAddReservationPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE add_reservation( in room int(100), in dFrom date,in stat int(100) ,in dTo date,in fname varchar(100) , in lname varchar(100) , in adr varchar(100) , in payment int )" +
                    "BEGIN "+
                    " declare cust int ;"+
                    " declare resNR int ;"+
                    "START TRANSACTION;"+
                    "   insert into customer(customer_Fname, customer_Lname, customer_address, payment_methode) VALUES (fname,lname,adr,payment); "+
                    "  select customer_ID into  cust from customer where customer_ID = last_insert_id();"+

                    "  insert into reservation(user_name, room_NR, date_reserve, res_status, reserved_until) values (concat(lname, ', ', fname), room, dFrom,stat,dTo); "+
                    "  set  resNR = last_insert_id (); "+
                    "     insert into room_reservation(room_NR, res_NR) values (room,last_insert_id()); "+
                    " insert into customers_reservations(customerID, reservationID, roomID) VALUES (cust,resNR,room);"+
                    "COMMIT;"+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createDeleteRoomPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE  delete_room(in num int) " +
                    "BEGIN "+
                    "START TRANSACTION;"+
                    "   delete  from reservation where room_NR = num; "+
                    "   delete  from room_reservation where room_NR = num; "+
                    "   delete from room where roomNumber = num;"+
                    "COMMIT;"+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createChangePassPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE  changePassword(in pass int, in namee varchar(100)) " +
                    "BEGIN "+
                    "    update user set user_Password = pass where  user_name = namee; "+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void createChangeRoomDetailsPres(){
        Connection connection = MyConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            String query = "CREATE PROCEDURE  changeRoomDetails(in numberr int, in Rsize int , in beds int , in loc varchar(100),in Rtype varchar(100), in stat int , in det varchar(400)) " +
                    "BEGIN "+
                    "START TRANSACTION;"+
                    "      update  room set  size = Rsize , bed_NR = beds , location = loc , room_type = Rtype , status = stat , details = det where  roomNumber = numberr; "+
                    "COMMIT;"+
                    "END";

            stmt.execute(query);
            System.out.println("Procedure Created......");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
