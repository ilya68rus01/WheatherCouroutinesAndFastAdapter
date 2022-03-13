package khrushchev.ilya.wheatherapp.models

import com.google.gson.annotations.SerializedName

data class SityWheatherModel(
    @SerializedName("valid")
    val valid: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val coord: Coordination,
    @SerializedName("country")
    val country: String,
    @SerializedName("population")
    val population: Int,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)
