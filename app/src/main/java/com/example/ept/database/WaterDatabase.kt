package com.example.ept.database

// Trong thư mục src/main/java/com/example/ept/database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//@Database(entities = [Water::class], version = 1, exportSchema = false)
//abstract class WaterDatabase : RoomDatabase() {
//
//    abstract fun waterDao(): WaterDAO
//
//    companion object {
//        private var instance: WaterDatabase? = null
//
//        fun getInstance(context: Context): WaterDatabase {
//            return instance ?: synchronized(this) {
//                val databaseName = "water_database"
//                instance ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    WaterDatabase::class.java,
//                    databaseName
//                ).build().also {
//                    instance = it
//                }
//            }
//        }
//    }
//}