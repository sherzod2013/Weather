package com.yusmp.domain.weather

import com.yusmp.domain.LocalFlowUseCase
import com.yusmp.domain.weather.model.WeatherData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCurrentWeatherDbUseCase : LocalFlowUseCase<Unit, WeatherData?>

class GetCurrentWeatherDbUseCaseImpl @Inject constructor(
    private val weatherDbDataSource: WeatherDbDataSource,
) : GetCurrentWeatherDbUseCase {

    override fun execute(param: Unit): Flow<WeatherData?> = weatherDbDataSource.observeWeatherData()
}