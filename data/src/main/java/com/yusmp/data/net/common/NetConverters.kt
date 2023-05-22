package com.yusmp.data.net.common

import com.yusmp.data.net.auth.model.AuthDataResponse
import com.yusmp.data.net.common.NetConverters.toDomain
import com.yusmp.data.net.common.model.ApiErrorResponse
import com.yusmp.data.net.common.model.ApiSuccessResponse
import com.yusmp.data.net.common.model.ErrorItemResponse
import com.yusmp.data.net.weather.model.ConditionResponse
import com.yusmp.data.net.weather.model.CurrentDataResponse
import com.yusmp.data.net.weather.model.DayResponse
import com.yusmp.data.net.weather.model.ForecastDataResponse
import com.yusmp.data.net.weather.model.ForecastDayResponse
import com.yusmp.data.net.weather.model.HourResponse
import com.yusmp.data.net.weather.model.LocationDataResponse
import com.yusmp.data.net.weather.model.WeatherDataResponse
import com.yusmp.domain.auth.model.AuthData
import com.yusmp.domain.common.extentions.convertToDate
import com.yusmp.domain.common.extentions.orDefault
import com.yusmp.domain.common.extentions.orZero
import com.yusmp.domain.common.model.ApiError
import com.yusmp.domain.common.model.ApiSuccess
import com.yusmp.domain.common.model.ErrorItem
import com.yusmp.domain.weather.model.ConditionData
import com.yusmp.domain.weather.model.CurrentWeatherData
import com.yusmp.domain.weather.model.DayData
import com.yusmp.domain.weather.model.ForecastData
import com.yusmp.domain.weather.model.ForecastDayData
import com.yusmp.domain.weather.model.HourData
import com.yusmp.domain.weather.model.LocationData
import com.yusmp.domain.weather.model.WeatherData

@Suppress("TooManyFunctions", "LargeClass")
object NetConverters {

    fun AuthDataResponse.toDomain() = AuthData(
        accessToken = token.orEmpty(),
        refreshToken = refreshToken.orEmpty()
    )

    fun ApiErrorResponse?.toDomain() = ApiError(
        extraMessage = this?.extraMessage.orEmpty(),
        code = this?.code.orDefault(),
        message = this?.message.orEmpty(),
        violations = this?.violations?.map {
            it.toDomain()
        }.orEmpty(),
        key = this?.key.orEmpty(),
        email = this?.mail.orEmpty()
    )

    fun ErrorItemResponse?.toDomain() = ErrorItem(
        propertyPath = this?.propertyPath.orEmpty(),
        key = this?.key.orEmpty(),
        message = this?.message.orEmpty()
    )

    fun ApiSuccessResponse.toDomain() = ApiSuccess(
        message = message.orEmpty()
    )

    fun CurrentDataResponse.toDomain() = CurrentWeatherData(
        lastUpdated = lastUpdated.orEmpty(),
        tempC = tempC.orZero(),
        condition = condition.toDomain(),
        windMph = windMph.orZero(),
        cloud = cloud.orZero()
    )

    fun ConditionResponse.toDomain() = ConditionData(
        text = text.orEmpty(),
        icon = "http://"+icon.orEmpty()
    )

    fun ForecastDataResponse.toDomain() = ForecastData(
        forecastday.map { it.toDomain() }
    )

    fun ForecastDayResponse.toDomain() = ForecastDayData(
        date.orEmpty().convertToDate(),
        day.toDomain(),
        hour.map { it.toDomain() }
    )

    fun DayResponse.toDomain() = DayData(
        maxtempC = maxtempC.orZero(),
        mintempC = mintempC.orZero(),
        maxwindMH = maxwindMH.orZero(),
        condition = condition.toDomain()
    )

    fun HourResponse.toDomain() = HourData(
        time.orEmpty().convertToDate("yyyy-MM-dd HH:mm"),
        tempC.orZero(),
        condition.toDomain()
    )

    fun LocationDataResponse.toDomain() = LocationData(
        name.orEmpty(),
        region.orEmpty(),
        country.orEmpty(),
        lat.orZero(),
        lon.orZero()
    )

    fun WeatherDataResponse.toDomain() = WeatherData(
        location.toDomain(),
        current.toDomain(),
        forecast.toDomain()
    )
}