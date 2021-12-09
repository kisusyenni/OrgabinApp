package com.kisusyenni.orgabin.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(

	@field:SerializedName("ScheduleResponse")
	val scheduleResponse: List<ScheduleResponseItem>
)

data class ScheduleResponseItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("show")
	val show: Boolean,

	@field:SerializedName("location")
	val location: List<LocationItem>,

	@field:SerializedName("id")
	val id: Int
)

data class LocationItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("startTime")
	val startTime: String,

	@field:SerializedName("endTime")
	val endTime: String
)
