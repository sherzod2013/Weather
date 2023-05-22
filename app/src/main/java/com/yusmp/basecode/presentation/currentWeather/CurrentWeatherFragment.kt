package com.yusmp.basecode.presentation.currentWeather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.badoo.mvicore.modelWatcher
import com.yusmp.basecode.databinding.FragmentCurrentWeatherBinding
import com.yusmp.basecode.presentation.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment :
    BaseFragment<FragmentCurrentWeatherBinding, CurrentWeatherUiState, CurrentWeatherEvent>() {

    override val viewModel: CurrentWeatherViewModel by viewModels()

    override val stateRenderer = modelWatcher {
        CurrentWeatherUiState::hourData { hourDate ->

        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentCurrentWeatherBinding {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false)
    }

    override fun FragmentCurrentWeatherBinding.setupViews() {

    }

    override fun CurrentWeatherEvent.handleEvent() {
        when (this) {
            is CurrentWeatherEvent.NavigateBack -> {

            }
        }
    }
}