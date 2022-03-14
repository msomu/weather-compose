package dev.msomu.weathercompose.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.msomu.weathercompose.data.local.WeatherDisplayData
import dev.msomu.weathercompose.repository.WeatherRepository
import dev.msomu.weathercompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    private val _viewState : MutableStateFlow<Resource<List<WeatherDisplayData>>> = MutableStateFlow(Resource.Loading(null))
    val viewState: StateFlow<Resource<List<WeatherDisplayData>>> = _viewState

    private val _detailViewState : MutableStateFlow<Resource<WeatherDisplayData>> = MutableStateFlow(Resource.Loading(null))
    val detailViewState: StateFlow<Resource<WeatherDisplayData>> = _detailViewState

    fun fetchData(cityName : String){
        repository.getWeather(cityName = cityName).onEach {
            _viewState.value = it
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun getData(cityName: String, dt : Int){
        Log.d("MainViewModel","Getting data for $cityName and $dt")
        repository.getWeather(cityName = cityName, dt = dt).onEach {
            _detailViewState.value = it
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}