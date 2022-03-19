package khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.squareup.picasso.Picasso
import khrushchev.ilya.wheathercouroutinesandfastadapter.R
import khrushchev.ilya.wheathercouroutinesandfastadapter.databinding.FragmentDayliWheatherBinding
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.ui.HourWeatherFragment
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di.MainScreenComponent.Builder.Companion.build
import khrushchev.ilya.wheathercouroutinesandfastadapter.mainscreen.di.ViewModelProviderFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class DayliWhetherFragment : Fragment() {

    private var _binding: FragmentDayliWheatherBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: DayliWheatherViewModel

    private val dayliWeatherAdapter = ItemAdapter<DayliWeatherItem>()
    private val adapter = FastAdapter.with(dayliWeatherAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayliWheatherBinding.inflate(inflater)
        viewModel = ViewModelProvider(
            requireActivity(),
            providerFactory
        )[DayliWheatherViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewModel.weatherFlow().collect {
                it.map { DayliWeatherItem(it, viewModel::onItemClick) }.also {
                    val diff = FastAdapterDiffUtil.calculateDiff(dayliWeatherAdapter, it)
                    FastAdapterDiffUtil[dayliWeatherAdapter] = diff
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.currentWeather.collect {
                Picasso.get()
                    .load("https://openweathermap.org/img/wn/${it.wheatherIconUrl}@4x.png")
                    .into(binding.header.weatherIcon)
                binding.header.description.text = it.description
                binding.header.temp.text = context?.getString(R.string.gradus, it.temp)
                binding.header.pressure.text = context?.getString(R.string.mm_rt_st, it.pressure)
                binding.header.wind.text = context?.getString(R.string.meter_in_second, it.wind)
                binding.header.visibility.text = context?.getString(R.string.meter, it.visibility)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.currentCity.collect {
                binding.header.cityName.text = it
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.openWeatherByHour.collect {
                val fragment = HourWeatherFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(this::class.simpleName)
                    .setReorderingAllowed(true)
                    .commit()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}