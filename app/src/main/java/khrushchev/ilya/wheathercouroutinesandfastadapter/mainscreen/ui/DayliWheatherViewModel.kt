package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import khrushchev.ilya.wheatherapp.models.ListWheatherModel
import khrushchev.ilya.wheatherapp.models.WheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.filterByArrangeDate
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.WheatherOnHourModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.DayliWheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.EMPTY_MODEL
import khrushchev.ilya.wheathercouroutinesandfastadapter.mapToDayliWheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mapToHourModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DayliWheatherViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val weather = mutableListOf<ListWheatherModel>()

    suspend fun  weatherFlow(): StateFlow<List<DayliWheatherModel>> =
        repository.getWheatherModel()
        .map {
            if (weather.isNotEmpty()){
                weather.clear()
            }
            weather.addAll(it.list)
            _currentCity.tryEmit("${it.city.name}, ${it.city.country}")
            it.mapToDayliWheatherModel()
                .filterByArrangeDate()
                .apply {
                    _currentWeather.tryEmit(this.first())
                }
        }.stateIn(viewModelScope)

    private val _currentWeather: MutableStateFlow<DayliWheatherModel> =
        MutableStateFlow(EMPTY_MODEL)
    val currentWeather: StateFlow<DayliWheatherModel> get() = _currentWeather.asStateFlow()

    private val _currentCity: MutableStateFlow<String> =
        MutableStateFlow("")
    val currentCity: StateFlow<String> get() = _currentCity.asStateFlow()

    private val _openWeatherByHour: MutableSharedFlow<List<WheatherOnHourModel>> =
        MutableSharedFlow(0,1, BufferOverflow.DROP_LATEST)
    val openWeatherByHour get() = _openWeatherByHour.asSharedFlow()

    fun onItemClick(model: DayliWheatherModel) {
        val hourModels = weather.filter {
            val formatterToDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val formatterFromDateToString = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val date = formatterToDate.parse(it.dt_txt)
            val formattedDate = formatterFromDateToString.format(date)
            model.formattedDate.contains(formattedDate)
        }.mapToHourModel()
        _openWeatherByHour.tryEmit(hourModels)
    }
}