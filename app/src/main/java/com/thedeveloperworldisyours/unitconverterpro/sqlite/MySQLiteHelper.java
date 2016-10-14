package com.thedeveloperworldisyours.unitconverterpro.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by javierg on 23/08/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_RATE = "rates";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COIN = "coin";
    public static final String COLUMN_VALUE = "value";

    public static final String TABLE_AREA = "area";
    public static final String COLUMN_AREA_ID = "_id";
    public static final String COLUMN_AREA_NAME = "area";
    public static final String COLUMN_AREA_VALUE = "value";
    public static final String COLUMN_AREA_POSITION = "position";

    private static final String DATABASE_NAME = "units.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE_CURRENCY = "create table "
            + TABLE_RATE + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COIN
            + " text not null," + COLUMN_VALUE + " real);";

    // Database creation sql statement
    private static final String DATABASE_CREATE_AREA = "create table "
            + TABLE_AREA + "( " + COLUMN_AREA_ID
            + " integer primary key autoincrement, " + COLUMN_AREA_NAME
            + " text not null," + COLUMN_AREA_POSITION + " integer, "
            + COLUMN_AREA_VALUE + " real);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_CURRENCY);
        database.execSQL(DATABASE_CREATE_AREA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATE);
        onCreate(db);
    }

}