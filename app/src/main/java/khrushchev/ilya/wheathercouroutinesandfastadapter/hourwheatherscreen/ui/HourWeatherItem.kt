package khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.squareup.picasso.Picasso
import khrushchev.ilya.wheathercouroutinesandfastadapter.R
import khrushchev.ilya.wheathercouroutinesandfastadapter.databinding.HourWeatherItemBinding
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.WheatherOnHourModel

class HourWeatherItem(
    private val item: WheatherOnHourModel
) : AbstractBindingItem<HourWeatherItemBinding>() {

    override var identifier = item.hashCode().toLong()

    override val type: Int = R.id.hour_weather_item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) = HourWeatherItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: HourWeatherItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.date.text = item.date
        binding.temp.text = binding.root.context.getString(R.string.gradus, item.temp)
        Picasso.get()
            .load("https://openweathermap.org/img/wn/${item.wheatherIconUrl}@2x.png")
            .into(binding.img)
    }
}