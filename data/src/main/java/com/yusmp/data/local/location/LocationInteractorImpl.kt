package com.yusmp.data.local.location

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import com.yusmp.domain.common.Constants.ONE_SECOND_IN_MS
import com.yusmp.domain.location.LocationInteractor
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocationInteractorImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val settingsClient: SettingsClient
) : LocationInteractor {
    private val callback = Callback()
    private val locationUpdateIntervalInMillis = ONE_SECOND_IN_MS * 5L

    private val _lastLocation = MutableStateFlow<Result<Location>?>(null)
    override val lastLocation: StateFlow<Result<Location>?> = _lastLocation.asStateFlow()

    @SuppressLint("MissingPermission") // Only called when holding location permission.
    override fun startLocationUpdates() {
        val request = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, locationUpdateIntervalInMillis)
            .setWaitForAccurateLocation(true)
            .build()
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)
        settingsClient.checkLocationSettings(builder.build())
            .addOnSuccessListener {
                fusedLocationProviderClient.requestLocationUpdates(
                    request,
                    callback,
                    Looper.getMainLooper()
                )
            }
            .addOnFailureListener { exception ->
                _lastLocation.value = Result.failure(exception)
            }
    }

    override fun stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(callback)
        _lastLocation.value = null
    }

    private inner class Callback : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            _lastLocation.value = Result.success(result.lastLocation ?: return)
        }
    }
}
