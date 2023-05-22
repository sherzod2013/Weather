package com.yusmp.data.net.weather

import com.yusmp.data.net.common.NetConverters.toDomain
import com.yusmp.domain.common.Constants
import com.yusmp.domain.weather.WeatherRemoteDataSource
import com.yusmp.domain.weather.model.WeatherData
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRemoteDataSource {

    override suspend fun getWeather(location: String): WeatherData =
        weatherApi.getWeather(q = location, key = Constants.API_KEY).toDomain()
}