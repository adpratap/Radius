package com.noreplypratap.radius.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.noreplypratap.radius.repository.RepositoryImpl
import com.noreplypratap.radius.utilities.isOnline
import com.noreplypratap.radius.utilities.logger
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltWorker
class FacilitiesWorker @AssistedInject constructor (
    @Assisted private val context: Context,
    @Assisted params: WorkerParameters,
    @Assisted private val repositoryImpl: RepositoryImpl
) : Worker(context, params) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            logger("Worker Called ... ")
            if (context.isOnline()){
                repositoryImpl.backgroundApiCall()
            }else {
                logger("No Internet ... ")
            }
        }
        return Result.success()
    }
}