package com.noreplypratap.radius.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.noreplypratap.radius.repository.RepositoryImpl
import javax.inject.Inject

class FacilitiesWorkerFactory @Inject constructor(private val repositoryImpl: RepositoryImpl) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = FacilitiesWorker(appContext,workerParameters,repositoryImpl)
}