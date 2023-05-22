package com.yusmp.basecode.app.hilt

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.common.api.ResolvableApiException
import com.yusmp.basecode.R
import com.yusmp.basecode.presentation.weather.listener.PermissionListener
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LocationPermissionObserver @Inject constructor(
) : AbstractPermissionObserver() {

    private var requestPermission: ActivityResultLauncher<Array<String>>? = null

    private var resolutionForResult: ActivityResultLauncher<IntentSenderRequest>? = null

    private var settingsDialog: AlertDialog? = null

    private var locationDialog: AlertDialog? = null

    override var permissionResult: PermissionListener? = null

    override var appContext: Context? = null

    override var activityResultCaller: ActivityResultCaller? = null

    override var layoutInflater: LayoutInflater? = null

    override val lifecycleEventObserver = LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                activityResultCaller?.let { activity ->
                    resolutionForResult =
                        activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { activityResult ->
                            if (activityResult.resultCode == AppCompatActivity.RESULT_OK) {
                                permissionResult?.permissionGranted()
                            }
                        }
                    requestPermission =
                        activity.registerForActivityResult(
                            ActivityResultContracts.RequestMultiplePermissions()
                        ) { permissions ->
                            if (permissions.getOrDefault(
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    false
                                )
                            ) {
                                // Location granted.
                                Log.e("locationIssues", " ${permissionResult.toString()}")
                                permissionResult?.permissionGranted()
                            } else {
                                // No location access granted.
                            }

                        }
                }

            }
            Lifecycle.Event.ON_DESTROY -> {
                requestPermission = null
                settingsDialog = null
                locationDialog = null
                permissionResult = null
                appContext = null
                activityResultCaller = null
            }

            else -> {
                // do nothing
            }
        }
    }

    override fun init(
        context: Context?,
        activityResultCaller: ActivityResultCaller?,
        listener: PermissionListener?,
    ) {
        this.appContext = context
        this.activityResultCaller = activityResultCaller
        this.permissionResult = listener
        this.layoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun launch() {
        requestPermission?.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun checkPermission() {
        appContext?.let { ctx ->
            activityResultCaller?.let { activity ->
                when (PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.checkSelfPermission(
                        ctx,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) -> permissionResult?.permissionGranted()
                    else -> showLocationDialog()
                }
            }

        }
    }

    override fun askOpenGPS(exception: ResolvableApiException) {
        val intentSenderRequest =
            IntentSenderRequest.Builder(exception.resolution).build()
        resolutionForResult?.launch(intentSenderRequest)
    }

    private fun showLocationDialog() {
        locationDialog?.dismiss()
        appContext?.let { ctx ->
            val view =
                layoutInflater?.inflate(
                    R.layout.dialog_like_ios_fragment,
                    null
                )
            val button = view?.findViewById<TextView>(R.id.right)
            val left = view?.findViewById<TextView>(R.id.left)
            button?.setOnClickListener {
                locationDialog?.dismiss()
                launch()
            }
            left?.setOnClickListener {
                locationDialog?.dismiss()
            }
            AlertDialog.Builder(ctx)
                .setView(view)
                .setCancelable(false)
                .show()
                .also { locationDialog = it }
        }
    }


    fun clearDialog() {
        locationDialog?.dismiss()
    }
}