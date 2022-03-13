package khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import khrushchev.ilya.wheathercouroutinesandfastadapter.R

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
}