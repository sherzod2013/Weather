package com.yusmp.domain.weather

import com.yusmp.domain.FlowUseCase
import com.yusmp.domain.auth.AuthDbDataSource
import com.yusmp.domain.weather.model.WeatherData
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

interface GetCurrentWeatherUseCase : FlowUseCase<String, WeatherData>

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val dataSource: WeatherRemoteDataSource,
    private val weatherDbDataSource: WeatherDbDataSource,
) : GetCurrentWeatherUseCase {
    override fun execute(param: String) = flow {
        val result = dataSource.getWeather(param)
        weatherDbDataSource.saveWeatherData(result)
        emit(result)
    }
}