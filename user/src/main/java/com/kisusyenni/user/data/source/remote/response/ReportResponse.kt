package com.kisusyenni.user.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ReportResponse(

    @field:SerializedName("ReportResponse")
    val reportResponse: List<ReportResponseItem?>? = null
)

@Parcelize
data class ReportResponseItem(

    @field:SerializedName("date")
    val date: Long? = null,

    @field:SerializedName("show")
    val show: Boolean? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: String? = null
): Parcelable
