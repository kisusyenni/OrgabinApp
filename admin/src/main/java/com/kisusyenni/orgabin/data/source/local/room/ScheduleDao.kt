package com.kisusyenni.orgabin.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity
import com.google.firebase.database.DatabaseError

import androidx.annotation.NonNull

import com.google.firebase.database.DataSnapshot

import com.google.firebase.database.ValueEventListener





class ScheduleDao {

    private val scheduleReference: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        scheduleReference = db.getReference("Schedule")
    }

    fun addSchedule(schedule: ScheduleEntity): Task<Void> {
        return scheduleReference.push().setValue(schedule)
    }

    fun generateId(): String? {
        return scheduleReference.push().key
    }

    fun deleteSchedule(id: String) {
        scheduleReference.child("id").equalTo(id).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.removeValue()

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
//        return scheduleReference.child(id!!).removeValue()
    }

    fun getDetailSchedule(id: String) {

    }
}