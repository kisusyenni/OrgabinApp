package com.kisusyenni.orgabin.data.source.local.entity

data class ScheduleEntity (
    var id: String? = null,
    var date: Long,
    var location: String,
    var endTime: Long,
    var startTime: Long,
    var show: Boolean
)