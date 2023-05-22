package com.yusmp.basecode.presentation.currentWeather.adapter

import com.yusmp.basecode.base.recyclerAdapter.BindableViewHolder
import com.yusmp.basecode.databinding.ItemWeatherByHourBinding
import com.yusmp.basecode.presentation.currentWeather.model.HourDataUi
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherByHourViewHolderArticleViewHolder(
    private val binding: ItemWeatherByHourBinding
) : BindableViewHolder<HourDataUi>(binding.root) {

    override fun bind(data: HourDataUi) {
        with(binding) {
            val simpleDate = SimpleDateFormat("EEEE", Locale.getDefault())
            val simpleDate2 = SimpleDateFormat("HH:mm", Locale.getDefault())
            data.time?.let {
                binding.dayTv.text = simpleDate.format(it)
                dateTv.text = (simpleDate2.format(it))
            }
            condition.text = data.condition.text
            textView4.text = data.tempC.toString()
        }
    }
}