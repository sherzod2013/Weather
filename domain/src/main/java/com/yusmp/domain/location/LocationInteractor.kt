package com.yusmp.domain.location

import android.location.Location
import kotlinx.coroutines.flow.StateFlow

interface LocationInteractor {
    val lastLocation: StateFlow<Result<Location>?>
    fun startLocationUpdates()
    fun stopLocationUpdates()
}