package dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

import models.LotteryDraw;
import models.Lottery;
import models.LotteryChoice;
import models.SyndicateMember;
import models.MemberPaid;
import models.MemberWins;

/**
 * Created by Paul on 19/01/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database path
    private static String DB_PATH = "/data/data/com.example.paul.syndicatemanager/databases/";
    // Database Name
    private static final String DATABASE_NAME = "syndicateManager";

    // Table names
    private static final String TABLE_NAME = "SYNDICATE_MEMBER";

    private SQLiteDatabase myDataBase;

    private static Context myContext;

    private static DatabaseHelper sInstance;

    private String myPath = DB_PATH + DATABASE_NAME;

     public static synchronized DatabaseHelper getInstance(Context context) {

         // Use the application context, which will ensure that you
         // don't accidentally leak an Activity's context.
         // See this article for more information: http://bit.ly/6LRzfx
         if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
             myContext = context;
         }
         return sInstance;
     }


    /**
     *
     * Constructor
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */

     private DatabaseHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
     }




    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        Log.e("CREATE DATABASE", "Database exists = " +  String.valueOf(dbExist));
        if(dbExist){
            //do nothing - database already exist
            openDataBase();


        }else{

            Log.e("CREATE DATABASE","Database does not exist");
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
//            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            Log.e("CHECK DATABASE","Database does not exist");
            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DATABASE_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

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


        SQLiteDatabase db = this.getWritableDatabase();

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
        SQLiteDatabase db = this.getWritableDatabase();

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
    public ArrayList<SyndicateMember> getAllSyndicateMembers() {

        ArrayList<SyndicateMember> syndicateMemberList = new ArrayList<>();
        String TABLE_NAME = "SYNDICATE_MEMBER";

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();

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
// closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}

