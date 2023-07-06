package com.noreplypratap.radius.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.noreplypratap.radius.data.local.Converters

@Entity(tableName = "facilities")
data class Facilities(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @TypeConverters(Converters::class)
    val exclusions: List<List<Exclusion>>,
    @TypeConverters(Converters::class)
    val facilities: List<Facility>
)