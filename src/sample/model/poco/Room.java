package sample.model.poco;

public class Room {
    public String room_NR;
    public String size;
    public String bed_NR;
    public String location;
    public String status;
    public String details;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoom_NR() {
        return room_NR;
    }

    public void setRoom_NR(String room_NR) {
        this.room_NR = room_NR;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBed_NR() {
        return bed_NR;
    }

    public void setBed_NR(String bed_NR) {
        this.bed_NR = bed_NR;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
