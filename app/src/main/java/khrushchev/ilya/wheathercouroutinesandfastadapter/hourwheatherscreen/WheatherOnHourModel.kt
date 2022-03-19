package khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WheatherOnHourModel(
    val temp: Float,
    val date: String,
    val wheatherIconUrl: String
) : Parcelable