package com.example.databaseapp.Worker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME    = "DbExample";
    private static final int    DATABASE_VERSION = 1;

    private static final String TABLE_WORKER = "worker";

    public OpenHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table " + TABLE_WORKER
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, dateBorn DATE, positionId INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKER);
        onCreate(db);
    }
}
