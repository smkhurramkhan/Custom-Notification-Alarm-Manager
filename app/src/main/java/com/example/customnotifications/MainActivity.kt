package com.example.customnotifications

import android.app.AlarmManager
import android.app.Dialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var hr = 0
    private var min = 0
    var calendar: Calendar? = null
    private var timeSet: String? = null
    private var SetTime: Button? = null
    private var DisplayTime: TextView? = null

    var prefs: SharedPrefs? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = SharedPrefs(this)
        setContentView(R.layout.activity_main)


        val c = Calendar.getInstance()
        calendar = Calendar.getInstance()
        SetTime = findViewById(R.id.Time)
        DisplayTime = findViewById(R.id.DisplayTime)
        hr = c[Calendar.HOUR_OF_DAY]
        min = c[Calendar.MINUTE]


    }


    fun changeNotificationTime(v: View?) {
        showDialog(333)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun setUserDefinedAlarm() {
        prefs?.setAlarm(true)
        val am: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        //intent.putExtra("des",Description);
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        am.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, calendar!!.timeInMillis, AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(this, "Notification Time Confirmed!", Toast.LENGTH_LONG).show()
    }


    private fun updateTime(hours: Int, mins: Int) {
        var hours = hours
        timeSet = ""
        when {
            hours > 12 -> {
                hours -= 12
                timeSet = "PM"
            }
            hours == 0 -> {
                hours += 12
                timeSet = "AM"
            }
            hours == 12 -> timeSet = "PM"
            else -> timeSet = "AM"
        }
        var minutes = ""
        minutes = if (mins < 10) "0$mins" else mins.toString()
        val aTime =
            StringBuilder().append(hours).append(':').append(minutes).append(" ").append(timeSet)
                .toString()
        DisplayTime?.text = "Default time is $aTime"
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateDialog(id: Int): Dialog? {


        // TODO Auto-generated method stub
        return if (id == 333) {
            TimePickerDialog(
                this,
                timePickerListener, hr, min, false
            )
        } else null
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private val timePickerListener =
        OnTimeSetListener { view, hourOfDay, minutes ->
            hr = hourOfDay
            min = minutes

            calendar!!.timeInMillis = System.currentTimeMillis()
            calendar!![Calendar.HOUR_OF_DAY] = hr
            calendar!![Calendar.MINUTE] = min
            calendar!![Calendar.SECOND] = 1
            if (calendar!!.before(Calendar.getInstance())) {
                calendar!!.add(Calendar.DATE, 1)
            }
            updateTime(hr, min)
            setUserDefinedAlarm()
        }

}