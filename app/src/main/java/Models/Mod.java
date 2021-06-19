package Models;

import com.google.firebase.Timestamp;

public class Mod {
    private String sting;

    private Timestamp timestamp = null;

    private String ID;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSting() {
        return sting;
    }

    public void setSting(String sting) {
        this.sting = sting;
    }

    public Mod(String sting) {
        this.sting = sting;
    }
}
