package com.kisusyenni.orgabin.ui.schedule.form

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.local.room.ScheduleDao
import com.kisusyenni.orgabin.databinding.ActivityScheduleFormBinding
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class ScheduleFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityScheduleFormBinding = ActivityScheduleFormBinding.inflate(layoutInflater)
        setContentView(activityScheduleFormBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dao = ScheduleDao()
        activityScheduleFormBinding.btnFormSubmit.setOnClickListener {

            val id = dao.generateId()
            val etFormDate = activityScheduleFormBinding.etFormDate.text
            val etFormLocation = activityScheduleFormBinding.etFormLocation.text
            val etFormStartTime = activityScheduleFormBinding.etFormStartTime.text
            val etFormEndTime = activityScheduleFormBinding.etFormEndTime.text

            val stringDate = etFormDate.toString()
            val date = formatDate(stringDate)
            val location = etFormLocation.toString()
            val startTime = formatTime(etFormStartTime.toString(), stringDate)
            val endTime = formatTime(etFormEndTime.toString(), stringDate)

            val new = ScheduleEntity(id, date,location, startTime, endTime, true)

            dao.addSchedule(new).addOnSuccessListener {
                Toast.makeText(this, "New Schedule is Successfully Added", Toast.LENGTH_LONG).show()
                etFormDate.clear()
                etFormLocation.clear()
                etFormStartTime.clear()
                etFormEndTime.clear()
            }. addOnCanceledListener {
                Toast.makeText(this, "Failed to Add New Schedule", Toast.LENGTH_LONG).show()
            }

        }

    }
    private fun formatDate(date: String): Long {
        val l = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        return l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    }

    private fun formatTime(time: String, date: String): Long {
        val data = "$date $time"
        val l = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        return l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    }
}