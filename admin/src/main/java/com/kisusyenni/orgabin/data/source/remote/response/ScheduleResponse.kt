package com.kisusyenni.orgabin.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

data class ScheduleResponse(

	@field:SerializedName("ScheduleResponse")
	val scheduleResponse: List<ScheduleResponseItem?>? = null
)

@Parcelize
data class ScheduleResponseItem(

	@field:SerializedName("date")
	val date: Long? = null,

	@field:SerializedName("show")
	val show: Boolean? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("startTime")
	val startTime: Long? = null,

	@field:SerializedName("endTime")
	val endTime: Long? = null,

	@field:SerializedName("id")
	val id: String? = null
): Parcelable
