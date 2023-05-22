package com.yusmp.data.net.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDataResponse(
    val forecastday: List<ForecastDayResponse>
)

@Serializable
data class ForecastDayResponse(
    val date: String? = null,
    val day: DayResponse,
    val hour: List<HourResponse>
)

@Serializable
data class DayResponse(
    @SerialName("maxtemp_c") val maxtempC: Double? = null,
    @SerialName("mintemp_c") val mintempC: Double? = null,
    @SerialName("maxwind_mph") val maxwindMH: Double? = null,
    @SerialName("condition") val condition: ConditionResponse
)

@Serializable
data class HourResponse(
    @SerialName("time") val time: String? = null,
    @SerialName("temp_c") val tempC: Double? = null,
    @SerialName("condition") val condition: ConditionResponse
)