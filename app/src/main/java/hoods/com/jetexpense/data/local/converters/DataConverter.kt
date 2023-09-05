package hoods.com.jetexpense.data.local.converters

import androidx.room.TypeConverter
import java.util.Date

open class DataConverter {

    @TypeConverter
    fun toDate(date: Long?): Date? {
        return date?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

}