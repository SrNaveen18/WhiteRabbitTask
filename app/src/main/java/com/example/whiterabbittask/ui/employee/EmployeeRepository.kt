package com.example.whiterabbittask.ui.employee

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.whiterabbittask.room.EmployeeDAO
import com.example.whiterabbittask.room.EmployeeDatabase
import com.example.whiterabbittask.room.EmployeeItem
import com.example.whiterabbittask.webservice.ApiClient
import com.example.whiterabbittask.webservice.ApiStories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EmployeeRepository(application: Application) {

    private var employeeDAO: EmployeeDAO?
    private val apiStories : ApiStories?

    init {
        val db = EmployeeDatabase.getDatabase(application)
        employeeDAO = db?.getUserDao()
        apiStories = ApiClient.create()
    }

    suspend fun addData(employeeItem: EmployeeItem){
        withContext(Dispatchers.IO){
            employeeDAO?.insert(employeeItem)
        }
    }

    suspend fun insertAll(employeeList : List<EmployeeItem>){
        withContext(Dispatchers.IO){
            employeeDAO?.insertAll(employeeList)
        }
    }

    fun getAllData() : LiveData<List<EmployeeItem>>? {
        return employeeDAO?.getAllData()
    }


    suspend fun fetchDataFromServer() : List<EmployeeItem>?{
       return withContext(Dispatchers.IO){
            apiStories?.getEmployeeList()
        }
    }
}