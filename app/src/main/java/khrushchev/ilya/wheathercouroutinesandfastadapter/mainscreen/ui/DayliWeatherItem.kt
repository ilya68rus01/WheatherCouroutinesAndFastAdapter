package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.squareup.picasso.Picasso
import khrushchev.ilya.wheathercouroutinesandfastadapter.R
import khrushchev.ilya.wheathercouroutinesandfastadapter.databinding.DayliWeatherItemBinding
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.DayliWheatherModel

class DayliWeatherItem(
    private val dayliModel: DayliWheatherModel
) : AbstractBindingItem<DayliWeatherItemBinding>() {

    override var identifier: Long = dayliModel.hashCode().toLong()

    override val type: Int
        get() = R.id.item

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): DayliWeatherItemBinding = DayliWeatherItemBinding.inflate(inflater, parent, false)

    override fun bindView(binding: DayliWeatherItemBinding, payloads: List<Any>) {
        super.bindView(binding, payloads)
        binding.date.text = dayliModel.formattedDate
        binding.description.text = dayliModel.description
        binding.pressure.text =
            binding.root.context.getString(R.string.mm_rt_st, dayliModel.pressure)
        binding.temp.text = binding.root.context.getString(R.string.gradus, dayliModel.temp)
        binding.wind.text =
            binding.root.context.getString(R.string.meter_in_second, dayliModel.wind)
        Picasso.get()
            .load("https://openweathermap.org/img/wn/${dayliModel.wheatherIconUrl}@2x.png")
            .into(binding.img)
    }
}