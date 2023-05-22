package com.yusmp.data.db.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yusmp.data.db.auth.SessionDao
import com.yusmp.data.db.auth.SessionEntity
import com.yusmp.data.db.weather.WeatherDao
import com.yusmp.data.db.weather.WeatherDataEntity

@Database(
    version = 1,
    entities = [
        SessionEntity::class,
        WeatherDataEntity::class
    ],
)
abstract class AppDatabase : RoomDatabase() {
    public abstract fun sessionDao(): SessionDao
    public abstract fun weatherDao(): WeatherDao
}