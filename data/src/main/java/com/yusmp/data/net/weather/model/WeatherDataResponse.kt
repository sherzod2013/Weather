package com.yusmp.data.net.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherDataResponse(
    val location: LocationDataResponse,
    val current: CurrentDataResponse,
    val forecast: ForecastDataResponse
)