package com.kisusyenni.orgabin.utils

import java.text.SimpleDateFormat
import java.util.*

class CustomDateTimeFormatter {
    fun fullDate(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss", Locale.getDefault())
        return sdf.format(timeD)
    }

    fun getDay(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        return sdf.format(timeD)
    }

    fun getNumberDay(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("d", Locale.getDefault())
        return sdf.format(timeD)
    }

    fun getTime(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(timeD)
    }

    fun getDate(timestamp: Long): String {
        val timeD = Date(timestamp)
        val sdf = SimpleDateFormat("EEE, MMM dd yyyy", Locale.getDefault())
        return sdf.format(timeD)
    }

    fun epochToDateUtil(timestamp: Long): Date {
        return Date(timestamp)
    }
}