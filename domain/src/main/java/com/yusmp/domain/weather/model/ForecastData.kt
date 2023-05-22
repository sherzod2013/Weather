package com.yusmp.domain.weather.model

import java.util.Date

data class ForecastData(
    val forecastday: List<ForecastDayData>
)

data class ForecastDayData(
    val date: Date?,
    val day: DayData,
    val hour: List<HourData>
)

data class DayData(
    val maxtempC: Double,
    val mintempC: Double,
    val maxwindMH: Double,
    val condition: ConditionData
)

data class HourData(
    val time: String,
    val tempC: Double,
    val condition: ConditionData
)