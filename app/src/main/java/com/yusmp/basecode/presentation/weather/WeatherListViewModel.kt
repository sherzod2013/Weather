package com.yusmp.basecode.presentation.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.ResolvableApiException
import com.yusmp.basecode.app.lifecycle.SingleLiveEvent
import com.yusmp.basecode.base.recyclerAdapter.RecyclerDataState
import com.yusmp.basecode.presentation.common.BaseViewModel
import com.yusmp.basecode.presentation.weather.dialog.FilterType
import com.yusmp.domain.location.LocationInteractor
import com.yusmp.domain.weather.GetCurrentWeatherDbUseCase
import com.yusmp.domain.weather.GetCurrentWeatherUseCase
import com.yusmp.domain.weather.model.ForecastDayData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getCurrentWeatherDbUseCase: GetCurrentWeatherDbUseCase,
    private val locationInteractor: LocationInteractor
) : BaseViewModel<WeatherListState, WeatherListEvent>(WeatherListState()) {

    private var allData: List<ForecastDayData> = listOf()

    init {
        getCurrentWeather()
        observeWeather()
    }

    private val _openGSMSettings = SingleLiveEvent<ResolvableApiException>()
    val openGSMSettings: LiveData<ResolvableApiException> = _openGSMSettings

    private fun getCurrentWeather() {
        locationInteractor.lastLocation
            .filterNotNull()
            .onEach { result ->
                result.onFailure { exception ->
                    when (exception) {
                        is ResolvableApiException -> {
                            _openGSMSettings.postValue(exception)
                        }
                    }
                }.onSuccess { location ->
                    locationInteractor.stopLocationUpdates()
                    getCurrentWeatherUseCase("${location.latitude},${location.longitude}").collect { result ->
                        result.onFailure {
                            handleError(it)
                        }
                    }
                }
            }.launchIn(viewModelScope)

    }

    private fun observeWeather() {
        viewModelScope.launch {
            getCurrentWeatherDbUseCase(Unit).collect { weather ->
                allData = weather?.forecast?.forecastday ?: listOf()
                updateUiState {
                    copy(isLoading = false,
                        weatherData = weather,
                        forecastDayData = weather?.forecast?.forecastday?.take(this.filterType.cout)?.map {
                            RecyclerDataState.Data(
                                it.toString(), it
                            )
                        })
                }
            }
        }
    }

    fun startLocationUpdates() {
        locationInteractor.startLocationUpdates()
    }

    fun onFilterTypeSelected(filterType: FilterType) {
        updateUiState {
            copy(
                forecastDayData = allData.take(filterType.cout).map {
                    RecyclerDataState.Data(
                        it.toString(), it
                    )
                },
                filterType = filterType
            )
        }
    }
}