package com.yusmp.basecode.presentation.currentWeather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusmp.basecode.base.recyclerAdapter.adapters.RecyclerDataStateAdapter
import com.yusmp.basecode.databinding.ItemWeatherByHourBinding
import com.yusmp.basecode.presentation.currentWeather.model.HourDataUi
import com.yusmp.domain.weather.model.HourData

class WeatherByHourAdapter() :
    RecyclerDataStateAdapter<HourDataUi, WeatherByHourViewHolderArticleViewHolder>() {

    override fun createLoadingStateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        WeatherByHourViewHolderArticleViewHolder(
            ItemWeatherByHourBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun createDataStateViewHolder(parent: ViewGroup): WeatherByHourViewHolderArticleViewHolder =
        WeatherByHourViewHolderArticleViewHolder(
            binding = ItemWeatherByHourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}