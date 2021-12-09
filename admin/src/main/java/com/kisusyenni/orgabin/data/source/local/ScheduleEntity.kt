package com.kisusyenni.orgabin.data.source.local

data class ScheduleEntity (
    var id: Int,
    var date: String,
    var location: String,
    var endTime: String,
    var startTime: String,
    var show: Boolean
)