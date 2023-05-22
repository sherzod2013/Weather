package com.yusmp.data.net.weather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDataResponse(
    @SerialName("last_updated") val lastUpdated: String? = null,
    @SerialName("temp_c") val tempC: Double? = null,
    val condition: ConditionResponse,
    @SerialName("wind_mph") val windMph: Double? = null,
    val cloud: Int? = null
)

@Serializable
data class ConditionResponse(
    val text: String? = null,
    val icon: String? = null
)