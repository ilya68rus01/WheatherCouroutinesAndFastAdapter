package khrushchev.ilya.wheatherapp.models

import com.google.gson.annotations.SerializedName

data class WheatherModel(
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("message")
    val message: Int,
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<ListWheatherModel>,
    @SerializedName("city")
    val city: SityWheatherModel
)
