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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.harutiro.geofencetestapp.Model.EntryData
import net.harutiro.geofencetestapp.Model.LatLng

// ここで取得できる
class MainViewModel(_app: Application): AndroidViewModel(_app) {
    val TAG = "MainViewModel"

    private val application = _app

    private var currentLatLng: LatLng = LatLng(0.0, 0.0) // 初期値は適当な値で
    private val _latlng = MutableStateFlow(LatLng(0.0,0.0))
    val latlng = _latlng.asStateFlow()

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

    // MainViewModel内で、UIを更新しないようにする関数を用意する
    fun setLatLngWithoutTriggeringUI(newLatLng: LatLng) {
        if (newLatLng != currentLatLng) {
            currentLatLng = newLatLng
            // UIを更新するStateを変更せずに、データのみを更新する
            _latlng.value = newLatLng
        }
    }

    fun startGeofence() {
        Log.d(TAG,"startGeofence")

        // application から activityを受け取る

        val entry = EntryData(
            key = "test",
            value = latlng.value
        )

        val radius = 300f

        geofenceRepository?.createGeofence(entry,radius)

        geofenceRepository?.addGeofences()

    }


    fun stopGeofence() {
        Log.d(TAG,"stopGeofence")
        geofenceRepository?.stopGeofece()
    }


}