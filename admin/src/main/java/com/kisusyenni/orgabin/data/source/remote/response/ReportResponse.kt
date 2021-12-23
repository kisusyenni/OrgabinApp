package com.kisusyenni.orgabin.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReportResponse(

	@field:SerializedName("ReportResponse")
	val reportResponse: List<ReportResponseItem?>? = null
)

data class ReportDate(

	@field:SerializedName("date")
	val date: Int? = null,

	@field:SerializedName("hours")
	val hours: Int? = null,

	@field:SerializedName("seconds")
	val seconds: Int? = null,

	@field:SerializedName("month")
	val month: Int? = null,

	@field:SerializedName("timezoneOffset")
	val timezoneOffset: Int? = null,

	@field:SerializedName("year")
	val year: Int? = null,

	@field:SerializedName("minutes")
	val minutes: Int? = null,

	@field:SerializedName("time")
	val time: Long? = null,

	@field:SerializedName("day")
	val day: Int? = null
)

data class ReportResponseItem(

	@field:SerializedName("senderAddress")
	val senderAddress: String? = null,

	@field:SerializedName("senderName")
	val senderName: String? = null,

	@field:SerializedName("reportDate")
	val reportDate: ReportDate? = null,

	@field:SerializedName("senderContact")
	val senderContact: String? = null,

	@field:SerializedName("reportTitle")
	val reportTitle: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("reportDetail")
	val reportDetail: String? = null
)
