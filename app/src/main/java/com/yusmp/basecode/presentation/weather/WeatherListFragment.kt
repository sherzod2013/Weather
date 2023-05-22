package com.yusmp.basecode.presentation.weather

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.badoo.mvicore.modelWatcher
import com.yusmp.basecode.app.hilt.LocationPermissionObserver
import com.yusmp.basecode.app.setUpLocationObserver
import com.yusmp.basecode.databinding.FragmentWeatherBinding
import com.yusmp.basecode.presentation.common.BaseFragment
import com.yusmp.basecode.presentation.weather.adapter.WeatherAdapter
import com.yusmp.basecode.presentation.weather.listener.PermissionListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherListFragment : BaseFragment<FragmentWeatherBinding, WeatherListState, WeatherListEvent>(),
    PermissionListener {

    override val viewModel: WeatherListViewModel by viewModels()
    private var weatherAdapter: WeatherAdapter? = null

    @Inject
    lateinit var locationPermission: LocationPermissionObserver

    override val stateRenderer = modelWatcher {
        WeatherListState::isLoading { isLoading ->

        }
        WeatherListState::forecastDayData { forecastData ->
            weatherAdapter?.submitList(forecastData)
        }
        WeatherListState::weatherData { weatherData ->
            binding.currentWeatherTv.text = weatherData?.current?.tempC.toString()
            binding.conditionTv.text = weatherData?.current?.condition?.text
            binding.countryTv.text = weatherData?.location?.name
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpLocationObserver(locationPermission)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentWeatherBinding {
        return FragmentWeatherBinding.inflate(inflater, container, false)
    }

    override fun FragmentWeatherBinding.setupViews() {
        locationPermission.checkPermission()
        weatherAdapter = WeatherAdapter(WeatherAdapter.Params {
            findNavController().navigate(WeatherListFragmentDirections.toCurrentWeather(it))
        })
        binding.weatherRv.adapter = weatherAdapter
        binding.weatherRv.layoutManager = LinearLayoutManager(requireContext())
        viewModel.openGSMSettings.observe(viewLifecycleOwner) {
            locationPermission.askOpenGPS(it)
        }
    }

    override fun WeatherListEvent.handleEvent() {
        when (this) {
            is WeatherListEvent.NavigateToWeatherScreen -> {

            }
        }
    }

    override fun permissionGranted() {
        Log.e("locationIssues", " permissionGranted")
        viewModel.startLocationUpdates()
    }

    override fun permissionDenied() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}