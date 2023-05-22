package com.yusmp.data.net.weather

import com.yusmp.data.net.common.model.HeaderFlags
import com.yusmp.data.net.weather.model.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @Headers(HeaderFlags.NoToken.formattedHeader)
    @GET("forecast.json")
    suspend fun getWeather(@Query("q") q: String, @Query("key") key: String, @Query("days") days: Int = 10, @Query("lang") lang: String = "ru" ): WeatherDataResponse
}