package com.yusmp.domain.weather

import com.yusmp.domain.weather.model.WeatherData
import kotlinx.coroutines.flow.Flow

interface WeatherDbDataSource {
    fun observeWeatherData(): Flow<WeatherData?>
    fun getWeatherData(): WeatherData?
    fun saveWeatherData(session: WeatherData)
    fun clearWeatherData()
}