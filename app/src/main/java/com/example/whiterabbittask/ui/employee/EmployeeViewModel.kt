package com.example.whiterabbittask.ui.employee

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.whiterabbittask.base.BaseViewModel
import com.example.whiterabbittask.room.EmployeeItem
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : BaseViewModel(application) {

    private val repository: EmployeeRepository = EmployeeRepository(application)

    fun getCustomerData() = liveData {
        val data = repository.getAllData()
        data?.let {
            emitSource(it)
        }
    }

    fun addAll(employeeItem: List<EmployeeItem>) {
        viewModelScope.launch {
            repository.insertAll(employeeItem)
        }
    }

    fun makeApiCall() = liveData {
        emit(repository.fetchDataFromServer())
    }
}