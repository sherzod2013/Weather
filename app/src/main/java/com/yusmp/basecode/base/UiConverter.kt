package com.yusmp.basecode.base

import com.yusmp.basecode.base.UiConverter.toUi
import com.yusmp.basecode.presentation.currentWeather.model.ConditionDataUi
import com.yusmp.basecode.presentation.currentWeather.model.DayDataUi
import com.yusmp.basecode.presentation.currentWeather.model.ForecastDayDataUi
import com.yusmp.basecode.presentation.currentWeather.model.HourDataUi
import com.yusmp.domain.weather.model.ConditionData
import com.yusmp.domain.weather.model.DayData
import com.yusmp.domain.weather.model.ForecastDayData
import com.yusmp.domain.weather.model.HourData

object UiConverter {

    fun ForecastDayData.toUi() = ForecastDayDataUi(
        date = date,
        day = day.toUi(),
        hour = hour.map { it.toUi() }
    )

    fun DayData.toUi() = DayDataUi(
        maxtempC = maxtempC,
        mintempC = mintempC,
        maxwindMH = maxwindMH,
        condition = condition.toUi()
    )

    fun ConditionData.toUi() = ConditionDataUi(
        text = text,
        icon = icon
    )

    fun HourData.toUi() = HourDataUi(
        time = time,
        tempC = tempC,
        condition = condition.toUi()
    )
}