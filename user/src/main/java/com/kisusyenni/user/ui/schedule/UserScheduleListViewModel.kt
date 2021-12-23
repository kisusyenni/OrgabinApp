package com.kisusyenni.user.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.user.data.source.UserScheduleRepository
import com.kisusyenni.user.data.source.remote.response.ScheduleResponseItem

class UserScheduleListViewModel : ViewModel() {

    private val repository = UserScheduleRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllScheduleList(): LiveData<List<ScheduleResponseItem>> {
        _isLoading.value = true
        return repository.getAllScheduleList()
    }

}