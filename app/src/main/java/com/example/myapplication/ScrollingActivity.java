package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

class Person {
    String name;
    int avatar_res; // имя ресурса аватара
//    String num; // состояние в строковом формате
    String clsh;

    public Person(String name, String clsh, int avatar_res) {
        this.avatar_res = avatar_res;
        this.clsh = clsh;
//        this.num = num;
        this.name = name;
    }
}

class MyAdapter extends ArrayAdapter<Person>{

    public MyAdapter(@NonNull Context context, ArrayList<Person> mas) {
        super(context, R.layout.list_item, mas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(person.name);
//        ((TextView) convertView.findViewById(R.id.num)).setText(person.num);
        ((TextView) convertView.findViewById(R.id.classh)).setText(person.clsh);

        ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(person.avatar_res);

        return convertView;
    }
}

public class ScrollingActivity extends AppCompatActivity {
    SimpleCursorAdapter userAdapter;
    SQLiteDatabase stDB;
    DBcontrol databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        ListView list = findViewById(R.id.list);
//        Cursor cursorU;
//        cursorU = DBcontrol.selectS("student");
        databaseHelper = new DBcontrol(getApplicationContext());
        stDB = databaseHelper.getWritableDatabase();


        String NameSr, LastNamet, Classt, Lesst;
        String Aget; int avatarT;

        ArrayList<String> NameSr1 = new ArrayList<>();
        ArrayList<String> LastNamet1 = new ArrayList<>();
        ArrayList<String> Classt1 = new ArrayList<>();
        ArrayList<String> Lesst1 = new ArrayList<>();
        ArrayList<String> Aget1 = new ArrayList<>();
        ArrayList<Integer> avatarsT = new ArrayList<>();

        stDB.execSQL("CREATE TABLE IF NOT EXISTS student (id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, LastName TEXT, Class TEXT, Less TEXT, Age INTEGER)");


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
//                TextView textView = (TextView) itemClicked;
//                String strText = textView.getText().toString(); // получаем текст нажатого элемента

                if(id == 0) {
                    // Запускаем активность, связанную с определенным именем кота
                    startActivity(new Intent(ScrollingActivity.this, MainActivity2.class));
                    closeActivity();
                }
            }
        });




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
            }
        }
        cursor.close();

//        String[] headers = new String[] {"Name", "LastName", "Age"};

            String[] num = {"13 лет", "2 лет", "41 лет"};
            String[] ClassSH = {"2 класс", "15 класс", "51 класс"};
            int[] avatars = {R.drawable.avatar, R.drawable.avatar, R.drawable.avatar};


//                                         Добавляю кнопку, чтобы не забыл
            ArrayList<Person> mas = new ArrayList<>();

            mas.add(new Person("Добавить ученика",
                    "", android.R.drawable.ic_menu_add));

            for (int i = 0; i < NameSr1.size(); i++) {
                NameSr=NameSr1.get(i);
                Aget= (Aget1.get(i));
                Classt=Lesst1.get(i)+", "+Aget1.get(i)+" Лет, "+Classt1.get(i);
                avatarT=avatarsT.get(i);
                mas.add(new Person(NameSr,
                        Classt, avatarT));
            }

//        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
//                cursorU, headers, new int[]{R.id.name, R.id.classh, R.id.num}, 0);
//        list.setAdapter(userAdapter);


//        list.setAdapter(userAdapter);
            list.setAdapter(new MyAdapter(this, mas));

    }


    private void closeActivity() {
        this.finish();
    }


//    public void onClick(View view) {
//        ListView list = findViewById(R.id.list);
//        int activePosition = 0; // первый элемент списка
//        list.performItemClick(list.getAdapter().
//                getView(activePosition, null, null), activePosition, list.getAdapter().
//                getItemId(activePosition));
//    }
}