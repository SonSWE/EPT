package com.example.ept.ObjectInfor

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.StringBuilder

@Entity
data class AlarmSleepInfo(
    @PrimaryKey (autoGenerate = true) val uid: Long?,
    @ColumnInfo val hour : Int,
    @ColumnInfo val minute : Int,
    @ColumnInfo val mon : Boolean,
    @ColumnInfo val tue : Boolean,
    @ColumnInfo val web : Boolean,
    @ColumnInfo val thu : Boolean,
    @ColumnInfo val fri : Boolean,
    @ColumnInfo val sat : Boolean,
    @ColumnInfo val sun : Boolean,
    @ColumnInfo val start : Boolean,

) {
    constructor(id: Long, hour: Int, minute: Int) : this(
        id,
        hour,
        minute,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
    )

    fun getTime() :String{
        return "$hour:$minute"
    }

    fun getRepeat():String{
        var builder = StringBuilder()

        if (mon){
            builder.append("Mon")
        }
        if (tue){
            builder.append(",Tue")
        }
        if (web){
            builder.append(",Web")
        }
        if (thu){
            builder.append(",Thu")
        }
        if (fri){
            builder.append(",Fri")
        }
        if (sat){
            builder.append(",Sat")
        }

        if (sun){
            builder.append(",Sun")
        }
        return  builder.toString()
    }


}
