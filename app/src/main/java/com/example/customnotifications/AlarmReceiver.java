package com.example.customnotifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    public String Channelid = "myChannel";
    public NotificationManager nm;
    public NotificationChannel mChannel;
    PendingIntent pendingIntenthoro;
    SharedPrefs sharedPrefs;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPrefs = new SharedPrefs(context);
        nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent mynew = new Intent(context, MainActivity.class);

        pendingIntenthoro = PendingIntent.getActivity(context, 0, mynew,
                PendingIntent.FLAG_IMMUTABLE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!sharedPrefs.getNotification()) {

                CharSequence name = "myChannel1";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                mChannel = new NotificationChannel(Channelid, name, importance);
                nm.createNotificationChannel(mChannel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channelid);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("Horoscope Updates");
                builder.setContentText("Tap to see daily horoscope.");
                builder.setContentIntent(pendingIntenthoro);
                builder.setOngoing(false);
                nm.notify(1001, builder.build());


            } else {
                CharSequence name = "myChannel1";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                mChannel = new NotificationChannel(Channelid, name, importance);
                nm.createNotificationChannel(mChannel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channelid);
                builder.setAutoCancel(true);
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle(context.getString(R.string.app_name));
                builder.setContentText("This is your custom notification.");
                builder.setContentIntent(pendingIntenthoro);
                builder.setOngoing(false);
                nm.notify(1001, builder.build());
            }
        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Channelid);
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(context.getString(R.string.app_name));
            builder.setContentText("This is your custom notification.");
            builder.setContentIntent(pendingIntenthoro);
            builder.setOngoing(false);
            nm.notify(1001, builder.build());

        }
    }

}
