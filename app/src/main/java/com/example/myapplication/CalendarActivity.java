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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


class PersonC {
    String name;
    String data;
    String delTime;
    String Time;
    String StID;

    public PersonC(String name, String data, String delTime, String Time, String StID) {
        this.StID = StID;
        this.Time = Time;
        this.delTime = delTime;
        this.data = data;
        this.name = name;
    }


}

class MyAdapterC extends ArrayAdapter<PersonC> {

    public MyAdapterC(@NonNull Context context, ArrayList<PersonC> mas) {
        super(context, R.layout.list_itemc, mas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PersonC personC = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_itemc, null);
        }

        ((TextView) convertView.findViewById(R.id.textNameC)).setText(personC.name);
        ((TextView) convertView.findViewById(R.id.textTime)).setText(personC.Time);
        ((TextView) convertView.findViewById(R.id.textData)).setText(personC.data);
        ((TextView) convertView.findViewById(R.id.textDelTime)).setText(personC.delTime);


        return convertView;
    }
}

public class CalendarActivity extends AppCompatActivity {
//    SimpleCursorAdapter userAdapter;
//    SQLiteDatabase stDB;
//    DBcontrol databaseHelper;
//

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

    SimpleCursorAdapter userAdapter;
    SQLiteDatabase stDB;
    DBcontrol databaseHelper;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_calendar);


        ListView list = findViewById(R.id.listCal);
        databaseHelper = new DBcontrol(getApplicationContext());
        stDB = databaseHelper.getWritableDatabase();

        String sendCRDB = "CREATE TABLE IF NOT EXISTS Calendar (id       INTEGER PRIMARY KEY AUTOINCREMENT, students         REFERENCES student (id), data     DATE, time     DATETIME, deltime     DATETIME);";
        stDB.execSQL("CREATE TABLE IF NOT EXISTS Calendar (    id       INTEGER  PRIMARY KEY AUTOINCREMENT,\n" +
                "    students,\n" +
                "    data     DATE,\n" +
                "    time     DATETIME,\n" +
                "    deltime  DATETIME);");

        String NameDt, DataDt, DelTimeDt, TimeDt, STidDt;

        ArrayList<String> NameDT = new ArrayList<>();
        ArrayList<String> DataDT = new ArrayList<>();
        ArrayList<String> DelTimeDT = new ArrayList<>();
        ArrayList<String> TimeDT = new ArrayList<>();
        ArrayList<String> STidDT = new ArrayList<>();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                if (id == 0) {
                    startActivity(new Intent(CalendarActivity.this, AddCalendarActivity3.class));
                    closeActivity();
                }
            }
        });


        Cursor cursorD = stDB.query("Calendar", null, null, null, null, null, null);

        if (cursorD.moveToFirst()) {
            while (cursorD.moveToNext()) {
                @SuppressLint("Range") String Data = cursorD.getString(cursorD.getColumnIndex("data"));
                @SuppressLint("Range") String time = cursorD.getString(cursorD.getColumnIndex("time"));
                @SuppressLint("Range") String deltime = cursorD.getString(cursorD.getColumnIndex("deltime"));
                @SuppressLint("Range") String StudID = cursorD.getString(cursorD.getColumnIndex("students"));
                String queryS = "SELECT Name, LastName FROM student WHERE id = " + StudID;
                Cursor cursorS = stDB.rawQuery(queryS, null);
                if (1 > cursorS.getCount()){
                    continue;
                }
                cursorS.moveToFirst();
                @SuppressLint("Range") String firstname = cursorS.getString(cursorS.getColumnIndex("Name"));
                @SuppressLint("Range") String lastname = cursorS.getString(cursorS.getColumnIndex("LastName"));
                NameDT.add(firstname + " " + lastname);
                DataDT.add(Data);
                TimeDT.add(time);
                try {
                    DelTimeDT.add(DelTimeV(deltime));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                STidDT.add(StudID);
                cursorS.close();
            }
            cursorD.close();
        }


//                                         Добавляю кнопку, чтобы не забыл
        ArrayList<PersonC> mas = new ArrayList<>();

        mas.add(new PersonC("Добавить время", " ", " ", " ", " "));

        for (int i = 0; i < NameDT.size(); i++) {
            NameDt = NameDT.get(i);
            DataDt = (DataDT.get(i));
            DelTimeDt = DelTimeDT.get(i);
            TimeDt = TimeDT.get(i);
            STidDt = STidDT.get(i);
            mas.add(new PersonC(NameDt,
                    DataDt, DelTimeDt, TimeDt, STidDt));
        }

//        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
//                cursorU, headers, new int[]{R.id.name, R.id.classh, R.id.num}, 0);
//        list.setAdapter(userAdapter);


//        list.setAdapter(userAdapter);
        list.setAdapter(new MyAdapterC(this, mas));
    }





//    public static long pushAppointmentsToCalender(Activity curActivity, String title, String addInfo, String place, int status, long startDate, boolean needReminder, boolean needMailService) {
//        /***************** Event: note(without alert) *******************/
//
//        String eventUriString = "content://com.android.calendar/events";
//        ContentValues eventValues = new ContentValues();
//
//        eventValues.put("calendar_id", 1); // id, We need to choose from
//        // our mobile for primary
//        // its 1
//        eventValues.put("title", title);
//        eventValues.put("description", addInfo);
//        eventValues.put("eventLocation", place);
//
//        long endDate = startDate + 1000 * 60 * 60; // For next 1hr
//
//        eventValues.put("dtstart", startDate);
//        eventValues.put("dtend", endDate);
//
//        // values.put("allDay", 1); //If it is bithday alarm or such
//        // kind (which should remind me for whole day) 0 for false, 1
//        // for true
//        eventValues.put("eventStatus", status); // This information is
//        // sufficient for most
//        // entries tentative (0),
//        // confirmed (1) or canceled
//        // (2):
//        eventValues.put("eventTimezone", "UTC/GMT +2:00");
//        /*Comment below visibility and transparency  column to avoid java.lang.IllegalArgumentException column visibility is invalid error */
//
//    /*eventValues.put("visibility", 3); // visibility to default (0),
//                                        // confidential (1), private
//                                        // (2), or public (3):
//    eventValues.put("transparency", 0); // You can control whether
//                                        // an event consumes time
//                                        // opaque (0) or transparent
//                                        // (1).
//      */
//        eventValues.put("hasAlarm", 1); // 0 for false, 1 for true
//
//        Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
//        long eventID = Long.parseLong(eventUri.getLastPathSegment());
//
//        if (needReminder) {
//            /***************** Event: Reminder(with alert) Adding reminder to event *******************/
//
//            String reminderUriString = "content://com.android.calendar/reminders";
//
//            ContentValues reminderValues = new ContentValues();
//
//            reminderValues.put("event_id", eventID);
//            reminderValues.put("minutes", 5); // Default value of the
//            // system. Minutes is a
//            // integer
//            reminderValues.put("method", 1); // Alert Methods: Default(0),
//            // Alert(1), Email(2),
//            // SMS(3)
//
//            Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
//        }
//
//        /***************** Event: Meeting(without alert) Adding Attendies to the meeting *******************/
//
//        if (needMailService) {
//            String attendeuesesUriString = "content://com.android.calendar/attendees";
//
//            /********
//             * To add multiple attendees need to insert ContentValues multiple
//             * times
//             ***********/
//            ContentValues attendeesValues = new ContentValues();
//
//            attendeesValues.put("event_id", eventID);
//            attendeesValues.put("attendeeName", "xxxxx"); // Attendees name
//            attendeesValues.put("attendeeEmail", "yyyy@gmail.com");// Attendee
//            // E
//            // mail
//            // id
//            attendeesValues.put("attendeeRelationship", 0); // Relationship_Attendee(1),
//            // Relationship_None(0),
//            // Organizer(2),
//            // Performer(3),
//            // Speaker(4)
//            attendeesValues.put("attendeeType", 0); // None(0), Optional(1),
//            // Required(2), Resource(3)
//            attendeesValues.put("attendeeStatus", 0); // NOne(0), Accepted(1),
//            // Decline(2),
//            // Invited(3),
//            // Tentative(4)
//
//            Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);
//        }
//
//        return eventID;
//
//    }

    private void closeActivity() {
        this.finish();
    }

    public void click1(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                (CharSequence) dateAndTime, Toast.LENGTH_SHORT);
        toast.show();
    }

    public String DelTimeV(String DelTime) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        long timeUp = format.parse(DelTime).getTime();
        long diff = timeUp - System.currentTimeMillis();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        if (diffSeconds < 0 || diffMinutes < 0 || diffHours < 0 || diffDays < 0){
            return "Прошло";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Осталось: ");
        if (diffDays > 0) {
            sb.append(diffDays + " дней, ");
        } if (diffHours > 0) {
            sb.append(diffHours + " часов, ");
        } if (diffMinutes > 0) {
            sb.append(diffMinutes + " минут, ");
        } if (diffSeconds > 0) {
            sb.append(diffSeconds + " секунд");
        }
        String Result = sb.toString();;
        return Result;
    }
//    public void onClick(View view) {
//        ListView list = findViewById(R.id.list);
//        int activePosition = 0; // первый элемент списка
//        list.performItemClick(list.getAdapter().
//                getView(activePosition, null, null), activePosition, list.getAdapter().
//                getItemId(activePosition));
//    }


}