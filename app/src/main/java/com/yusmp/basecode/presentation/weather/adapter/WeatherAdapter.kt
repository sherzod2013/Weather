package com.yusmp.basecode.presentation.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusmp.basecode.base.recyclerAdapter.BindableViewHolder
import com.yusmp.basecode.base.recyclerAdapter.adapters.RecyclerDataStateAdapter
import com.yusmp.basecode.databinding.ItemWeatherBinding
import com.yusmp.basecode.databinding.ItemWeatherShimmerBinding
import com.yusmp.basecode.presentation.common.extentions.setImageFromUrl
import com.yusmp.basecode.presentation.common.utils.setSafeOnClickListener
import com.yusmp.domain.weather.model.ForecastDayData
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherAdapter(private val params: Params) :
    RecyclerDataStateAdapter<ForecastDayData, WeatherAdapter.WeatherListViewHolder>() {

    class WeatherListViewHolder(
        private val binding: ItemWeatherBinding,
        val onWeatherDataClick: (ForecastDayData) -> Unit,
    ) : BindableViewHolder<ForecastDayData>(binding.root) {
        override fun bind(item: ForecastDayData) {
            binding.root.setSafeOnClickListener { onWeatherDataClick.invoke(item) }
            val simpleDate = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
            binding.dayTv.text = simpleDate.format(item.date)
            binding.imageView2.setImageFromUrl(item.day.condition.icon)
            binding.minTv.text = item.day.mintempC.toString()
            binding.maxTv.text = item.day.maxtempC.toString()
        }
    }

    class WeatherListShimmerViewHolder(
        private val binding: ItemWeatherShimmerBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    data class Params(
        val onWeatherDataClick: (ForecastDayData) -> Unit,
    )

    override fun createLoadingStateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        WeatherListShimmerViewHolder(
            binding = ItemWeatherShimmerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun createDataStateViewHolder(parent: ViewGroup): WeatherListViewHolder = WeatherListViewHolder(
        binding = ItemWeatherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        onWeatherDataClick = params.onWeatherDataClick
    )
}
