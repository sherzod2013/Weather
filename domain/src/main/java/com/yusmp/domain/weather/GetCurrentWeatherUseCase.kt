package com.yusmp.domain.weather

import com.yusmp.domain.FlowUseCase
import com.yusmp.domain.weather.model.WeatherData
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

interface GetCurrentWeatherUseCase : FlowUseCase<String, WeatherData>

class GetCurrentWeatherUseCaseImpl @Inject constructor(
    private val dataSource: WeatherRemoteDataSource
) : GetCurrentWeatherUseCase {
    override fun execute(param: String) = flow {
        val result = dataSource.getWeather(param)
        emit(result)
    }
}