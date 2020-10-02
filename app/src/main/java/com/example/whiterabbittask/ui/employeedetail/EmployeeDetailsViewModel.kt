package com.example.whiterabbittask.ui.employeedetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.whiterabbittask.base.BaseViewModel
import com.example.whiterabbittask.room.EmployeeItem

class EmployeeDetailsViewModel(application: Application) : BaseViewModel(application) {
    var employeeItem = MutableLiveData<EmployeeItem>()
}