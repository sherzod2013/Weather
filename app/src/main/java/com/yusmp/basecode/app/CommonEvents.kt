package com.yusmp.basecode.app

import android.content.Context
import androidx.activity.result.ActivityResultCaller
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.yusmp.basecode.app.hilt.LocationPermissionObserver
import com.yusmp.basecode.presentation.weather.listener.PermissionListener

fun setUpLocationObserver(
    context: Context,
    activityResultCaller: ActivityResultCaller,
    lifecycle: Lifecycle,
    listener: PermissionListener,
    locationPermission: LocationPermissionObserver
) {
    locationPermission.init(
        context = context,
        activityResultCaller = activityResultCaller,
        listener = listener,
    )
    lifecycle.addObserver(locationPermission.lifecycleEventObserver)
    locationPermission.permissionResult = listener
}

fun <T> T.setUpLocationObserver(locationPermission: LocationPermissionObserver) where T : Fragment, T : PermissionListener {
    setUpLocationObserver(
        context = requireContext(),
        activityResultCaller = this,
        lifecycle = lifecycle,
        listener = this,
        locationPermission = locationPermission
    )
}

fun <T> T.setUpLocationObserver(locationPermission: LocationPermissionObserver) where T : AppCompatActivity, T : PermissionListener {
    setUpLocationObserver(
        context = this,
        activityResultCaller = this,
        lifecycle = lifecycle,
        listener = this,
        locationPermission = locationPermission
    )
}


