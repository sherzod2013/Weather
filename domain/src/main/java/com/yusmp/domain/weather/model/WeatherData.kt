package com.yusmp.domain.weather.model

data class WeatherData(
    val location: LocationData,
    val current: CurrentWeatherData,
    val forecast: ForecastData
)