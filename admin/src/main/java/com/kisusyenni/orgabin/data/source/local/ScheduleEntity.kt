package com.kisusyenni.orgabin.data.source.local

data class ScheduleEntity (
    var id: Int,
    var date: String,
    var location: LocationEntity,
    var show: Boolean
)