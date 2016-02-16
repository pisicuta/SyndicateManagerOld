package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.LotteryChoice;

/**
 * Created by Paul on 13/02/2016.
 */
public class LotteryChoiceDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
//    private String[] allColumns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_COMMENT };

    public LotteryChoiceDataSource(Context context) {
//        dbHelper = new DatabaseHelper(context);
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Adding new lottery_choice
    public long addChoice(LotteryChoice lc) {
        String TABLE_NAME = "LOTTERY_CHOICE";
        String LOTTERY_KEY = "";
        String SYNDICATE_NAME = "";
        String CHANGED_ymdhms = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());

        SQLiteDatabase db = this.database;

        ContentValues values = new ContentValues();
        values.put(LOTTERY_KEY, lc.getLOTTERY_KEY());
        values.put(SYNDICATE_NAME, lc.getSYNDICATE_NAME()); // Syndicate Member Name
        values.put(CHANGED_ymdhms, currentDateandTime);
        // Inserting Row
        long _id = db.insert(TABLE_NAME, null, values);

        db.close(); // Closing database connection
        return _id;
    }

    public LotteryChoice getLotteryChoice (int _id) {
        String TABLE_NAME = "LOTTERY_CHOICE";
        String LOTTERY_KEY = "";
        String SYNDICATE_NAME = "";
        String CHANGED_ymdhms = "";

        SQLiteDatabase db = this.database;


        String[] projection = {
                "_id",
                LOTTERY_KEY,
                SYNDICATE_NAME,
                CHANGED_ymdhms
        };

        String[] selection = { String.valueOf(_id) };
        String[] selectionArgs = { "=?" };

        Cursor cursor = db.query(
                TABLE_NAME,          //table to query
                projection,
                String.valueOf(selection),
                selectionArgs,
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        if (cursor != null)
            cursor.moveToFirst();

        LotteryChoice lc =  new LotteryChoice(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), cursor.getString(3));
//LotteryChoice(int _id, int LOTTERY_KEY, String SYNDICATE_NAME, String CHANGED_ymdhms) {

        db.close(); // Closing database connection
        // return lottery choice
        return lc;

    }

}
