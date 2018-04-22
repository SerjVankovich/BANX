package com.example.sergey.myapplication.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.Key;

/**
 * Created by sergey on 07.01.2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final int BASE_VERSION = 6;
    public static final String BASE_NAME = "contactsDb";
    public static final String TABLE_VKLADS = "vklads";
    public static final String TABLE_CREDITS = "credits";
    public static final String TABLE_CACHE = "cache";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_PERCENTS = "per_in_rub";
    public static final String KEY_SUM_IN_RUB = "sum_in_rub";
    public static final String KEY_SROK_IN_RUB = "srok_in_rub";
    public static final String KEY_BANK = "bank";
    public static final String KEY_LINK = "link";

    public DataBaseHelper(Context context) {
        super(context, BASE_NAME, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_VKLADS + "(" + KEY_ID + " integer primary key,"
                + KEY_BANK + " text," + KEY_TITLE + " text," + KEY_PERCENTS + " double," + KEY_SUM_IN_RUB + " integer,"
                + KEY_SROK_IN_RUB + " integer);");
        sqLiteDatabase.execSQL("create table " + TABLE_CREDITS + "(" + KEY_ID + " integer primary key,"
                + KEY_BANK + " text," + KEY_TITLE + " text," + KEY_PERCENTS + " double," + KEY_SUM_IN_RUB + " integer,"
                + KEY_SROK_IN_RUB + " integer);");
        sqLiteDatabase.execSQL("create table " + TABLE_CACHE + "(" + KEY_ID + " integer primary key,"
                + KEY_BANK + " text," + KEY_TITLE + " text," + KEY_PERCENTS + " double," + KEY_SUM_IN_RUB + " integer,"
                + KEY_SROK_IN_RUB + " integer," + KEY_LINK + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_VKLADS);
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_CREDITS);
        onCreate(sqLiteDatabase);
    }
}
