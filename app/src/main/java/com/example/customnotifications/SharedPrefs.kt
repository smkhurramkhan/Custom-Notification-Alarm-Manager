package com.example.customnotifications

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context?) {

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        if (context != null) {
            prefs = context.getSharedPreferences("CustomNotifications", Context.MODE_PRIVATE)
            editor = prefs.edit()
            editor.apply()
        }
    }

    fun setAlarm(name: Boolean) {
        editor.putBoolean("isnotificationset", name)
        editor.commit()
    }

    fun getNotification(): Boolean {
        return prefs.getBoolean("isnotificationset", false)
    }



}