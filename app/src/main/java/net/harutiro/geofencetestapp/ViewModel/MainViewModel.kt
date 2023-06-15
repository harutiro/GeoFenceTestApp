package net.harutiro.geofencetestapp.ViewModel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import net.harutiro.geofencetestapp.Model.EntryData
import net.harutiro.geofencetestapp.Model.LatLng

// ここで取得できる
class MainViewModel(_app: Application): AndroidViewModel(_app) {
    val TAG = "MainViewModel"

    private val application = _app

    // TODO:Staticで持ってくるのを止める。
    companion object {
        var activity: Activity? = null
    }
    var geofenceRepository:GeofenceRepository? = null


    init {
        if(activity != null){
           geofenceRepository = GeofenceRepository(activity!!)
        }
    }

    fun startGeofence() {
        Log.d(TAG,"startGeofence")

        // application から activityを受け取る

        val entry = EntryData(
            key = "test",
            value =  LatLng(
                34.810950,
                137.313826
            )
        )

        val radius = 100f

        geofenceRepository?.createGeofence(entry,radius)

        geofenceRepository?.addGeofences()

    }


    fun stopGeofence() {
        Log.d(TAG,"stopGeofence")
        geofenceRepository?.stopGeofece()
    }


}