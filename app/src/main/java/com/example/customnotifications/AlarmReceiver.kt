package com.example.customnotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {
    var Channelid = "myChannel"
    var nm: NotificationManager? = null
    var mChannel: NotificationChannel? = null
    var pendingIntenthoro: PendingIntent? = null
    var sharedPrefs: SharedPrefs? = null

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        sharedPrefs = SharedPrefs(context)
        nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mynew = Intent(context, MainActivity::class.java)
        pendingIntenthoro = PendingIntent.getActivity(
            context, 0, mynew,
            PendingIntent.FLAG_IMMUTABLE
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!sharedPrefs!!.getNotification()) {
                val name: CharSequence = "myChannel1"
                val importance = NotificationManager.IMPORTANCE_HIGH
                mChannel = NotificationChannel(Channelid, name, importance)
                nm!!.createNotificationChannel(mChannel!!)
                val builder = NotificationCompat.Builder(context, Channelid)
                builder.setAutoCancel(true)
                builder.setSmallIcon(R.mipmap.ic_launcher)
                builder.setContentTitle("Horoscope Updates")
                builder.setContentText("Tap to see daily horoscope.")
                builder.setContentIntent(pendingIntenthoro)
                builder.setOngoing(false)
                nm!!.notify(1001, builder.build())
            } else {
                val name: CharSequence = "myChannel1"
                val importance = NotificationManager.IMPORTANCE_HIGH
                mChannel = NotificationChannel(Channelid, name, importance)
                nm!!.createNotificationChannel(mChannel!!)
                val builder = NotificationCompat.Builder(context, Channelid)
                builder.setAutoCancel(true)
                builder.setSmallIcon(R.mipmap.ic_launcher)
                builder.setContentTitle(context.getString(R.string.app_name))
                builder.setContentText("This is your custom notification.")
                builder.setContentIntent(pendingIntenthoro)
                builder.setOngoing(false)
                nm!!.notify(1001, builder.build())
            }
        } else {
            val builder = NotificationCompat.Builder(context, Channelid)
            builder.setAutoCancel(true)
            builder.setSmallIcon(R.mipmap.ic_launcher)
            builder.setContentTitle(context.getString(R.string.app_name))
            builder.setContentText("This is your custom notification.")
            builder.setContentIntent(pendingIntenthoro)
            builder.setOngoing(false)
            nm!!.notify(1001, builder.build())
        }
    }
}