package com.kisusyenni.orgabin.ui.schedule.form

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.local.room.ScheduleDao
import com.kisusyenni.orgabin.databinding.ActivityScheduleFormBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class ScheduleFormActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ACTION = "extra_action"
        const val EXTRA_SCHEDULE = "extra_schedule"
    }

    private val dao = ScheduleDao()
    private lateinit var activityScheduleFormBinding: ActivityScheduleFormBinding
    private lateinit var etFormDate: EditText
    private lateinit var etFormLocation: EditText
    private lateinit var etFormStartTime: EditText
    private lateinit var etFormEndTime: EditText
    private lateinit var schedule: ScheduleEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityScheduleFormBinding = ActivityScheduleFormBinding.inflate(layoutInflater)
        setContentView(activityScheduleFormBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val action = intent.getStringExtra(EXTRA_ACTION)

        etFormDate = activityScheduleFormBinding.etFormDate
        etFormLocation = activityScheduleFormBinding.etFormLocation
        etFormStartTime = activityScheduleFormBinding.etFormStartTime
        etFormEndTime = activityScheduleFormBinding.etFormEndTime

        if (action == "edit") {
            schedule = intent.getParcelableExtra<ScheduleEntity>(EXTRA_SCHEDULE) as ScheduleEntity
            setFormData(schedule)
        }

        activityScheduleFormBinding.btnFormSubmit.setOnClickListener {
            // Add New Schedule
            if(action == "add") {
                addNewSchedule()
            } else if (action == "edit") {
                editSchedule(schedule.id)
            }

        }

    }

    private fun addNewSchedule() {

        val id = dao.generateId()
        val stringDate = etFormDate.text.toString()
        val date = formatDate(stringDate)
        val location = etFormLocation.text.toString()
        val startTime = formatTime(etFormStartTime.text.toString(), stringDate)
        val endTime = formatTime(etFormEndTime.text.toString(), stringDate)


        id?.let { ID ->
            val new = ScheduleEntity(ID, date,location, startTime, endTime, true)
            dao.addSchedule(new).addOnSuccessListener {
                Toast.makeText(this, "New Schedule is Successfully Added", Toast.LENGTH_LONG).show()
                clearForm()
            }. addOnCanceledListener {
                Toast.makeText(this, "Failed to Add New Schedule", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun editSchedule(id: String) {
        val stringDate = etFormDate.text.toString()
        val date = formatDate(stringDate)
        val location = etFormLocation.text.toString()
        val startTime = formatTime(etFormStartTime.text.toString(), stringDate)
        val endTime = formatTime(etFormEndTime.text.toString(), stringDate)

        val editedSchedule = ScheduleEntity(id, date, location, startTime,endTime,true)
        val hashMap = HashMap<String, Any>()
        hashMap["id"] = editedSchedule.id
        hashMap["date"] = editedSchedule.date
        hashMap["location"] = editedSchedule.location
        hashMap["startTime"] = editedSchedule.startTime
        hashMap["endTime"] = editedSchedule.endTime
        hashMap["show"] = editedSchedule.show


        dao.editSchedule(editedSchedule, hashMap).addOnSuccessListener {
            Toast.makeText(this, "Schedule is Successfully Edited", Toast.LENGTH_LONG).show()
            finish()
        }. addOnCanceledListener {
            Toast.makeText(this, "Failed to Edit Schedule", Toast.LENGTH_LONG).show()
        }
    }

    private fun setFormData(schedule: ScheduleEntity) {
        etFormDate.setText(customizeDate("dd/MM/yyyy", schedule.date))
        etFormStartTime.setText(customizeTime(schedule.startTime))
        etFormEndTime.setText(customizeTime(schedule.endTime))
        etFormLocation.setText(schedule.location)
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

    private fun customizeDate(format: String, date:Long?): String {
        val sdf = SimpleDateFormat(format, Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        return sdf.format(date).toString()
    }

    private fun customizeTime(date:Long?): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Jakarta")
        return sdf.format(date).toString()
    }

    private fun clearForm() {
        etFormDate.text.clear()
        etFormLocation.text.clear()
        etFormStartTime.text.clear()
        etFormEndTime.text.clear()
    }
}