package com.example.customnotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

public class BootCompleteNotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPrefs sharedPrefs  =new SharedPrefs(context);
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            if (!sharedPrefs.getNotification()) {
                    Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                    alarmIntent.putExtra("sender2", "horoscope");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent,
                            PendingIntent.FLAG_IMMUTABLE);

                    AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

     /*               Date date = new Date();
                    //Calendar cal_now = Calendar.getInstance();
                    //cal_now.setTime(date);*/

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(System.currentTimeMillis());
                    calendar.set(Calendar.HOUR_OF_DAY, 10);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 1);


                    if (calendar.before(Calendar.getInstance())) {
                        calendar.add(Calendar.DATE, 1);
                    }

                    if (manager != null) {
                        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, pendingIntent);
                    }

            } else {

                // on device boot complete, reset the alarm

                Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent,
                        PendingIntent.FLAG_IMMUTABLE);

                AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

                Date date = new Date();
                //Calendar cal_now = Calendar.getInstance();
                //cal_now.setTime(date);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 1);


                if (calendar.before(Calendar.getInstance())) {
                    calendar.add(Calendar.DATE, 1);
                }

                if (manager != null) {
                    manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_DAY, pendingIntent);
                }
            }
        }
    }
}
