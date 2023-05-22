package com.yusmp.basecode.app.hilt.dataSourcesUsecasesModules

import com.yusmp.data.net.weather.WeatherRemoteDataSourceImpl
import com.yusmp.domain.weather.WeatherRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindWeatherRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}