package com.example.madpractical7_20012011193

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BtnCreateAlarm = findViewById<MaterialButton>(R.id.btn_set_alarm)
        val ShowTimeTV = findViewById<TextView>(R.id.show_time_tv)
        ShowTimeTV.text =
            SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault()).format(Date());
        BtnCreateAlarm.setOnClickListener {
            showDialog()
            ShowTimeTV.text =
                SimpleDateFormat("dd-MM-yyyy HH:mm a", Locale.getDefault()).format(Date());
        }
    }

    private fun showDialog() {
        var cal: Calendar = Calendar.getInstance()
        var hour = cal.get(Calendar.HOUR_OF_DAY)
        var min = cal.get(Calendar.MINUTE)
        val tpd =
            TimePickerDialog(this, TimePickerDialog.OnTimeSetListener(function = { view, h, m ->

            }), hour, min, false)
        tpd.show()

    }

    fun setAlarm(millisTime: Long, str: String) {
        val intent = Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra("Service1", str)
        val pendingIntent =
            PendingIntent.getBroadcast(applicationContext, 234324243, intent, 0)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        if (str == "Start") {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                millisTime,
                pendingIntent
            )
        } else if (str == "Stop") {
            alarmManager.cancel(pendingIntent)
        }
    }
}