package com.yusmp.data.db.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusmp.data.db.auth.SESSION_FIXED_ID

@Entity(tableName = "weather")
data class WeatherDataEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = SESSION_FIXED_ID,
    val weather: String,
)