package models;

/**
 * Created by Paul on 28/01/2016.
 */
public class Lottery{

    private int _id;
    private int LOTTERY_DAY;
    private String CHANGED_ymdhms;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLOTTERY_DAY() {
        return LOTTERY_DAY;
    }

    public void setLOTTERY_DAY(int LOTTERY_DAY) {
        this.LOTTERY_DAY = LOTTERY_DAY;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }
}
