package com.mmt.shubh.datastore.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * TODO:Add class comment.
 * <p>
 * Created by shubham,
 * on 12/19/15,
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tasks.db";
    public static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            //Uncomment line below if you want to enable foreign keys
            db.execSQL("PRAGMA foreign_keys=ON;");
            db.execSQL(Tables.createTaskBoardTable());
            db.execSQL(Tables.createTaskTable());
            db.execSQL(Tables.createNoteTable());
            //Add other tables here
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTaskTable() {

    }
}
