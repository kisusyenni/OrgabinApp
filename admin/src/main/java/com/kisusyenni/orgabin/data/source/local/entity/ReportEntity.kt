package com.kisusyenni.orgabin.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportEntity (
    val id: String,
    val reportDate: Long?,
    val reportTitle: String?,
    val reportDetail: String?,
    val senderName: String?,
    val senderAddress: String?,
    val senderContact: String?
): Parcelable
