package com.noreplypratap.radius.model

import androidx.room.TypeConverters
import com.noreplypratap.radius.data.local.Converters

data class Facility(
    val facility_id: String,
    val name: String,
    @TypeConverters(Converters::class)
    val options: List<Option>
)