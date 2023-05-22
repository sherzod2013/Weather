package com.yusmp.basecode.presentation.currentWeather

import androidx.lifecycle.SavedStateHandle
import com.yusmp.basecode.base.recyclerAdapter.RecyclerDataState
import com.yusmp.basecode.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<CurrentWeatherUiState, CurrentWeatherEvent>(CurrentWeatherUiState()) {

    private val args = CurrentWeatherFragmentArgs.fromSavedStateHandle(savedStateHandle)

    init {
        updateUiState {
            copy(hourData = args.param.hour.map {
                RecyclerDataState.Data(
                    it.toString(), it
                )
            })
        }
    }

}