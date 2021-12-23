package com.kisusyenni.user.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.user.data.source.ReportRepository
import com.kisusyenni.user.data.source.remote.response.ReportResponseItem

class UserNotificationViewModel: ViewModel() {
    private val repository = ReportRepository()

//    private val _scheduleList = MutableLiveData<List<ScheduleResponseItem>>()
//    val scheduleList: LiveData<List<ScheduleResponseItem>> = _scheduleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllReportList(): LiveData<List<ReportResponseItem>> {
        _isLoading.value = true
        return repository.getAllReportList()
    }
}