package com.kisusyenni.orgabin.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem

class ScheduleRepository {
    private val database = FirebaseDatabase.getInstance()
    private val scheduleReference = database.getReference("Schedule")

    fun getAllScheduleList(liveData: MutableLiveData<List<ScheduleResponseItem>>) {
        scheduleReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val scheduleData: List<ScheduleResponseItem> = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue(ScheduleResponseItem::class.java)!!
                }

                liveData.postValue(scheduleData)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Cancel", error.toString())
            }

        })
    }
}