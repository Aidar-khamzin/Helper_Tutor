package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;


public class MainActivity2 extends AppCompatActivity {

    String[] lessons = {"Английский язык", "Математика", "Русский язык", "Физика", "Химия", "Обществознание", "Биология", "Литература", "Логопед", "Общая подготовка", "Чтение", "Музыка", "ИЗО", "Родной язык", "Иностранный язык", "Немецский язык", "Французский язык", "История", "География", "Информатика", "Черчение", "Алгебра", "Геометрия", "Экономика", "Экология", "Астрономия"};
    String[] classAS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "Вне школьной программы", "-"};
    static String classData;
    static String lessData;
    DBcontrol databaseHelper;
    SQLiteDatabase stDB;
    Cursor userCursor;
    public static String classDataI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        TextView selection = findViewById(R.id.textView2);
//        TextView selection1 = findViewById(R.id.textView3);



        Spinner spinner1 = findViewById(R.id.spinnerClassAddST);
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classAS);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);




        databaseHelper = new DBcontrol(getApplicationContext());






        Perehod per = new Perehod();

        String[] classData = new String[1];
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

//                String[] choose = getResources().getStringArray(R.id.spinnerClassAddST);

                classData[0] = classAS[selectedItemPosition];
                classDataI = classData[0];
                per.setclassDataI(classDataI);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



    }



    public void StartDelST(View view) {
        Intent intent = new Intent(MainActivity2.this,DeleteActivity.class);
        startActivity(intent);
    }

    static class Perehod {
        String classDataI1 = classDataI;
        public void setclassDataI(String classDataI) {
            classDataI1 = classDataI;
        }
        public String getclassDataI() {

            return classDataI1;
        }
    }

    public String classDataIH = classDataI;

    @SuppressLint("ResourceAsColor")
    public void sendaddst(View view) throws InterruptedException {

        EditText name = (EditText) findViewById(R.id.editNameAddST);
        EditText LastName = (EditText) findViewById(R.id.editLastNameAddST);
        EditText less = (EditText) findViewById(R.id.editLessAddST);
        EditText age = (EditText) findViewById(R.id.editAgeAddST);
//        TextView selection1 = findViewById(R.id.textView3);

        String NameText = name.getText().toString();
        String LastNameText = LastName.getText().toString();
        String lessText = less.getText().toString();
        String ageTextST = age.getText().toString();
        int ageText = 0;
        if (ageTextST.length() > 0) {
            ageText = Integer.parseInt(age.getText().toString().trim());
        }
//        int ageTextInt = Integer.parseInt(ageText);

        Spinner spinner1 = findViewById(R.id.spinnerClassAddST);
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, classAS);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Perehod per = new Perehod();

        stDB = databaseHelper.getWritableDatabase();

        Bitmap bmOriginal = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_done_white_48dp);
        Bitmap ErrorImg = BitmapFactory.decodeResource(getResources(),
                android.R.drawable.stat_notify_error);
        CircularProgressButton btn = (CircularProgressButton) findViewById(R.id.button4);
        btn.startAnimation();
        if (NameText.length() < 1 || LastNameText.length() < 1 || lessText.length() < 1 || ageTextST.length() < 1) {
            Log.e("e", "NameText: "+NameText);
            Log.e("e", "LastNameText: "+LastNameText);
            Log.e("e", "lessText: "+lessText);
            Log.e("e", "ageText: "+ ageText);
            TextView ErrorText = findViewById(R.id.ErrorText);
            ErrorText.setText("Введены не все поля");
//            btn.revertAnimation();
//            btn.setBackgroundColor(R.color.red);
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    btn.doneLoadingAnimation(321300,ErrorImg);

                }
            },500);
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    btn.revertAnimation();
                    ErrorText.setText("");

                }
            },10000);
            return;
        }



//        selection1.setText(spinner1.getSelectedItem().toString());

//        btn.setBackgroundColor(R.color.white);

//        DBcontrol dBcontrol = new DBcontrol(getApplicationContext());

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", NameText);
        contentValues.put("LastName", LastNameText);
        contentValues.put("Less", lessText);
        contentValues.put("Age", ageText);
        contentValues.put("Class", per.getclassDataI());

        stDB.insert("student", null, contentValues);

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
                startActivity(new Intent(MainActivity2.this, ScrollingActivity.class));
                closeActivity();


            }
        },5500);




        stDB.close();


    }

    private void closeActivity() {
        this.finish();
    }
    public SQLiteDatabase getDBContex(){
        return stDB;
    }
}
