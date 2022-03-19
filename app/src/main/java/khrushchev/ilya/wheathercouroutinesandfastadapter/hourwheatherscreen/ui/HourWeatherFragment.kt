package khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import khrushchev.ilya.wheathercouroutinesandfastadapter.R
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.WheatherOnHourModel

class HourWeatherFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hour_weather, container, false)
    }


    companion object {
        const val KEY = "hourData"

        fun createNewInstance(hourWeather: List<WheatherOnHourModel>): HourWeatherFragment {
            val data = Bundle().apply {
                putParcelableArrayList(KEY, ArrayList(hourWeather))
            }
            return HourWeatherFragment().apply {
                arguments = data
            }
        }
    }
}