package khrushchev.ilya.wheatherapp.models

import com.google.gson.annotations.SerializedName

data class ListWheatherModel(
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("pop")
    val pop: Float,
    @SerializedName("dt_txt")
    val dt_txt: String,
    @SerializedName("main")
    val mainWeather: MainWheather,
    @SerializedName("weather")
    val weather: List<WheatherInfo>,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("sys")
    val sys: Sys
)
