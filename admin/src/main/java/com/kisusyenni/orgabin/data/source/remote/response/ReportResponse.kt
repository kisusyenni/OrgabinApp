package com.kisusyenni.orgabin.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ReportResponse(

	@field:SerializedName("ReportResponse")
	val reportResponse: List<ReportResponseItem?>? = null
)

data class ReportResponseItem(

	@field:SerializedName("senderAddress")
	val senderAddress: String? = null,

	@field:SerializedName("senderName")
	val senderName: String? = null,

	@field:SerializedName("reportDate")
	val reportDate: String? = null,

	@field:SerializedName("senderContact")
	val senderContact: String? = null,

	@field:SerializedName("reportTitle")
	val reportTitle: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("reportDetail")
	val reportDetail: String? = null
)
