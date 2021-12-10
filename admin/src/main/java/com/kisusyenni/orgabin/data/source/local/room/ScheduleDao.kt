package com.kisusyenni.orgabin.data.source.local.room

import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.kisusyenni.orgabin.data.source.local.entity.ScheduleEntity


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
}

//class DAOEmployee {
//    fun add(emp: Employee?): Task<Void> {
//        return databaseReference.push().setValue(emp)
//    }
//
//    fun update(key: String?, hashMap: HashMap<String?, Any?>?): Task<Void> {
//        return databaseReference.child(key!!).updateChildren(hashMap!!)
//    }
//
//    fun remove(key: String?): Task<Void> {
//        return databaseReference.child(key!!).removeValue()
//    }
//
//    operator fun get(key: String?): Query {
//        return if (key == null) {
//            databaseReference.orderByKey().limitToFirst(8)
//        } else databaseReference.orderByKey().startAfter(key).limitToFirst(8)
//    }
//
//    fun get(): Query {
//        return databaseReference
//    }
//
//    init {
//        val db = FirebaseDatabase.getInstance()
//        databaseReference = db.getReference(Employee::class.java.getSimpleName())
//    }
//}