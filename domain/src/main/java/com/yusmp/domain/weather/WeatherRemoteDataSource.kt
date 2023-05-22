package com.yusmp.domain.weather

import com.yusmp.domain.weather.model.WeatherData

interface WeatherRemoteDataSource {
    suspend fun getWeather(location: String): WeatherData
}