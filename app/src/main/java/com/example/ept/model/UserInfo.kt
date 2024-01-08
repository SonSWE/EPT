package com.example.ept.model

import java.util.Date

data class UserInfo(
    var user_Id: Int? = 0,
    var user_Name: String? = "",
    var password: String? = "",
    var full_Name: String? = "",
    var created_Date: Date? = null
)
