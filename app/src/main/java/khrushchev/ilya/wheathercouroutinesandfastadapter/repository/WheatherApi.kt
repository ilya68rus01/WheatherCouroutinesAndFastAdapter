package khrushchev.ilya.wheathercouroutinesandfastadapter.repository

import khrushchev.ilya.wheatherapp.models.WheatherModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WheatherApi {

    @GET("forecast")
    suspend fun getWheatherByCoord(
        @Query("lat")
        lat: Float = 52f,
        @Query("lon")
        lon: Float = 41f,
        @Query("lang")
        lang: String = "ru"
    ): WheatherModel
}