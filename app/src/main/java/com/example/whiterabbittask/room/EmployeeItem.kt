package com.example.whiterabbittask.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "employee_item")
data class EmployeeItem(
    val profileImage: String? = "",
    val website: String? = "",
    val phone: String? = null,
    val name: String? = "",
    val id: Int? = 0,
    val email: String? = "",
    val username: String? = ""
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var empId: Int = 0
}


//data class Geo(val lng: String = "",
//               val lat: String = "")
//
//
//data class Company(val bs: String = "",
//                   val catchPhrase: String = "",
//                   val name: String = "")
//
//
//data class Address(val zipcode: String = "",
//                   val geo: Geo,
//                   val suite: String = "",
//                   val city: String = "",
//                   val street: String = "")

