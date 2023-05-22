package com.yusmp.basecode.presentation.currentWeather

import com.yusmp.basecode.base.recyclerAdapter.RecyclerDataState
import com.yusmp.basecode.presentation.common.UiEvent
import com.yusmp.basecode.presentation.common.UiState
import com.yusmp.basecode.presentation.currentWeather.model.HourDataUi
import com.yusmp.domain.weather.model.HourData

data class CurrentWeatherUiState(
    val hourData: List<RecyclerDataState<HourDataUi>> = RecyclerDataState.createLoadingState()
) : UiState

sealed class CurrentWeatherEvent : UiEvent() {
    object NavigateBack : CurrentWeatherEvent()
}