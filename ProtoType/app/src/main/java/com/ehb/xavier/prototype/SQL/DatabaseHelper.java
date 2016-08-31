package com.ehb.xavier.prototype.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_TELNR = "telnr";
    public static final String KEY_GSM = "gsm";


    public static final String KEY_USERNAME = "usename";
    public static final String KEY_PASSWORD = "password";

    public static final String KEY_NAME = "name";
    public static final String KEY_SELECT = "select";

    private static final String DATABASE_NAME = "WoonZorg";
    private static final String SQLITE_TABLE_Contact = "Contact";
    private static final String SQLITE_TABLE_User = "User";
    private static final String SQLITE_TABLE_MenuKeuze = "MenuKeuze";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "DB";



    private static final String DATABASE_CREATE_CONTACT =
            "CREATE TABLE if not exists " + SQLITE_TABLE_Contact + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_FIRSTNAME + "," +
                    KEY_LASTNAME +","+
                    KEY_TELNR + ","+
                    KEY_GSM +

                    ");";
    private static final String DATABASE_CREATE_USER =
            "CREATE TABLE if not exists " + SQLITE_TABLE_User + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_FIRSTNAME + "," +
                    KEY_LASTNAME + "," +
                    KEY_USERNAME + "," +
                    KEY_PASSWORD  +

                    ");";
    private static final String DATABASE_CREATE_MENUK =
            "CREATE TABLE if not exists " + SQLITE_TABLE_MenuKeuze + " (" +
                    KEY_ROWID + " integer PRIMARY KEY autoincrement," +
                    KEY_NAME+ "," +
                    KEY_SELECT + "," +

                    ");";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE_CONTACT);
            db.execSQL(DATABASE_CREATE_CONTACT);

            Log.w(TAG, DATABASE_CREATE_USER);
            db.execSQL(DATABASE_CREATE_USER);

            Log.w(TAG, DATABASE_CREATE_MENUK);
            //db.execSQL(DATABASE_CREATE_MENUK);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_Contact);
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_User);
            db.execSQL("DROP TABLE IF EXISTS " + SQLITE_TABLE_MenuKeuze);
            onCreate(db);
        }








}
