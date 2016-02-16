package models;

/**
 * Created by Paul on 10/11/2015.
 */
public class LotteryChoice {

    private int _id;
    private int LOTTERY_KEY;
    private String SYNDICATE_NAME;
    private String CHANGED_ymdhms;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getLOTTERY_KEY() {
        return LOTTERY_KEY;
    }

    public void setLOTTERY_KEY(int LOTTERY_KEY) {
        this.LOTTERY_KEY = LOTTERY_KEY;
    }

    public String getSYNDICATE_NAME() {
        return SYNDICATE_NAME;
    }

    public void setSYNDICATE_NAME(String SYNDICATE_NAME) {
        this.SYNDICATE_NAME = SYNDICATE_NAME;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public LotteryChoice(int _id, int LOTTERY_KEY, String SYNDICATE_NAME, String CHANGED_ymdhms) {
        this.set_id(_id);
        this.setLOTTERY_KEY(LOTTERY_KEY);
        this.setSYNDICATE_NAME(SYNDICATE_NAME);
        this.setCHANGED_ymdhms(CHANGED_ymdhms);
    }
}
