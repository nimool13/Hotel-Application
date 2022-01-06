package sample.model.poco;

import java.util.Date;

public class Booking {
    public String fName;

    public String getResNumber() {
        return resNumber;
    }

    public void setResNumber(String resNumber) {
        this.resNumber = resNumber;
    }

    public String lName;
    public String roomNumber;
    public String pMethode;

    public Date date_from;
    public Date date_to;

    public  String resNumber ;

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public String pStatus;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getpMethode() {
        return pMethode;
    }

    public void setpMethode(String pMethode) {
        this.pMethode = pMethode;
    }


    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }



}
