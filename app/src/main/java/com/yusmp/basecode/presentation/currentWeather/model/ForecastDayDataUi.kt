package com.yusmp.basecode.presentation.currentWeather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class ForecastDayDataUi(
    val date: Date?,
    val day: DayDataUi,
    val hour: List<HourDataUi>
): Parcelable

@Parcelize
data class DayDataUi(
    val maxtempC: Double,
    val mintempC: Double,
    val maxwindMH: Double,
    val condition: ConditionDataUi
): Parcelable

@Parcelize
data class HourDataUi(
    val time: Date?,
    val tempC: Double,
    val condition: ConditionDataUi
): Parcelable

@Parcelize
data class ConditionDataUi(
    val text: String,
    val icon: String
): Parcelable