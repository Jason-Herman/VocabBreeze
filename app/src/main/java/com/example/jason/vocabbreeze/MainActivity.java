package com.example.jason.vocabbreeze;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        //populate
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            myDb.populate();
        }
        //Alert
        long when = System.currentTimeMillis();

        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, when, AlarmManager.INTERVAL_FIFTEEN_MINUTES/15, pendingIntent);
//        am.setRepeating(AlarmManager.RTC_WAKEUP, when, AlarmManager.INTERVAL_DAY, pendingIntent);
        am.set(AlarmManager.RTC_WAKEUP, when, pendingIntent);
    }
}
