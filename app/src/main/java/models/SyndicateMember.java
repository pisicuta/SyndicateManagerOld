package models;

/**
 * Created by Paul on 10/11/2015.
 */

public class SyndicateMember extends LotteryBase {
    private int _id;
    private int MEMBER_ID;
    private int LOTTERY_CHOICE_KEY;
    private String MEMBER_NAME;
    private String MEMBER_CONTACT_INFO;
    private String LuckyStar1;
    private String LuckyStar2;
    private String CHANGED_ymdhms;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getMEMBER_ID() {
        return MEMBER_ID;
    }

    public void setMEMBER_ID(int MEMBER_ID) {
        this.MEMBER_ID = MEMBER_ID;
    }

    public int getLOTTERY_CHOICE_KEY() {
        return LOTTERY_CHOICE_KEY;
    }

    public void setLOTTERY_CHOICE_KEY(int LOTTERY_CHOICE_KEY) {
        this.LOTTERY_CHOICE_KEY = LOTTERY_CHOICE_KEY;
    }

    public String getMEMBER_NAME() {
        return MEMBER_NAME;
    }

    public void setMEMBER_NAME(String MEMBER_NAME) {
        this.MEMBER_NAME = MEMBER_NAME;
    }

    public String getMEMBER_CONTACT_INFO() {
        return MEMBER_CONTACT_INFO;
    }

    public void setMEMBER_CONTACT_INFO(String MEMBER_CONTACT_INFO) {
        this.MEMBER_CONTACT_INFO = MEMBER_CONTACT_INFO;
    }

    public String getLuckyStar1() {
        return LuckyStar1;
    }

    public void setLuckyStar1(String LuckyStar1) {
        LuckyStar1 = LuckyStar1;
    }

    public String getLuckyStar2() {
        return LuckyStar2;
    }

    public void setLuckyStar2(String LuckyStar2) {
        LuckyStar2 = LuckyStar2;
    }

    public String getCHANGED_ymdhms() {
        return CHANGED_ymdhms;
    }

    public void setCHANGED_ymdhms(String CHANGED_ymdhms) {
        this.CHANGED_ymdhms = CHANGED_ymdhms;
    }

    public SyndicateMember(int _id,int MEMBER_ID, int LOTTERY_CHOICE_KEY, String MEMBER_NAME, String MEMBER_CONTACT_INFO, String ball1, String ball2, String ball3, String ball4, String ball5, String LuckyStar1, String LuckyStar2, String CHANGED_ymdhms) {
        this.set_id(_id);
        this.setMEMBER_ID(MEMBER_ID);
        this.setLOTTERY_CHOICE_KEY(LOTTERY_CHOICE_KEY);
        this.setMEMBER_NAME(MEMBER_NAME);
        this.setMEMBER_CONTACT_INFO(MEMBER_CONTACT_INFO);
        this.setBall1(ball1);
        this.setBall2(ball2);
        this.setBall3(ball3);
        this.setBall4(ball4);
        this.setBall5(ball5);
        this.setLuckyStar1(LuckyStar1);
        this.setLuckyStar2(LuckyStar2);
        this.setCHANGED_ymdhms(CHANGED_ymdhms);
    }

    public SyndicateMember() {

    }
}
