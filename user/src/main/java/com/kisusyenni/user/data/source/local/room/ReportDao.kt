package com.kisusyenni.user.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kisusyenni.user.data.source.local.entity.ReportEntity

class ReportDao {
    private val reportReference: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        reportReference = db.getReference("Report")
    }

    fun sendReport(report: ReportEntity): Task<Void> {
        return reportReference.child(report.id).setValue(report)
    }

    fun generateId(): String? {
        return reportReference.push().key
    }


}