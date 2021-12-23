package com.kisusyenni.user.ui.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.kisusyenni.user.data.source.local.entity.ReportEntity
import com.kisusyenni.user.data.source.local.room.ReportDao
import com.kisusyenni.user.databinding.ActivityUserReportBinding
import kotlin.collections.HashMap

class UserReportActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ACTION = "extra_action"
        const val EXTRA_REPORT = "extra_report"
    }

    private val dao = ReportDao()
    private lateinit var activityUserReportBinding: ActivityUserReportBinding
    private lateinit var etReportName: EditText
    private lateinit var etFormLocation: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etReportTitle: EditText
    private lateinit var etDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUserReportBinding = ActivityUserReportBinding.inflate(layoutInflater)
        setContentView(activityUserReportBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val action = intent.getStringExtra(EXTRA_ACTION)
//        val id = intent.getStringExtra(EXTRA_ID)
        val schedule = intent.getParcelableExtra<ReportEntity>(EXTRA_REPORT) as ReportEntity

        etReportName = activityUserReportBinding.etReportName
        etFormLocation = activityUserReportBinding.etFormLocation
        etPhoneNumber = activityUserReportBinding.etPhoneNumber
        etReportTitle = activityUserReportBinding.etReportTitle
        etDescription = activityUserReportBinding.etDescription

        if (action == "edit") {
            setFormData(schedule)
        }

        activityUserReportBinding.btnFormSubmit.setOnClickListener {
            // Add New Schedule
            if(action == "add") {
                addNewReport()
            } else if (action == "edit") {
                val id = schedule.id
                val name = etFormLocation.text.toString()
                val location = etFormLocation.text.toString()
                val phoneNumber = etFormLocation.text.toString()
                val title = etFormLocation.text.toString()
                val description = etFormLocation.text.toString()

                val editedSchedule = ReportEntity(id, name, location, phoneNumber, title, description,true)

                editReport(editedSchedule)
            }

        }

    }

    private fun addNewReport() {

        val id = dao.generateId()
        val name = etFormLocation.text.toString()
        val location = etFormLocation.text.toString()
        val phoneNumber = etFormLocation.text.toString()
        val title = etFormLocation.text.toString()
        val description = etFormLocation.text.toString()


        id?.let { ID ->
            val new = ReportEntity(ID, name, location, phoneNumber, title, description, true)
            dao.addReport(new).addOnSuccessListener {
                Toast.makeText(this, "New Schedule is Successfully Added", Toast.LENGTH_LONG).show()
                clearForm()
            }. addOnCanceledListener {
                Toast.makeText(this, "Failed to Add New Schedule", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun editReport(editedSchedule: ReportEntity) {
        val hashMap = HashMap<String, Any>()
        hashMap["id"] = editedSchedule.id
        hashMap["name"] = editedSchedule.name
        hashMap["location"] = editedSchedule.location
        hashMap["phoneNumber"] = editedSchedule.phone
        hashMap["title"] = editedSchedule.title
        hashMap["description"] = editedSchedule.description
        hashMap["show"] = editedSchedule.show

        dao.editReport(editedSchedule, hashMap).addOnSuccessListener {
            Toast.makeText(this, "Schedule is Successfully Edited", Toast.LENGTH_LONG).show()
            finish()
        }. addOnCanceledListener {
            Toast.makeText(this, "Failed to Edit Schedule", Toast.LENGTH_LONG).show()
        }
    }

    private fun setFormData(report: ReportEntity) {
        etReportName.setText(report.name)
        etFormLocation.setText(report.location)
        etPhoneNumber.setText(report.phone)
        etReportTitle.setText(report.title)
        etDescription.setText(report.description)
    }


    private fun clearForm() {
        etReportName.text.clear()
        etFormLocation.text.clear()
        etPhoneNumber.text.clear()
        etReportTitle.text.clear()
        etDescription.text.clear()
    }

}