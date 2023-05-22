package com.yusmp.basecode.app.hilt.dataSourcesUsecasesModules

import com.yusmp.domain.weather.GetCurrentWeatherUseCase
import com.yusmp.domain.weather.GetCurrentWeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface WeatherModule {
    @Binds
    fun bindGetCurrentWeatherUseCase(impl: GetCurrentWeatherUseCaseImpl): GetCurrentWeatherUseCase
}