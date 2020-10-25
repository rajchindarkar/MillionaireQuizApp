package com.dc.msu.wwbm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
// - class responsible for DB operations
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "quiz.db";
    public static final String TABLE_NAME = "quiz_table";
    public static final String col1 = "QUESTION";
    public static final String col2 = "ANSWER";
    public static final String col3 = "OPTIONS";
    public static final String col4 = "ID";
    public static final String col5 = "PRIZE";
    Context mContext;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER primary key AUTOINCREMENT,QUESTION text,ANSWER text,OPTIONS text,PRIZE text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean insert(String q, String a, String o, String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1, q);
        contentValues.put(col2, a);
        contentValues.put(col3, o);
        contentValues.put(col5, p);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor get() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        res = db.rawQuery("select * from quiz_table ", null);
        return res;
    }
}
