package com.kisusyenni.user.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kisusyenni.user.data.source.local.entity.UserReportEntity

class UserReportDao {
    private val reportReference: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        reportReference = db.getReference("Report")
    }

    fun sendReport(userReport: UserReportEntity): Task<Void> {
        return reportReference.child(userReport.id).setValue(userReport)
    }

    fun generateId(): String? {
        return reportReference.push().key
    }


}