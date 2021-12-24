package com.kisusyenni.orgabin.ui.schedule.form

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.kisusyenni.orgabin.data.source.local.room.ScheduleDao
import com.kisusyenni.orgabin.databinding.ActivityScheduleFormBinding
import com.kisusyenni.orgabin.utils.CustomDateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFormActivity : AppCompatActivity(), DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private val dao = ScheduleDao()
    private lateinit var activityScheduleFormBinding: ActivityScheduleFormBinding
    private lateinit var etFormLocation: EditText
    private lateinit var btnDate: ImageButton
    private lateinit var tvDate: TextView
    private lateinit var btnStartTime: ImageButton
    private lateinit var tvStartTime: TextView
    private lateinit var btnEndTime: ImageButton
    private lateinit var tvEndTime: TextView
    private lateinit var schedule: ScheduleEntity
    private var submitDate: Long? = null
    private var startTime: Long? = null
    private var endTime: Long? = null
    private val customDateTimeFormatter = CustomDateTimeFormatter()


    override fun onCreate(savedInstanceState: Bundle?) {
        val action = intent.getStringExtra(EXTRA_ACTION)

        super.onCreate(savedInstanceState)
        activityScheduleFormBinding = ActivityScheduleFormBinding.inflate(layoutInflater)
        setContentView(activityScheduleFormBinding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etFormLocation = activityScheduleFormBinding.etFormLocation
        btnDate = activityScheduleFormBinding.btnDate
        btnStartTime = activityScheduleFormBinding.btnStartTime
        btnEndTime = activityScheduleFormBinding.btnEndTime
        tvDate = activityScheduleFormBinding.tvDate
        tvStartTime = activityScheduleFormBinding.tvStartTime
        tvEndTime = activityScheduleFormBinding.tvEndTime

        // button date onclick listener
        btnDate.setOnClickListener{
            val datePickerFragment = DatePickerFragment()
            datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
        }

        // button start time onclick listener
        btnStartTime.setOnClickListener {
            val timePickerFragment = TimePickerFragment()
            timePickerFragment.show(supportFragmentManager, START_TIME_PICKER_TAG)
        }

        // button start time onclick listener
        btnEndTime.setOnClickListener {
            val timePickerFragment = TimePickerFragment()
            timePickerFragment.show(supportFragmentManager, END_TIME_PICKER_TAG)
        }

        if (action == "edit") {
            activityScheduleFormBinding.tvFormTitle.text = resources.getString(R.string.edit_schedule_title)
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

        if(etFormLocation.text.isEmpty()) {
            etFormLocation.error = resources.getString(R.string.empty_error)
        }

        val id = dao.generateId()
        val location = etFormLocation.text.toString()

        if(etFormLocation.text.isNotEmpty() && id != null && startTime != null && endTime != null && submitDate != null) {
            val new = ScheduleEntity(id, submitDate!!,location, endTime!!, startTime!!, true)
            dao.addSchedule(new).addOnSuccessListener {
                Toast.makeText(this, "New Schedule is Successfully Added", Toast.LENGTH_LONG).show()
                clearForm()
            }. addOnCanceledListener {
                Toast.makeText(this, "Failed to Add New Schedule", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun editSchedule(id: String) {
        if(etFormLocation.text.isEmpty()) {
            etFormLocation.error = resources.getString(R.string.empty_error)
        }

        val location = etFormLocation.text.toString()

        if(etFormLocation.text.isNotEmpty() && id != null && startTime != null && endTime != null && submitDate != null) {
            val editedSchedule = ScheduleEntity(id, submitDate!!, location, endTime!! , startTime!!,true)
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
    }

    private fun setFormData(schedule: ScheduleEntity) {
        submitDate = schedule.date
        tvDate.text = customDateTimeFormatter.getDate(schedule.date)

        startTime = schedule.startTime
        tvStartTime.text = customDateTimeFormatter.getTime(schedule.startTime)

        endTime = schedule.endTime
        tvEndTime.text = customDateTimeFormatter.getTime(schedule.endTime)
        etFormLocation.setText(schedule.location)

    }

    private fun clearForm() {
        etFormLocation.text.clear()
        tvDate.text = resources.getString(R.string.input_date_hint)
        tvStartTime.text = resources.getString(R.string.input_start_time_hint)
        tvEndTime.text = resources.getString(R.string.input_end_time_hint)

    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        submitDate = calendar.time.time
        tvDate.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        when(tag) {
            START_TIME_PICKER_TAG -> {
                startTime = calendar.time.time
                tvStartTime.text = dateFormat.format(calendar.time)
            }
            END_TIME_PICKER_TAG -> {
                endTime = calendar.time.time
                tvEndTime.text = dateFormat.format(calendar.time)
            }
        }
    }

    companion object {
        const val EXTRA_ACTION = "extra_action"
        const val EXTRA_SCHEDULE = "extra_schedule"
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val START_TIME_PICKER_TAG = "StartTimePicker"
        private const val END_TIME_PICKER_TAG = "EndTimePicker"
    }

}