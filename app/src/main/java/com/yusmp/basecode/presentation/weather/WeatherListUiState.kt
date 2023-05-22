package com.yusmp.basecode.presentation.weather

import com.yusmp.basecode.base.recyclerAdapter.RecyclerDataState
import com.yusmp.basecode.presentation.common.UiEvent
import com.yusmp.basecode.presentation.common.UiState
import com.yusmp.domain.weather.model.ForecastDayData
import com.yusmp.domain.weather.model.WeatherData

data class WeatherListState(
    val forecastDayData: List<RecyclerDataState<ForecastDayData>>? = RecyclerDataState.createLoadingState(),
    val weatherData: WeatherData? = null,
    val isLoading: Boolean = true,
) : UiState

sealed class WeatherListEvent : UiEvent() {
    object NavigateToWeatherScreen : WeatherListEvent()
}