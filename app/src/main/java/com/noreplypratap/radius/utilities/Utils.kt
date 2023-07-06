package com.noreplypratap.radius.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.noreplypratap.radius.R
import com.noreplypratap.radius.model.Facilities
import com.noreplypratap.radius.model.Option

fun logger(msg: String) {
    Log.d("RadiusAppTag!!!!",msg)
}


fun getIconResourceId(iconName: String): Int {
    return when (iconName) {
        "apartment" -> R.drawable.apartment2x
        "condo" -> R.drawable.condo2x
        "boat" -> R.drawable.boat2x
        "land" -> R.drawable.land2x
        "room" -> R.drawable.rooms2x
        "swimming" -> R.drawable.swimming2x
        "garden" -> R.drawable.garden2x
        "no-room" -> R.drawable.noroom2x
        "garage" -> R.drawable.garage2x
        // Add more icon mappings as needed
        else -> R.drawable.apartment2x
    }
}

fun getNewOptionsList(id: String, facilities: Facilities, option: List<Option>, pos: Int): List<Option>{
    val notTOUse: MutableList<String> = mutableListOf()
    facilities.exclusions.forEach {
        it.forEach { d ->
            if (d.facility_id == id){
                notTOUse.add(d.options_id)
            }
        }
    }
    val newOptions: MutableList<Option> = mutableListOf()

    facilities.facilities.get(pos).options.forEach { it ->
        notTOUse.forEach { x ->
            if (x != it.id){
                if (!newOptions.contains(it)){
                    newOptions.add(it)
                }
            }

        }
    }

    return newOptions
}

fun Context.isOnline() : Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}
