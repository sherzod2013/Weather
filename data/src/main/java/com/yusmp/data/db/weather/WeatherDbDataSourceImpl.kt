package com.yusmp.data.db.weather

import com.yusmp.data.db.common.DbConverters.toDomain
import com.yusmp.data.db.common.DbConverters.toEntity
import com.yusmp.domain.weather.WeatherDbDataSource
import com.yusmp.domain.weather.model.WeatherData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherDbDataSourceImpl @Inject constructor(
    private val dao: WeatherDao
) : WeatherDbDataSource {
    override fun observeWeatherData(): Flow<WeatherData?> =
        dao.observeLastWeather().map { it?.toDomain() }


    override fun getWeatherData(): WeatherData? {
        return dao.getLastWeather()?.toDomain()
    }

    override fun saveWeatherData(weather: WeatherData) {
        dao.createWeather(weather.toEntity())
    }

    override fun clearWeatherData() {
        dao.clearWeathers()
    }
}