package com.kisusyenni.user.data.source.local.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ReportEntity (
    val id: String,
    val reportDate: Date?,
    val reportTitle: String?,
    val reportDetail: String?,
    val senderName: String?,
    val senderAddress: String?,
    val senderContact: String?
): Parcelable