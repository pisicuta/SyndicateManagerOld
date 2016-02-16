package models;

/**
 * Created by Paul on 10/11/2015.
 */
public class EuroDraw extends LotteryBase {

    private int id;
    private String EdDrawDate;
    private String EdNumberOfWinningRows;
    private String luckyStar1;
    private String luckyStar2;
    private String CreatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEdDrawDate() {
        return EdDrawDate;
    }

    public void setEdDrawDate(String edDrawDate) {
        EdDrawDate = edDrawDate;
    }

    public String getEdNumberOfWinningRows() {
        return EdNumberOfWinningRows;
    }

    public void setEdNumberOfWinningRows(String edNumberOfWinningRows) {
        EdNumberOfWinningRows = edNumberOfWinningRows;
    }

    public String getLuckyStar1() {
        return luckyStar1;
    }

    public void setLuckyStar1(String luckyStar1) {
        this.luckyStar1 = luckyStar1;
    }

    public String getLuckyStar2() {
        return luckyStar2;
    }

    public void setLuckyStar2(String luckyStar2) {
        this.luckyStar2 = luckyStar2;
    }


    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }
}
