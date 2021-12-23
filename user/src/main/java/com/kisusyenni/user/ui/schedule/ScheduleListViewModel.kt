package com.kisusyenni.user.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.user.data.source.ScheduleRepository
import com.kisusyenni.user.data.source.remote.response.ScheduleResponseItem

class ScheduleListViewModel : ViewModel() {

    private val repository = ScheduleRepository()

//    private val _scheduleList = MutableLiveData<List<ScheduleResponseItem>>()
//    val scheduleList: LiveData<List<ScheduleResponseItem>> = _scheduleList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllScheduleList(): LiveData<List<ScheduleResponseItem>> {
        _isLoading.value = true
        return repository.getAllScheduleList()
    }

}