package com.example.jason.vocabbreeze;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


/**
 * Created by Jason on 6/21/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    int MID = 123;
    DatabaseHelper myDb;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        myDb = new DatabaseHelper(context);

        myDb.decrement();
        Cursor res = myDb.getWords();

        String noteWords = "";
        String noteDefs = "";

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("Id :"+ res.getString(0)+"\n");
            buffer.append("Word :"+ res.getString(1)+"\n");
            buffer.append("Definition :"+ res.getString(2)+"\n");
            buffer.append("Interval :"+ res.getString(3)+"\n");
            buffer.append("TTS :"+ res.getString(4)+"\n\n");
            // Double increment and set TTS to increment
            Integer newInterval = 2 * Integer.valueOf(res.getString(3));
            myDb.updateData(res.getString(0), res.getString(1), res.getString(2), newInterval, newInterval);
            noteWords = noteWords + res.getString(1)+ "; ";
            noteDefs = noteDefs + res.getString(2) + "; ";
        }

        if (noteWords.length() > 0) {
            noteWords = noteWords.substring(0, noteWords.length() - 1);
            noteDefs = noteDefs.substring(0, noteDefs.length() - 1);
        }


        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, OpenActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.ic_terrain_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle(noteWords)
                .setContentText(noteDefs).setSound(alarmSound)
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;

//        Toast.makeText(context,"Working", Toast.LENGTH_LONG).show();

    }
}


