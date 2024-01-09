package com.example.ept.ObjectInfor

import java.util.Date

data class UserInfo(
    var user_Id: Int? = 0,
    var user_Name: String? = "",
    var password: String? = "",
    var full_Name: String? = "",
    var created_Date: Date? = null,
    var target: Int? = 0,
    var weigth: Double? = 0.0,
    var heigth: Double? = 0.0,
    var weigth_target: Double? = 0.0,
    var avt: String? = ""
)
