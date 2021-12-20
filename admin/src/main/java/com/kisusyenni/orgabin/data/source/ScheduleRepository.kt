package com.kisusyenni.orgabin.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem

class ScheduleRepository {
    private val database = FirebaseDatabase.getInstance()
    private val scheduleReference = database.getReference("Schedule")

    private val _scheduleList = MutableLiveData<List<ScheduleResponseItem>>()

    fun getAllScheduleList(): MutableLiveData<List<ScheduleResponseItem>> {
        scheduleReference.addValueEventListener(object: ValueEventListener {
            var scheduleData = ArrayList<ScheduleResponseItem>()
            override fun onDataChange(snapshot: DataSnapshot) {
                scheduleData = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(ScheduleResponseItem::class.java)!!
                } as ArrayList<ScheduleResponseItem>

                _scheduleList.value = scheduleData

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", error.toString())
            }

        })
        return _scheduleList
    }
}