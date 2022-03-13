package khrushchev.ilya.wheatherapp.models

import com.google.gson.annotations.SerializedName

data class Coordination(
    @SerializedName("lat")
    val lat: Int,
    @SerializedName("lon")
    val lon: Int
)
