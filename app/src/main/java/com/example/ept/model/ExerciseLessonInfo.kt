package com.example.ept.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ExerciseLessonInfo(
    val exercise_Id: Int? = 0,
    val lesson_Id: Int? = 1,
    val exercise_Lesson_Id: Int? = 0
){
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "exercise_Id" to exercise_Id,
            "lesson_Id" to lesson_Id,
            "exercise_Lesson_Id" to exercise_Lesson_Id
        )
    }
}
