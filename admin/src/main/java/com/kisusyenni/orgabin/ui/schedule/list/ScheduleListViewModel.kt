package com.kisusyenni.orgabin.ui.schedule.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.orgabin.data.source.ScheduleRepository
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem

class ScheduleListViewModel : ViewModel() {

    private val repository = ScheduleRepository()

    private val _scheduleList = MutableLiveData<List<ScheduleResponseItem>>()
    val scheduleList: LiveData<List<ScheduleResponseItem>> = _scheduleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        getAllScheduleList()
    }

    private fun getAllScheduleList() {
        _isLoading.value = true

        repository.getAllScheduleList(_scheduleList)
    }

}