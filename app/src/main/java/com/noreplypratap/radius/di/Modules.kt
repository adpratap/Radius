package com.noreplypratap.radius.di

import android.app.Application
import android.content.Context
import com.noreplypratap.radius.data.local.DatabaseFacilities
import com.noreplypratap.radius.data.local.FacilitiesDao
import com.noreplypratap.radius.data.remote.ApiService
import com.noreplypratap.radius.utilities.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : DatabaseFacilities {
        return DatabaseFacilities.createDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(databaseFacilities: DatabaseFacilities) : FacilitiesDao {
        return databaseFacilities.getDao()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(base_URL: String , okHttpClient: OkHttpClient ): Retrofit {
        return Retrofit.Builder().baseUrl(base_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideBaseURL(): String {
        return Constants.BaseURL
    }

    @Provides
    fun provideAppContext(application: Application): Context {
        return application.applicationContext
    }

}
