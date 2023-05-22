package com.yusmp.basecode.presentation.currentWeather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.badoo.mvicore.modelWatcher
import com.yusmp.basecode.databinding.FragmentCurrentWeatherBinding
import com.yusmp.basecode.presentation.common.BaseFragment
import com.yusmp.basecode.presentation.currentWeather.adapter.WeatherByHourAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment :
    BaseFragment<FragmentCurrentWeatherBinding, CurrentWeatherUiState, CurrentWeatherEvent>() {

    override val viewModel: CurrentWeatherViewModel by viewModels()
    val adapter = WeatherByHourAdapter()

    override val stateRenderer = modelWatcher {
        CurrentWeatherUiState::hourData { hourDate ->
            adapter.submitList(hourDate)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCurrentWeatherBinding {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false)
    }

    override fun FragmentCurrentWeatherBinding.setupViews() {
        list.adapter = adapter
    }

    override fun CurrentWeatherEvent.handleEvent() {
        when (this) {
            is CurrentWeatherEvent.NavigateBack -> {

            }
        }
    }
}