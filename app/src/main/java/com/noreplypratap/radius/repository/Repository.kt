package com.noreplypratap.radius.repository

import com.noreplypratap.radius.model.Facilities
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {

    suspend fun getFacilitiesFromAPI(): Response<Facilities>

    suspend fun updateFacilitiesToDB(facilities: Facilities)

    fun readFacilitiesFromDB(): Flow<Facilities>

    suspend fun backgroundApiCall()

}