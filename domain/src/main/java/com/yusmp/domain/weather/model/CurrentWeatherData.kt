package com.yusmp.domain.weather.model

data class CurrentWeatherData(
    val lastUpdated: String,
    val tempC: Double,
    val condition: ConditionData,
    val windMph: Double,
    val cloud: Int
)

data class ConditionData(
    val text: String,
    val icon: String
)