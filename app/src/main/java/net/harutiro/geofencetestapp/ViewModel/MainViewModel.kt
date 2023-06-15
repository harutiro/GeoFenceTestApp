package net.harutiro.geofencetestapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    val TAG = "MainViewModel"

    fun startGeofence() {
        Log.d(TAG,"startGeofence")
    }
}