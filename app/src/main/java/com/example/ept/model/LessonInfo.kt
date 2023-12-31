package com.example.ept.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class LessonInfo(
    val lesson_Id: Int? = 0,
    val name: String? = "",
    val description: String? = "",
    val level: Int? = 0,
    val lesson_Type: Int? = 0,
    val thumbnail: String? = ""
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "lesson_Id" to lesson_Id,
            "name" to name,
            "description" to description,
            "level" to level,
            "lesson_Type" to lesson_Type,
            "thumnail" to thumbnail,
        )
    }
}
