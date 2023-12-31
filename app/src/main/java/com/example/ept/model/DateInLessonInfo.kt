package com.example.ept.model

data class DateInLessonInfo(
    val id: Int? = 0,
    val lesson_Id: Int? = 0,
    val day: Int? =0,
    val name: String? = "",
    val description: String? = "",
    val time: Int? = 0,
    val status: Int? = 0,
    val thumbnail: String? = "",
    val countDone: Int? =0
)