package com.kisusyenni.orgabin.ui.schedule.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.orgabin.data.source.ScheduleRepository
import com.kisusyenni.orgabin.data.source.remote.response.ScheduleResponseItem

class ScheduleListViewModel : ViewModel() {

    private val repository = ScheduleRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllScheduleList(): LiveData<List<ScheduleResponseItem>> {
        _isLoading.value = true
        return repository.getAllScheduleList()
    }

}