package com.example.databaseapp.Position;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class OpenHelperPosition extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DbExample";
    private static final int    DATABASE_VERSION = 2;

    private static final String TABLE_POSITION = "position";


    public OpenHelperPosition(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table " + TABLE_POSITION + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name Text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSITION);
        onCreate(db);
    }
}
