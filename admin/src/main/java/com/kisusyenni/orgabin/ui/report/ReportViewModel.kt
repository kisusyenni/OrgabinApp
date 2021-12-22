package com.kisusyenni.orgabin.ui.report

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kisusyenni.orgabin.data.source.ReportRepository
import com.kisusyenni.orgabin.data.source.remote.response.ReportResponseItem

class ReportViewModel: ViewModel() {
    private val repository = ReportRepository()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getAllReportList(): LiveData<List<ReportResponseItem>> {
        _isLoading.value = true
        return repository.getAllReportList()
    }
}