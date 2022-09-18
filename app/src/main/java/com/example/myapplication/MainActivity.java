package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
    }

    public void progeny(View view) {
        Intent intent = new Intent(MainActivity.this,ScrollingActivity.class);
        startActivity(intent);
    }

    public void addst(View view) {
        Intent intent = new Intent(MainActivity.this,AddCalendarActivity3.class);
        startActivity(intent);
    }

    public void startcall(View view) {
        Intent intent = new Intent(MainActivity.this,CalendarActivity.class);
        startActivity(intent);
    }
}