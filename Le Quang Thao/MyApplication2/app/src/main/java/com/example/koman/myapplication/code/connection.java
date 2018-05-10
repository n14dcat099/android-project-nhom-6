package com.example.koman.myapplication.code;

import android.content.Context;
import android.content.ContentValues;
import java.util.ArrayList;
import java.util.List;
import com.example.koman.myapplication.code.deathnote;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by koman on 4/20/2018.
 */

public class connection extends SQLiteOpenHelper
{
    //Database
    private static final String TAG = "SQLite";
    //Version
    private static final int version = 1;
    //Database name
    private static final String database_name = "demo_app";
    //Table name
    private static final String table_name = "death_note";
    //Column names
    private static final String id_col = "id";
    private static final String name_col = "name";
    private static final String reason_col = "reason";

    public connection(Context context)
    {
        super(context,database_name,null,version);
    }

    //Create table
    public void onCreate(SQLiteDatabase db)
    {
        //CREATE TABLE demo_app (id INTEGER PRIMARY KEY,name TEXT,reason TEXT)
        String sql = "CREATE TABLE " + table_name + "(" + id_col + " INTEGER PRIMARY KEY," + name_col + " TEXT," + reason_col + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion)
    {
        //Drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        //recreate
        onCreate(db);
    }
    //count target
    public int countTarget()
    {
        String sql = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void addTarget(deathnote target)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Insert new record to database.
        ContentValues victim = new ContentValues();
        //Insert victim name
        victim.put(name_col,target.getName());
        //Insert victim reason
        victim.put(reason_col, target.getReason());
        //Insert victim to table
        db.insert(table_name,null,victim);
        db.close();
    }
    //Create readme
    public void createReadme()
    {
        int count = this.countTarget();
        if ( count == 0 )
        {
            deathnote readme = new deathnote("Shinigami (The Unkillable)","Hi master, write out the one that you want him to be dead");
            this.addTarget(readme);
        }
    }

    //Get target
    public deathnote getTarget(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_name,new String[] {id_col,name_col,reason_col},id_col + "=?", new String[] {String.valueOf(id)},null,null,null,null);
        if (cursor != null)
        {
            //Get the first fetching record.
            cursor.moveToFirst();
        }
        //0 => id , 1 => name, 2 => reason
        deathnote target = new deathnote(Integer.parseInt(cursor.getString(0)) ,cursor.getString(1),cursor.getString(2));
        return target;
    }

    //Get All target
    public List<deathnote> getAllTarget()
    {
        List<deathnote> victimList = new ArrayList<deathnote>();
        String sql = "SELECT * FROM " + table_name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if ( cursor.moveToFirst())
        {
            do
            {
                deathnote victim = new deathnote();
                //Get current target info
                victim.setId(Integer.parseInt(cursor.getString(0)));
                victim.setName(cursor.getString(1));
                victim.setReason(cursor.getString(2));
                //Add to list
                victimList.add(victim);
            }
            while(cursor.moveToNext());
        }

        return victimList;
    }

    //Update to database when edited.
    public int updateTarget(deathnote target)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //Insert new record to database
        ContentValues victim = new ContentValues();
        //Put name
        victim.put(name_col, target.getName());
        //Put reason
        victim.put(reason_col, target.getReason());
        //Update target
        return db.update(table_name,victim,id_col + " = ?", new String[] {String.valueOf(target.getId())});
    }

    public void deleteTarget(deathnote target)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,id_col + " = ?", new String[] {String.valueOf(target.getId())});
        db.close();
    }
}
