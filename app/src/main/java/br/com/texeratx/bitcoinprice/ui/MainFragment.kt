package br.com.texeratx.bitcoinprice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.texeratx.bitcoinprice.R
import br.com.texeratx.bitcoinprice.databinding.FragmentMainBinding
import br.com.texeratx.bitcoinprice.utils.DateUtils
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke


class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(requireActivity().application)
    }
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        viewModel.values.observe(viewLifecycleOwner) {
            val recentValue = it.maxByOrNull { value -> value.time }!!
            binding.txvCurrentValue.text = recentValue.formattedValue
            binding.txvCurrentValueUpdateTime.text = DateUtils.getCompleteDate(recentValue.time)
        }

        viewModel.chartValues.observe(viewLifecycleOwner) {
            setChart(it)
        }

        return binding.root
    }

    private fun setChart(values: List<ValueDataEntry>) {
        val cartesian = AnyChart.line().apply {
            animation(true)

            padding(10.0, 20.0, 5.0, 20.0)

            crosshair().enabled(true)
            crosshair()
                .yLabel(true)
                .yStroke(null as Stroke?, null, null, null as String?, null as String?)

            tooltip().positionMode(TooltipPositionMode.POINT)

            title(resources.getString(R.string.bitcoin_values))

            yAxis(0).title(resources.getString(R.string.value))
            xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

            line(values).apply {
                name("Bitcoin")
                hovered().markers().enabled(true)
                hovered().markers()
                    .type(MarkerType.CIRCLE)
                    .size(4.0)
                tooltip()
                    .position("right")
                    .anchor(Anchor.LEFT_CENTER)
                    .offsetX(5.0)
                    .offsetY(5.0)
            }
        }

        binding.acvBitcoin.setChart(cartesian)
    }
}