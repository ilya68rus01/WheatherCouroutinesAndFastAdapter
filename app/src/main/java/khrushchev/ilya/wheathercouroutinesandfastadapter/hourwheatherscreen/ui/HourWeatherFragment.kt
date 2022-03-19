package khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import khrushchev.ilya.wheathercouroutinesandfastadapter.databinding.FragmentHourWeatherBinding
import khrushchev.ilya.wheathercouroutinesandfastadapter.hourwheatherscreen.WheatherOnHourModel

class HourWeatherFragment : Fragment() {

    private var _binding: FragmentHourWeatherBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding is null")

    private val itemAdapter = ItemAdapter<HourWeatherItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHourWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = fastAdapter
        val data = arguments?.getParcelableArrayList<WheatherOnHourModel>(KEY)?.toList()
            ?.map {
                HourWeatherItem(it)
            }
        if (data != null) {
            val diff = FastAdapterDiffUtil.calculateDiff(itemAdapter, data)
            FastAdapterDiffUtil[itemAdapter] = diff
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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