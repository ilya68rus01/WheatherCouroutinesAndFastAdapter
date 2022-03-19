package khrushchev.ilya.wheathercouroutinesandfastadapter

import khrushchev.ilya.wheatherapp.models.WheatherModel
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.DayliWheatherModel
import java.text.SimpleDateFormat
import java.util.*

fun WheatherModel.mapToDayliWheatherModel(): List<DayliWheatherModel> =
    list.map {
        val formatterToDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val formatterFromDateToString = SimpleDateFormat("dd.MM.yyyy EEEE", Locale.getDefault())
        val date = formatterToDate.parse(it.dt_txt)
        val formattedDate = formatterFromDateToString.format(date)
        val description = it.weather.first().description
        val wind = it.wind.speed.toInt().toString()
        val pressure = (it.mainWeather.pressure * 0.75f).toInt().toString()
        val temp = (it.mainWeather.temp - 273f).toInt()
        val icon = it.weather.first().icon
        DayliWheatherModel(formattedDate, temp.toString(), icon, pressure, wind, description, it.visibility.toString())
    }

fun List<DayliWheatherModel>.filterByArrangeDate(): List<DayliWheatherModel> {
    var currentDate: String? = null
    return filterIndexed { index, model ->
        when {
            index == 0 -> {
                currentDate = model.formattedDate
                true
            }
            currentDate != model.formattedDate -> {
                currentDate = model.formattedDate
                true
            }
            else -> false
        }
    }
}