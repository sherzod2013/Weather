package com.yusmp.basecode.app.hilt

import android.content.Context
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultCaller
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.common.api.ResolvableApiException
import com.yusmp.basecode.presentation.weather.listener.PermissionListener

abstract class AbstractPermissionObserver : LifecycleObserver {

    abstract var permissionResult: PermissionListener?

    abstract val lifecycleEventObserver: LifecycleEventObserver

    protected abstract var appContext: Context?

    protected abstract var activityResultCaller: ActivityResultCaller?

    protected abstract var layoutInflater: LayoutInflater?

    abstract fun init(
        context: Context?,
        activityResultCaller: ActivityResultCaller?,
        listener: PermissionListener?,
    )

    abstract fun checkPermission()

    abstract fun askOpenGPS(exception: ResolvableApiException)

    protected abstract fun launch()
}