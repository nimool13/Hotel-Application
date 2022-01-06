package sample.model;

import sample.model.DBM;
import sample.model.StoredProcedure;

public class InsertData {

    public void insert(){
        DBM dbm = new DBM();
        dbm.CreateRoles();
        dbm.CreatUser();
        dbm.Add_Rooms();
    }

}
