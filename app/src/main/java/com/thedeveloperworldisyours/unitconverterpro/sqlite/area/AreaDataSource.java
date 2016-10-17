package com.thedeveloperworldisyours.unitconverterpro.sqlite.area;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.thedeveloperworldisyours.unitconverterpro.sqlite.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javierg on 14/10/2016.
 */

public class AreaDataSource {
    private SQLiteDatabase mDatabase;
    private MySQLiteHelper mDbHelper;
    private String[] mAllColumns = {MySQLiteHelper.COLUMN_AREA_ID, MySQLiteHelper.COLUMN_AREA_NAME, MySQLiteHelper.COLUMN_AREA_VALUE, MySQLiteHelper.COLUMN_AREA_POSITION};

    public AreaDataSource(Context context) {
        mDbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Area createArea(String name, Long value, int position) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_AREA_NAME, name);
        values.put(MySQLiteHelper.COLUMN_AREA_VALUE, value);
        values.put(MySQLiteHelper.COLUMN_AREA_POSITION, position);

        long insertId = mDatabase.insert(MySQLiteHelper.TABLE_AREA, null, values);
        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_AREA, mAllColumns, MySQLiteHelper.COLUMN_AREA_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Area newComment = cursorToArea(cursor);
        cursor.close();
        return newComment;
    }

    public void deleteAll() {
        mDatabase.delete(MySQLiteHelper.TABLE_AREA, null, null);
    }

    public void deleteArea(Area area) {
        long id = area.getId();
        mDatabase.delete(MySQLiteHelper.TABLE_AREA, MySQLiteHelper.COLUMN_AREA_ID + " = "+ id, null);
    }

    public List<Area> getAllAreas() {
        List<Area> areas = new ArrayList<>();
        Cursor cursor = mDatabase.query(MySQLiteHelper.TABLE_AREA,
                mAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Area area = cursorToArea(cursor);
            areas.add(area);
            cursor.moveToNext();
        }
        cursor.close();
        return areas;
    }

    private Area cursorToArea(Cursor cursor) {
        Area area = new Area();
        area.setId(cursor.getLong(0));
        area.setName(cursor.getString(1));
        area.setValue(cursor.getLong(2));
        area.setPosition(cursor.getInt(3));
        return area;
    }

}
