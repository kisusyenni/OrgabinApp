package com.kisusyenni.orgabin.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity

class ScheduleDao {

    private val scheduleReference: DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        scheduleReference = db.getReference("Schedule")
    }

    fun addSchedule(schedule: ScheduleEntity): Task<Void> {
        return scheduleReference.child(schedule.id).setValue(schedule)
    }

    fun generateId(): String? {
        return scheduleReference.push().key
    }

    fun deleteSchedule(id: String) {
        scheduleReference.child(id).equalTo(id).addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.removeValue()

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
//        return scheduleReference.child(id!!).removeValue()
    }

    fun editSchedule(editedSchedule: ScheduleEntity, hashMap: HashMap<String, Any>): Task<Void> {
        return scheduleReference.child(editedSchedule.id).updateChildren(hashMap)
    }
}