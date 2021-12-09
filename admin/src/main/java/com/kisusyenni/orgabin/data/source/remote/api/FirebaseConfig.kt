package com.kisusyenni.orgabin.data.source.remote.api

import com.google.firebase.database.FirebaseDatabase

class FirebaseConfig {
    companion object {
        private val database = FirebaseDatabase.getInstance()
        val scheduleReference = database.getReference("Schedule")
    }
}