package com.yusmp.data.db.weather

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY id DESC LIMIT 1")
    fun observeLastWeather(): Flow<WeatherDataEntity?>

    @Query("SELECT * FROM weather ORDER BY id DESC LIMIT 1")
    fun getLastWeather(): WeatherDataEntity?

    @Insert(entity = WeatherDataEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun createWeather(entity: WeatherDataEntity): Long

    @Query("DELETE FROM weather")
    fun clearWeathers()
}
