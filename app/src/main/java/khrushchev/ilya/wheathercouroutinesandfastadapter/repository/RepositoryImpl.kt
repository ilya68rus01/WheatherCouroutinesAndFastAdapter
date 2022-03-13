package khrushchev.ilya.wheathercouroutinesandfastadapter.repository

import khrushchev.ilya.wheatherapp.models.WheatherModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val wheatherApi: WheatherApi
): Repository {

    override fun getWheatherModel(): Flow<WheatherModel> =
        flow {
            val data = wheatherApi.getWheatherByCoord()
            emit(data)
        }
}