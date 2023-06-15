package net.harutiro.geofencetestapp.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    val TAG = "GeofenceBroadcastReceiver"
    // ...
    override fun onReceive(context: Context?, intent: Intent?) {

        val geofencingEvent = if(intent != null){
            GeofencingEvent.fromIntent(intent)
        }else{
            null
        }

        if (geofencingEvent?.hasError() == true) {
            val errorMessage = GeofenceStatusCodes
                .getStatusCodeString(geofencingEvent.errorCode)
            Log.e(TAG, errorMessage)
            return
        }

        // Get the transition type.
        val geofenceTransition = geofencingEvent?.geofenceTransition

        // Test that the reported transition was of interest.
        if (
            geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
            geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT
        ) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            val triggeringGeofences = geofencingEvent.triggeringGeofences

//            // Get the transition details as a String.
//            val geofenceTransitionDetails = getGeofenceTransitionDetails(
//                this,
//                geofenceTransition,
//                triggeringGeofences
//            )
//
//            // Send notification and log the transition details.
//            sendNotification(geofenceTransitionDetails)
            Log.i(TAG, "とりあえずジオフェンスのイベントが走った" + triggeringGeofences.toString())
        } else {
            // Log the error
            Log.e(TAG, "geofence error")
        }
    }
}