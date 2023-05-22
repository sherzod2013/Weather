package com.yusmp.data.net.weather.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationDataResponse(
    val name: String? = null,
    val region: String? = null,
    val country: String? = null,
    val lat: Double? = null,
    val lon: Double? = null
)