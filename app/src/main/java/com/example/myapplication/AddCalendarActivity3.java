package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class AddCalendarActivity3 extends AppCompatActivity {

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

    SimpleCursorAdapter userAdapter;
    SQLiteDatabase stDB;
    DBcontrol databaseHelper;

    ArrayList<String> NameSr2 = new ArrayList<>();
    ArrayList<String> LastNamet2 = new ArrayList<>();
    ArrayList<String> Aget2 = new ArrayList<>();
    ArrayList<String> Lesst2 = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add_calendar3);
        currentDateTime = findViewById(R.id.currentDateTime);
        setInitialDateTime();

        String NameSr, LastNamet, Classt, Lesst;
        String Aget;
        int avatarT;

        databaseHelper = new DBcontrol(getApplicationContext());
        stDB = databaseHelper.getWritableDatabase();



        ArrayList<String> NameSr1 = new ArrayList<>();
        ArrayList<String> LastNamet1 = new ArrayList<>();
        ArrayList<String> Classt1 = new ArrayList<>();
        ArrayList<String> Lesst1 = new ArrayList<>();
        ArrayList<String> Aget1 = new ArrayList<>();
        ArrayList<Integer> avatarsT = new ArrayList<>();


        Cursor cursor = stDB.query("student", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String firstname = cursor.getString(cursor.getColumnIndex("Name"));
                @SuppressLint("Range") String lastname = cursor.getString(cursor.getColumnIndex("LastName"));
                @SuppressLint("Range") String Class = cursor.getString(cursor.getColumnIndex("Class"));
                @SuppressLint("Range") String Less = cursor.getString(cursor.getColumnIndex("Less"));
                @SuppressLint("Range") String Age = cursor.getString(cursor.getColumnIndex("Age"));
                NameSr1.add(firstname + " " + lastname);
                LastNamet1.add(lastname);
                if (Class.equals("Вне школьной программы") || Class.equals("-")) {
                } else {
                    Class = Class + " класс.";
                }
                Classt1.add(Class);
                Lesst1.add(Less);
                Aget1.add((Age));
                avatarsT.add(R.drawable.avatar);
                NameSr2.add(firstname);
                LastNamet2.add(lastname);
                Aget2.add(Age);
                Lesst2.add(Less);
            }
            cursor.close();

            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, NameSr1);
            ((Spinner) findViewById(R.id.spinnerStudentC)).setAdapter(spinnerAdapter);
        }
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(AddCalendarActivity3.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(AddCalendarActivity3.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {

        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    public void saveaddcl(View view) {


        Cursor cursor;
//        int ageTextInt = Integer.parseInt(ageText);


        stDB = databaseHelper.getWritableDatabase();

        Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_done_white_48dp);
        Bitmap ErrorImg = BitmapFactory.decodeResource(getResources(),
                android.R.drawable.stat_notify_error);
        CircularProgressButton btn = (CircularProgressButton) findViewById(R.id.button10);
        btn.startAnimation();

        Spinner spinner1 = findViewById(R.id.spinnerStudentC);

        String[] textTI = spinner1.getSelectedItem().toString().split(" ");

//        int studentId = (getIndex(spinner1, textTI));

        cursor = stDB.rawQuery("SELECT id FROM student WHERE Name = '"+textTI[0]+"' AND LastName = '"+textTI[1]+"'", null);
        cursor.moveToFirst();
//        @SuppressLint("Range") int studentId = cursor.getInt(cursor.getColumnIndex("id"));
        @SuppressLint("Range") String studentId = cursor.getString(cursor.getColumnIndex("id"));
        cursor.close();

        int studentIdint = Integer.parseInt(studentId);

        Date currentTime = Calendar. getInstance(). getTime();


        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");



        ContentValues contentValues = new ContentValues();
        contentValues.put("students", studentId);
        contentValues.put("data", DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        contentValues.put("time", DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_YEAR));
        contentValues.put("deltime", format.format(dateAndTime.getTime()));
//        contentValues.put("deltime", ageText);

        stDB.insert("Calendar", null, contentValues);

//        dBcontrol.insertS(stDB, NameText, LastNameText, per.getclassDataI(), lessText, ageTextInt);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                btn.doneLoadingAnimation(321300,bmOriginal);


            }
        },4000);


        new Handler().postDelayed(new Runnable() {
            public void run() {

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Успешно", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(AddCalendarActivity3.this, CalendarActivity.class));
                closeActivity();


            }
        },5500);




        stDB.close();
    }
    private void closeActivity() {
        this.finish();
    }



    //private method of your class
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public void StartDelST(View view) {
    }
}