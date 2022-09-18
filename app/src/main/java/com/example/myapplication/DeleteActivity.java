package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class PersonD1 {
    String name;
    int avatar_res; // имя ресурса аватара
    //    String num; // состояние в строковом формате
    String clsh;

    public PersonD1(String name, String clsh, int avatar_res) {
        this.avatar_res = avatar_res;
        this.clsh = clsh;
//        this.num = num;
        this.name = name;
    }
}

class MyAdapterD1 extends ArrayAdapter<PersonD1> {

    private ArrayList<String> mCatNameList = new ArrayList<>();
    private HashMap<Integer, Boolean> mCheckedMap = new HashMap<>();
    private int position;
    private View convertView;
    private ViewGroup parent;

    public MyAdapterD1(@NonNull Context context, ArrayList<PersonD1> mas) {
        super(context, R.layout.list_itemdel, mas);

        for (int i = 0; i < mas.size(); i++) {
            mCheckedMap.put(i, false);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PersonD1 personD1 = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_itemdel, null);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(personD1.name);
//        ((TextView) convertView.findViewById(R.id.num)).setText(personD1.num);
        ((TextView) convertView.findViewById(R.id.classh)).setText(personD1.clsh);

        ((ImageView) convertView.findViewById(R.id.avatar)).setImageResource(personD1.avatar_res);


        this.position = position;
        this.convertView = convertView;
        this.parent = parent;
        View row = convertView;


        CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(R.id.checkBox);

        Boolean checked = mCheckedMap.get(position);
        if (checked != null) {
            checkedTextView.setChecked(checked);
        }

        return convertView;
//        return row;
    }

    void toggleChecked(int position) {
        if (mCheckedMap.get(position)) {
            mCheckedMap.put(position, false);
        } else {
            mCheckedMap.put(position, true);
        }

        notifyDataSetChanged();
    }

    public List<Integer> getCheckedItemPositions() {
        List<Integer> checkedItemPositions = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItemPositions).add(i);
            }
        }

        return checkedItemPositions;
    }

    List<String> getCheckedItems() {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(mCatNameList.get(i));
            }
        }

        return checkedItems;
    }
    List<String> getCheckedfirstname(ArrayList<String> firstname) {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(firstname.get(i));
            }
        }

        return checkedItems;
    }
    List<String> getCheckedlastname(ArrayList<String> lastname) {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(lastname.get(i));
            }
        }

        return checkedItems;
    }
    List<String> getCheckedage(ArrayList<String> age) {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(age.get(i));
            }
        }

        return checkedItems;
    }
    List<String> getCheckedless(ArrayList<String> less) {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(less.get(i));
            }
        }

        return checkedItems;
    }
    List<String> getCheckedid(ArrayList<String> id) {
        List<String> checkedItems = new ArrayList<>();

        for (int i = 0; i < mCheckedMap.size(); i++) {
            if (mCheckedMap.get(i)) {
                (checkedItems).add(id.get(i));
            }
        }

        return checkedItems;
    }

//    @NonNull
//    @Override
//    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
//        this.position = position;
//        this.convertView = convertView;
//        this.parent = parent;
//        View row = convertView;
//
////        if (row == null) {
//////            LayoutInflater inflater = getLayoutInflater();
//////            row = inflater.inflate(R.layout.list_item, parent, false);
////        }
//
//        CheckedTextView checkedTextView = (CheckedTextView) row
//                .findViewById(R.id.checkBox);
//        checkedTextView.setText(mCatNameList.get(position));
//
//        Boolean checked = mCheckedMap.get(position);
//        if (checked != null) {
//            checkedTextView.setChecked(checked);
//        }
//
//        return row;
//    }
//}
}

public class DeleteActivity extends AppCompatActivity {
    SimpleCursorAdapter userAdapter;
    SQLiteDatabase stDB;
    DBcontrol databaseHelper;
    private MyArrayAdapter mArrayAdapter;
    private ArrayList<String> mCatNameList = new ArrayList<>();
    private MyAdapterD1 MyAdapterD1;
    ArrayList<String> NameSr2 = new ArrayList<>();
    ArrayList<String> LastNamet2 = new ArrayList<>();
    ArrayList<String> Aget2 = new ArrayList<>();
    ArrayList<String> Lesst2 = new ArrayList<>();
    ArrayList<String> id2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ListView listD = findViewById(R.id.listDel1);

        databaseHelper = new DBcontrol(getApplicationContext());
        stDB = databaseHelper.getWritableDatabase();


        String NameSr, LastNamet, Classt, Lesst;
        String Aget;
        int avatarT;

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
                @SuppressLint("Range") String ids = cursor.getString(cursor.getColumnIndex("id"));
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
                id2.add(ids);
            }
            cursor.close();

            ArrayList<PersonD1> mas = new ArrayList<>();
            for (int i = 0; i < NameSr1.size(); i++) {
                NameSr = NameSr1.get(i);
                Aget = (Aget1.get(i));
                Classt = Lesst1.get(i) + ", " + Aget1.get(i) + " Лет, " + Classt1.get(i);
                avatarT = avatarsT.get(i);
                mas.add(new PersonD1(NameSr,
                        Classt, avatarT));
            }

//        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
//                cursorU, headers, new int[]{R.id.name, R.id.classh, R.id.num}, 0);
//        list.setAdapter(userAdapter);

            MyAdapterD1 = new MyAdapterD1(this, mas);
//        list.setAdapter(userAdapter);
//            listD.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listD.setAdapter(MyAdapterD1);
            listD.setOnItemClickListener(myOnItemClickListener);
        }
    }

    private void DeleteST(String Name, String LastName, String Age, String Less) {
        stDB.execSQL("DELETE FROM student WHERE Name = '"+Name+"' AND LastName = '"+LastName+"' AND Age = '"+Age+"' AND Less = '"+Less+"'");
    }
    private void DeleteCL(String id) {
        stDB.execSQL("DELETE FROM Calendar WHERE students = '"+id+"'");
    }

    private void closeActivity() {
        this.finish();
    }

    public void deniedST(View view) {
        closeActivity();
    }

    public void okST(View view) {onClick(view); closeActivity();}

    public void onClick(View view) {
        String result = "";
        List<String> resultList = MyAdapterD1.getCheckedfirstname(NameSr2);
        List<String> resultList1 = MyAdapterD1.getCheckedlastname(LastNamet2);
        List<String> resultList2 = MyAdapterD1.getCheckedage(Aget2);
        List<String> resultList3 = MyAdapterD1.getCheckedless(Lesst2);
        List<String> resultListCal = MyAdapterD1.getCheckedid(id2);
        for (int i = 0; i < resultList.size(); i++) {
            DeleteST(String.valueOf(resultList.get(i)), String.valueOf(resultList1.get(i)), String.valueOf(resultList2.get(i)), String.valueOf(resultList3.get(i)));
            DeleteCL(resultListCal.get(i));
        }

        Toast toast = Toast.makeText(getApplicationContext(),
                "Удалено", Toast.LENGTH_SHORT);
        toast.show();
    }


    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            MyAdapterD1.toggleChecked(position);
        }
    };

    private class MyArrayAdapter {

        private HashMap<Integer, Boolean> mCheckedMap = new HashMap<>();

        MyArrayAdapter(Context context, int resource,
                       int textViewResourceId, List<String> objects) {
//            super(context, resource, textViewResourceId, objects);

            for (int i = 0; i < objects.size(); i++) {
                mCheckedMap.put(i, false);
            }
        }

        void toggleChecked(int position) {
            if (mCheckedMap.get(position)) {
                mCheckedMap.put(position, false);
            } else {
                mCheckedMap.put(position, true);
            }


        }

        public List<Integer> getCheckedItemPositions() {
            List<Integer> checkedItemPositions = new ArrayList<>();

            for (int i = 0; i < mCheckedMap.size(); i++) {
                if (mCheckedMap.get(i)) {
                    (checkedItemPositions).add(i);
                }
            }

            return checkedItemPositions;
        }

        List<String> getCheckedItems() {
            List<String> checkedItems = new ArrayList<>();

            for (int i = 0; i < mCheckedMap.size(); i++) {
                if (mCheckedMap.get(i)) {
                    (checkedItems).add(mCatNameList.get(i));
                }
            }

            return checkedItems;
        }

        @NonNull
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.list_itemdel, parent, false);
            }

            CheckedTextView checkedTextView = (CheckedTextView) row
                    .findViewById(R.id.checkBox);
            checkedTextView.setText(mCatNameList.get(position));

            Boolean checked = mCheckedMap.get(position);
            if (checked != null) {
                checkedTextView.setChecked(checked);
            }

            return row;
        }
    }
}
