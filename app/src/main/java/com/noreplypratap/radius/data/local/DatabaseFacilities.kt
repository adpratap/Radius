package com.noreplypratap.radius.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.noreplypratap.radius.model.Facilities
import com.noreplypratap.radius.model.Facility

@TypeConverters(Converters::class)
@Database(entities = [Facilities::class], version = 1, exportSchema = false)
abstract class DatabaseFacilities : RoomDatabase() {

    abstract fun getDao(): FacilitiesDao

    companion object {

        @Volatile
        private var instance: DatabaseFacilities? = null

        fun createDatabase(context: Context): DatabaseFacilities {

            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseFacilities::class.java,
                        "DatabaseFacilities"
                    ).build()
                }
            }
            return instance!!
        }
    }
}