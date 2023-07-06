package com.noreplypratap.radius.data.remote

import com.noreplypratap.radius.model.Facilities
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/iranjith4/ad-assignment/db")
    suspend fun getFacilities(): Response<Facilities>

}

