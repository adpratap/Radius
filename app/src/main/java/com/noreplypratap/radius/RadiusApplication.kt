package com.noreplypratap.radius

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.noreplypratap.radius.utilities.Constants
import com.noreplypratap.radius.utilities.logger
import com.noreplypratap.radius.worker.FacilitiesWorker
import com.noreplypratap.radius.worker.FacilitiesWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class RadiusApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var facilitiesWorkerFactory: FacilitiesWorkerFactory

    override fun onCreate() {
        super.onCreate()
        setupWorker()
    }

    private fun setupWorker() {
        logger("setupWorker... Application")
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workRequest =
            PeriodicWorkRequest.Builder(FacilitiesWorker::class.java, 1, TimeUnit.DAYS)
                .setConstraints(constraints).build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(facilitiesWorkerFactory).build()
}
