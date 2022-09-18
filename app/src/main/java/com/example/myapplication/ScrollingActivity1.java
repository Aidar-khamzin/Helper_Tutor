package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Person1 {
    String name;
    int avatar_res; // имя ресурса аватара
    String num1; // состояние в строковом формате
    String clsh;

    public Person1(String name, String num, String clsh, int avatar_res) {
        this.avatar_res = avatar_res;
        this.clsh = clsh;
        this.num1 = num;
        this.name = name;
    }
}

class MyAdapter1 extends ArrayAdapter<Person1>{

    public MyAdapter1(@NonNull Context context, ArrayList<Person1> mas1) {
        super(context, R.layout.list_item, mas1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person1 person1 = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(person1.name);
//        ((TextView) convertView.findViewById(R.id.num)).setText(person1.num);
        ((TextView) convertView.findViewById(R.id.classh)).setText(person1.clsh);

        ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(person1.avatar_res);

        return convertView;
    }
}

public class ScrollingActivity1 extends AppCompatActivity {
    SimpleCursorAdapter userAdapter;
    SQLiteDatabase stDB;
    DBcontrol databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

//        Cursor cursorU;
//        cursorU = DBcontrol.selectS("student");
        databaseHelper = new DBcontrol(getApplicationContext());
        stDB = databaseHelper.getWritableDatabase();


        String[] NameSr = new String[0], LastNamet = new String[0], Classt = new String[0], Lesst = new String[0];
        int[] Aget = new int[0];

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
                NameSr1.add(firstname);
                LastNamet1.add(lastname);
                Classt1.add(Class);
                Lesst1.add(Less);
                Aget1.add(Age);
                avatarsT.add(R.drawable.avatar);
            }
            cursor.close();

//        String[] headers = new String[] {"Name", "LastName", "Age"};

            String[] num = {"13 лет", "2 лет", "41 лет"};
            String[] ClassSH = {"2 класс", "15 класс", "51 класс"};
            int[] avatars = {R.drawable.avatar, R.drawable.avatar, R.drawable.avatar};

            ArrayList<Person1> mas1 = new ArrayList<>();
            for (int i = 0; i < NameSr1.size(); i++) {
                mas1.add(new Person1(NameSr1.get(i),
                        Aget1.get(i), Classt1.get(i), avatarsT.get(i)));
            }

//        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
//                cursorU, headers, new int[]{R.id.name, R.id.classh, R.id.num}, 0);
//        list.setAdapter(userAdapter);

            ListView list = findViewById(R.id.list);

            // Создаём адаптер ArrayAdapter, чтобы привязать массив к ListView
//            final ArrayAdapter<String> adapter;
//            adapter = new ArrayAdapter(this,
//                    android.R.layout.simple_list_item_1, mas);
            // Привяжем массив через адаптер к ListView
//            list.setAdapter(adapter);
//        list.setAdapter(userAdapter);
            list.setAdapter(new MyAdapter1(this, mas1));
        }
    }
}