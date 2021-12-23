package com.kisusyenni.orgabin.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kisusyenni.orgabin.data.source.remote.response.ReportResponseItem

class ReportRepository {
    private val database = FirebaseDatabase.getInstance()
    private val reportReference = database.getReference("Report")

    private val _reportList = MutableLiveData<List<ReportResponseItem>>()

    fun getAllReportList(): MutableLiveData<List<ReportResponseItem>> {
        reportReference.addValueEventListener(object: ValueEventListener {
            var scheduleData = ArrayList<ReportResponseItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                scheduleData = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(ReportResponseItem::class.java)!!
                } as ArrayList<ReportResponseItem>

                _reportList.value = scheduleData

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", error.toString())
            }

        })
        return _reportList
    }
}