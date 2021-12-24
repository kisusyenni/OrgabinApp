package com.kisusyenni.orgabin.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScheduleEntity (
    var id: String,
    var date: Long,
    var location: String,
    var endTime: Long,
    var startTime: Long,
    var show: Boolean
): Parcelable