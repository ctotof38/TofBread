package com.totof.bread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.totof.bread.data.Input
import com.totof.bread.databinding.FragmentQuantityBinding
import com.totof.bread.process.Calculator
import com.totof.bread.process.InputInterface
import com.totof.bread.viewmodel.BreadViewModel
import java.text.DecimalFormat

class QuantityFragment : Fragment(), InputInterface {
    private var _binding: FragmentQuantityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BreadViewModel by activityViewModels()
    private lateinit var input: Input

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        input = (arguments?.let { BundleCompat.getSerializable(it, ARG_INPUT, Input::class.java) }) ?: viewModel.input.value!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentQuantityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.input.observe(viewLifecycleOwner) {
            update()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is MainFragmentCallBack) {
            throw RuntimeException("$context must implement MainFragmentCallBack")
        }
    }

    override fun update() {
        _binding?.let {
            try {
                val output = Calculator.calculate(input)
                it.makeTitle.text = makeTitle(output.nbPain, output.patePourUnPain, output.hydratation)
                it.resuPateTotale.text = getValue(output.pateTotale)
                it.resuFarineRecette.text = getValue(output.farineBleTotale)
                it.resuEauRecette.text = getValue(output.eauBleTotale)
                it.resuSel.text = getValue(output.selTotal)
                it.resuGraine.text = getValue(output.graine)
                it.resuLevain.text = getValue(output.levainDePate)
                it.resuFarineLevain.text = getValue(output.farinePourLevain)
                it.resuEauLevain.text = getValue(output.eauPourLevain)
                it.resuPateConserver.text = getValue(output.pateAGarder)
                it.resuPateADecouper.text = getValue(output.pateTotaleSimple + output.pateTotaleGraine)
                it.resuPateTotaleSimple.text = getValue(output.pateTotaleSimple)
                it.resuPateTotaleGraine.text = getValue(output.pateTotaleGraine)
                it.resuPateSimple.text = getValue(output.pateSimple)
                it.resuPateGraine.text = getValue(output.pateGraine)
            } catch (e: Exception) {
                // handle exception
            }
        }
    }

    private fun getValue(data: Double) = Calculator.truncateDoubleToString(data)

    private fun makeTitle(nbBread: Double, pastaWeight: Double, hydration: Double): String {
        return "${doubleToString(nbBread, 1)} pâtes de ${doubleToString(pastaWeight, 0)} g à ${doubleToString(hydration, 0)} % d'eau"
    }

    companion object {
        private const val ARG_INPUT = "input"
        fun newInstance(input: Input?): QuantityFragment {
            val fragment = QuantityFragment()
            val args = Bundle()
            args.putSerializable(ARG_INPUT, input)
            fragment.arguments = args
            return fragment
        }

        fun doubleToString(value: Double, precision: Int): String {
            val pattern = if (precision == 1) "######.#" else "######"
            return DecimalFormat(pattern).format(value)
        }
    }
}
