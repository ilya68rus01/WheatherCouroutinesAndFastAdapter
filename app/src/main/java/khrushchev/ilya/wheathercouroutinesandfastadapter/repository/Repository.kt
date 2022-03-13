package khrushchev.ilya.wheathercouroutinesandfastadapter.repository

import khrushchev.ilya.wheatherapp.models.WheatherModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getWheatherModel(): Flow<WheatherModel>
}