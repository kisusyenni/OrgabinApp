package com.kisusyenni.orgabin.ui.schedule.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.orgabin.data.source.local.ScheduleEntity
import com.kisusyenni.orgabin.data.source.remote.api.FirebaseService

class ScheduleListViewModel : ViewModel() {
    private val _scheduleList = MutableLiveData<List<ScheduleEntity>>()
    val scheduleList: LiveData<List<ScheduleEntity>> = _scheduleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllScheduleList()
    }

    private fun getAllScheduleList() {
        _isLoading.value = true
        val list = FirebaseService.getAllSchedule()
        list?.let {
            _scheduleList.value = it
        }
    }

}