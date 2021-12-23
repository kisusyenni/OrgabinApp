package com.kisusyenni.user.data.source.local.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReportEntity (
    var id: String,
    var name: String,
    var location: String,
    var phone: String,
    var title : String,
    var description : String,
    var show: Boolean
): Parcelable