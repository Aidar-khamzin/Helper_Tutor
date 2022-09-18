package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBcontrol extends SQLiteOpenHelper {


    SQLiteDatabase stDB;
    Cursor cursor;
    DBcontrol databaseHelper;

    public DBcontrol(@Nullable Context context) {
        super(context, "ST1.db", null, 2);

    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student (" + "id"
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Name" + " TEXT, "
                + "LastName" + " TEXT, "
                + "Class" + " TEXT, "
                + "Less" + " TEXT, "
                + "Age" + " INTEGER);");
//        db.execSQL("CREATE TABLE Calendar (id       INTEGER PRIMARY KEY AUTOINCREMENT, students         REFERENCES student (id), data     DATE, time     DATETIME, deltime     DATETIME);");
//        db.execSQL("CREATE TABLE Calendar ("+
//                "id       INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                "students         REFERENCES student (id),"+
//                "data     DATE,"+
//                "time     DATETIME,"+
//                "deltime"+
//                ");");]
        db.execSQL("CREATE TABLE IF NOT EXISTS Calendar (    id       INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    students,\n" +
                "    data     DATE,\n" +
                "    time     DATETIME,\n" +
                "    deltime  DATETIME);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS student");
//        db.execSQL("DROP TABLE IF EXISTS Calendar");
        onCreate(db);

    }


//    public void insertS(SQLiteDatabase stDB, String Name, String LastName, String Class, String Less, int Age){
//        onCreate(stDB);
//        stDB.execSQL("INSERT INTO student (Name, LastName, Class, Less, Age) VALUES (\" "+Name+ "\", \"" + LastName + "\", \"" + Class + "\", \"" + Less + "\", \"" + Age +"\");");
//
//
//    }

    public static Cursor selectS(String Table){
        SQLiteDatabase stDB;
        Cursor cursor;
        DBcontrol databaseHelper;
        MainActivity2 main = new MainActivity2();
        stDB = main.getDBContex();
        cursor =  stDB.rawQuery("select * from "+ Table, null);
        return cursor;
    }


}
