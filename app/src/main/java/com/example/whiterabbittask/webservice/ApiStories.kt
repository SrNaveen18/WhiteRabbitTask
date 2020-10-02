package com.example.whiterabbittask.webservice

import com.example.whiterabbittask.room.EmployeeItem
import retrofit2.http.GET

interface ApiStories {
    @GET("5d565297300000680030a986")
    suspend fun getEmployeeList(): List<EmployeeItem>
}