package com.example.whiterabbittask.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface EmployeeDAO {
    @Insert
    fun insert(employee: EmployeeItem?)

    @Query("SELECT * FROM employee_item ORDER BY name ASC")
    fun getAllData(): LiveData<List<EmployeeItem>>

    @Query("DELETE FROM employee_item")
    fun deleteAllData()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(order: List<EmployeeItem>)
}