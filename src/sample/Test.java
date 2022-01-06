package sample;

import sample.model.CreateDatabase2;
import sample.model.DBM2;
import sample.model.MyConnection;
import sample.model.StoredProcedure2;

import java.sql.CallableStatement;
import java.sql.Connection;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {


        CreateDatabase2 createDatabase2 = new CreateDatabase2();
        createDatabase2.createDB();
        createDatabase2.createTables();

        DBM2 dbm2 = new DBM2();
       // dbm2.CreateRoles();
       // dbm2.CreatUser();
     //   dbm2.CreateRooms();
/*
        StoredProcedure2 storedProcedure2 = new StoredProcedure2();
        storedProcedure2.createAddUserPres();
        storedProcedure2.createAddRoomPres();
        storedProcedure2.createAddReservationPres();
        storedProcedure2.createChangePassPres();
        storedProcedure2.createChangeRoomDetailsPres();
        storedProcedure2.createShow_bookingPres();
        storedProcedure2.createChange_reservation();
        storedProcedure2.createDeleteRoomPres();
        storedProcedure2.createSearch_bookingPres();
*/

       insertt();


    }
    public static void insertt(){
        String sql = "{call add_reservation(1001,date(now()),1,date(now()),'Jacob','Sander','visby',2)}";
        String sql2 = "{call add_user('Asmaa','Eizeddin','Fl','123','asmaa.eizeddin@gmail.com',1)}";
        String sql3 = "{call add_room(1003,30,2,'Visby','Tes',1,'TADSADS')}";
        String sql4 = "{call add_reservation(1002,'2021-05-19',1,'2021-05-23','Bella','bel','visby',1)}";
        String sql5 = "{call add_user('Resp','RespL','re','123','test2@gmail.com',2)}";
        try {

            Connection con = MyConnection.getConnection();

            CallableStatement cs = con.prepareCall(sql);

            CallableStatement cs2 = con.prepareCall(sql2);
            CallableStatement cs3 = con.prepareCall(sql3);
            CallableStatement cs4 = con.prepareCall(sql4);
            CallableStatement cs5 = con.prepareCall(sql5);
           cs.executeQuery();
           cs2.executeQuery();
            cs3.executeQuery();

            cs4.executeQuery();
            cs5.executeQuery();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
