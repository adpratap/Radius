package com.noreplypratap.radius.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.noreplypratap.radius.model.Facilities
import com.noreplypratap.radius.model.Facility
import kotlinx.coroutines.flow.Flow

@Dao
interface FacilitiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFacilities(facilities: Facilities)

    @Query("SELECT * From facilities")
    fun readFacilities(): Flow<Facilities>

    @Query("DELETE FROM facilities")
    suspend fun deleteDatabase()

}