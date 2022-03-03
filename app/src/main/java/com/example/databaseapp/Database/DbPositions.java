package com.example.databaseapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseapp.Position.OpenHelperPosition;
import com.example.databaseapp.Position.Position;

import java.util.ArrayList;

public class DbPositions {

    private final static String tableName = "position";
    private SQLiteDatabase db;

    public DbPositions(Context ctx){
        OpenHelperPosition openHelperPosition = new OpenHelperPosition(ctx);
        this.db = openHelperPosition.getWritableDatabase();
    }


    public long insert(Position position){

        ContentValues contentValues = new ContentValues();

        contentValues.put("name", position.getName());

        return db.insert(tableName, null, contentValues);
    }


    public long[] insertMany(ArrayList<Position> positions){

        long[] numbers = new long[positions.size()];

        positions.forEach(position -> {
            this.insert(position);
        });

        return numbers;
    }

    public ArrayList<Position> getMany(){

        Cursor cursor = db.rawQuery("select * from " + tableName + " order by id Desc", null);
        ArrayList<Position> positions = new ArrayList<>();

        cursor.moveToNext();

        if(!cursor.isAfterLast()){
            do{
                Position position = new Position(
                    cursor.getLong(0),
                    cursor.getString(1)
                );

                positions.add(position);
            } while(cursor.moveToNext());
        }

        return positions;
    }
}
