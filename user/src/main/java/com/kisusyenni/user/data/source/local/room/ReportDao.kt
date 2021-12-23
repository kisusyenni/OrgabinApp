package com.kisusyenni.user.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.kisusyenni.user.data.source.local.entity.ReportEntity

class ReportDao {
    private val reportReference: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        reportReference = db.getReference("Report")
    }

    fun addReport(report: ReportEntity): Task<Void> {
        return reportReference.child(report.id).setValue(report)
    }

    fun generateId(): String? {
        return reportReference.push().key
    }

    fun deleteReport(id: String) {
        reportReference.child(id).equalTo(id).addListenerForSingleValueEvent(object:
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.removeValue()

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
//        return reportReference.child(id!!).removeValue()
    }

    fun editReport(editedReport: ReportEntity, hashMap: HashMap<String, Any>): Task<Void> {
        return reportReference.child(editedReport.id).updateChildren(hashMap)
    }
}