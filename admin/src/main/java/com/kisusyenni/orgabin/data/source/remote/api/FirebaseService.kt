package com.kisusyenni.orgabin.data.source.remote.api

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.kisusyenni.orgabin.data.source.local.LocationEntity
import com.kisusyenni.orgabin.data.source.local.ScheduleEntity
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponse

class FirebaseService {
    companion object {
        private val scheduleRef = FirebaseConfig.scheduleReference

        fun getAllSchedule(): ArrayList<ScheduleEntity> {
            val scheduleData = ArrayList<ScheduleEntity>()
            scheduleRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val response = snapshot.getValue(ScheduleResponse::class.java)
                    response?.let {
                        for(data in response.scheduleResponse) {
                            for(dataLocation in data.location) {
                                val location = LocationEntity(dataLocation.name, dataLocation.startTime, dataLocation.endTime)
                                val schedule = ScheduleEntity(data.id, data.date, location, data.show)
                                scheduleData.add(schedule)
                            }
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("cancel", error.toString())
                }

            })
            return scheduleData
        }
    }
}