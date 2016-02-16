package models;

/**
 * Created by Paul on 13/02/2016.
 */
public class LotteryDraw extends LotteryBase {
        private int _id;
        private String LOTTERY_DATE;
        private String CHANGED_ymdhms;

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLOTTERY_DATE() {
        return LOTTERY_DATE;
    }

    public void setLOTTERY_DATE(String LOTTERY_DATE) {
        this.LOTTERY_DATE = LOTTERY_DATE;
    }


}

