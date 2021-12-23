package com.kisusyenni.orgabin.utils

import java.text.SimpleDateFormat
import java.util.*

class CustomDateTimeFormatter {
    fun fullDate(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(timeD)
    }


}