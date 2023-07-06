package com.noreplypratap.radius.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.noreplypratap.radius.model.Facilities
import com.noreplypratap.radius.repository.RepositoryImpl
import com.noreplypratap.radius.utilities.Resource
import com.noreplypratap.radius.utilities.logger
import com.noreplypratap.radius.worker.FacilitiesWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _data = MutableLiveData<Resource<Facilities>>()
    val data: LiveData<Resource<Facilities>> get() = _data

    init {
        loadFromDB()
    }

    fun loadFromDB() = viewModelScope.launch {
        _data.postValue(Resource.Loading())
        repositoryImpl.readFacilitiesFromDB().collect {
            _data.postValue(Resource.Success(it))
        }
    }

    //API Call
    fun refreshFacilities() = viewModelScope.launch {
        try {
            val res = repositoryImpl.getFacilitiesFromAPI()
            if (res.isSuccessful){
                res.body()?.let {
                    //Saving/Updating to DB
                    repositoryImpl.updateFacilitiesToDB(it)
                }
            }
        }catch (e: Exception){
            logger("Exception...")
        }
    }

}
