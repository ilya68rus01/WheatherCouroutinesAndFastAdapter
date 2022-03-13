package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import khrushchev.ilya.wheathercouroutinesandfastadapter.filterByArrangeDate
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.WheatherOnHourModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.DayliWheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.EMPTY_MODEL
import khrushchev.ilya.wheathercouroutinesandfastadapter.mapToDayliWheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.repository.Repository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayliWheatherViewModel
@Inject constructor(
    repository: Repository
) : ViewModel() {

    private val _weatherFlow: MutableStateFlow<List<DayliWheatherModel>> =
        MutableStateFlow(emptyList())
    val weatherFlow: StateFlow<List<DayliWheatherModel>> get() = _weatherFlow.asStateFlow()

    private val _currentWeather: MutableStateFlow<DayliWheatherModel> =
        MutableStateFlow(EMPTY_MODEL)
    val currentWeather: StateFlow<DayliWheatherModel> get() = _currentWeather.asStateFlow()

    private val _currentCity: MutableStateFlow<String> =
        MutableStateFlow("")
    val currentCity: StateFlow<String> get() = _currentCity.asStateFlow()


    init {
        viewModelScope.launch {
            repository.getWheatherModel()
                .map {
                    _currentCity.tryEmit("${it.city.name}, ${it.city.country}")
                    it.mapToDayliWheatherModel()
                        .filterByArrangeDate()
                }
                .collect {
                    _currentWeather.tryEmit(it.first())
                    _weatherFlow.tryEmit(it)
                }
        }
    }
}