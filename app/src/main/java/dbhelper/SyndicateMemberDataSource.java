package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.SyndicateMember;
/**
 * Created by Paul on 13/02/2016.
 */
public class SyndicateMemberDataSource {

    // Database fields
    private SQLiteDatabase database;
    private static DatabaseHelper dbHelper;
    private static final String TABLE_NAME = "SYNDICATE_MEMBER";
//    private String[] allColumns = { DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_COMMENT };

    public SyndicateMemberDataSource(Context context) {

        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }

    // Adding new syndicate_member
    public long addSynsdicateMember(SyndicateMember sm) {

        String MEMBER_ID = "";
        String LOTTERY_CHOICE_KEY = "";
        String MEMBER_NAME = "";
        String MEMBER_CONTACT_INFO = "";
        String ball1 = "";
        String ball2 = "";
        String ball3 = "";
        String ball4 = "";
        String ball5 = "";
        String LuckyStar1 = "";
        String LuckyStar2 = "";
        String CHANGED_ymdhms = "";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateandTime = sdf.format(new Date());


        SQLiteDatabase db = this.database;

        ContentValues values = new ContentValues();
        values.put(LOTTERY_CHOICE_KEY, sm.getLOTTERY_CHOICE_KEY());
        values.put(MEMBER_NAME, sm.getMEMBER_NAME()); // Syndicate Member Name
        values.put(MEMBER_CONTACT_INFO, sm.getMEMBER_CONTACT_INFO());
        values.put(ball1, sm.getBall1());
        values.put(ball2, sm.getBall2());
        values.put(ball3, sm.getBall3());
        values.put(ball4, sm.getBall4());
        values.put(ball5, sm.getBall5());
        values.put(LuckyStar1, sm.getLuckyStar1());
        values.put(LuckyStar2, sm.getLuckyStar2());
        values.put(CHANGED_ymdhms, currentDateandTime);

        long _id = db.insert(TABLE_NAME, null, values);

        db.close(); // Closing database connection
        return _id;
    }

    // Retreive one syndicate member
    public SyndicateMember getSyndicateMember(int _id) {
        String TABLE_NAME = "SYNDICATE_MEMBER";
        String MEMBER_ID = "";
        String LOTTERY_CHOICE_KEY = "";
        String MEMBER_NAME = "";
        String MEMBER_CONTACT_INFO = "";
        String ball1 = "";
        String ball2 = "";
        String ball3 = "";
        String ball4 = "";
        String ball5 = "";
        String LuckyStar1 = "";
        String LuckyStar2 = "";
        String CHANGED_ymdhms = "";
        SQLiteDatabase db = this.database;

        String[] projection = {
                "_id",
                MEMBER_ID,
                LOTTERY_CHOICE_KEY,
                MEMBER_NAME,
                MEMBER_CONTACT_INFO,
                ball1,
                ball2,
                ball3,
                ball4,
                ball5,
                LuckyStar1,
                LuckyStar2,
                CHANGED_ymdhms
        };

        String[] selection = {String.valueOf(_id)};
        String[] selectionArgs = {"=?"};

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

        SyndicateMember sm = new SyndicateMember(Integer.parseInt(cursor.getString(0)), //_id
                Integer.parseInt(cursor.getString(1)),                                  //MEMBER_ID
                Integer.parseInt(cursor.getString(2)),                                  //LOTTERY_CHOICE_KEY
                cursor.getString(3),                                                    // MEMBER_NAME
                cursor.getString(4),                                                    // MEMBER_CONTACT_INFO
                cursor.getString(5),                                                    // ball1
                cursor.getString(6),                                                    // ball2
                cursor.getString(7),                                                    // ball3
                cursor.getString(8),                                                    // ball4
                cursor.getString(9),                                                    // ball5
                cursor.getString(10),                                                   // LuckStar1
                cursor.getString(11),                                                   // LuckStar2
                cursor.getString(12));                                                   // CHANGED_ymdhms

//SyndicateMember(int _id, int LOTTERY_KEY, String SYNDICATE_NAME, String CHANGED_ymdhms) {

        db.close(); // Closing database connection
        // return lottery choice
        return sm;

    }

    // Retreive all syndicate member List<Contact> getAllContacts()
    public static ArrayList<SyndicateMember> getAllSyndicateMembers() {

        ArrayList<SyndicateMember> syndicateMemberList = new ArrayList<>();
        String TABLE_NAME = "SYNDICATE_MEMBER";

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                SyndicateMember sm = new SyndicateMember();
                sm.set_id(Integer.parseInt(cursor.getString(0)));
                sm.setMEMBER_ID(Integer.parseInt(cursor.getString(1)));
                sm.setLOTTERY_CHOICE_KEY(Integer.parseInt(cursor.getString(2)));
                sm.setMEMBER_NAME(cursor.getString(3));
                sm.setMEMBER_CONTACT_INFO(cursor.getString(4));
                sm.setBall1(cursor.getString(5));
                sm.setBall2(cursor.getString(6));
                sm.setBall3(cursor.getString(7));
                sm.setBall4(cursor.getString(8));
                sm.setBall5(cursor.getString(9));
                sm.setLuckyStar1(cursor.getString(10));
                sm.setLuckyStar2(cursor.getString(11));
                sm.setLuckyStar2(cursor.getString(12));
                // Adding contact to list
                syndicateMemberList.add(sm);
            } while (cursor.moveToNext());
        }

        // return SyndicateMember list
        return syndicateMemberList;

    }
}