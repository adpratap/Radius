package com.noreplypratap.radius.repository

import com.noreplypratap.radius.data.local.FacilitiesDao
import com.noreplypratap.radius.data.remote.ApiService
import com.noreplypratap.radius.model.Facilities
import com.noreplypratap.radius.utilities.logger
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val facilitiesDao: FacilitiesDao
) : Repository {

    //API Call
    override suspend fun getFacilitiesFromAPI(): Response<Facilities> {
        return apiService.getFacilities()
    }

    //UPDATE To DB
    override suspend fun updateFacilitiesToDB(facilities: Facilities) {
        facilitiesDao.saveFacilities(facilities)
    }

    //Read From DB
    override fun readFacilitiesFromDB(): Flow<Facilities> {
        return facilitiesDao.readFacilities()
    }

    //Worker Call
    override suspend fun backgroundApiCall() {
        try {
            val data = apiService.getFacilities()
            if (data.isSuccessful) {
                logger("backgroundApiCall... Success")
                data.body()?.let { facilitiesDao.saveFacilities(it) }
            }
        }catch (e: Exception){
            logger("Exception!!! on backgroundApiCall")
        }
    }
}