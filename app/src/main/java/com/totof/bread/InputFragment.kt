package com.totof.bread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.totof.bread.data.Input
import com.totof.bread.databinding.FragmentInputBinding
import com.totof.bread.process.InputInterface
import com.totof.bread.viewmodel.BreadViewModel

class InputFragment : Fragment(), InputInterface {
    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BreadViewModel by activityViewModels()
    private lateinit var input: Input

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        input = arguments?.getSerializable(ARG_INPUT) as? Input ?: viewModel.input.value!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nbPainsSimple.setText(input.nbPainSimple.toString())
        binding.nbPainsGraine.setText(input.nbPainGraine.toString())
        binding.nbDemiPainsSimple.setText(input.nbDemiPainSimple.toString())
        binding.nbDemiPainsGraine.setText(input.nbDemiPainGraine.toString())
        binding.nbPainsSimpleMoule.setText(input.nbPainSimpleMoule.toString())
        binding.nbPainsGraineMoule.setText(input.nbPainGraineMoule.toString())
        binding.nbDemiPainsSimpleMoule.setText(input.nbDemiPainSimpleMoule.toString())
        binding.nbDemiPainsGraineMoule.setText(input.nbDemiPainGraineMoule.toString())
        binding.levainActuel.setText(input.levainActuel.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is MainFragmentCallBack) {
            throw RuntimeException("$context must implement MainFragmentCallBack")
        }
    }

    override fun update() {
        _binding?.let {
            input.setNbPainSimple(it.nbPainsSimple.text.toString(), input.nbPainSimple)
            input.setNbPainGraine(it.nbPainsGraine.text.toString(), input.nbPainGraine)
            input.setNbDemiPainSimple(it.nbDemiPainsSimple.text.toString(), input.nbDemiPainSimple)
            input.setNbDemiPainGraine(it.nbDemiPainsGraine.text.toString(), input.nbDemiPainGraine)
            input.setNbPainSimpleMoule(it.nbPainsSimpleMoule.text.toString(), input.nbPainSimpleMoule)
            input.setNbPainGraineMoule(it.nbPainsGraineMoule.text.toString(), input.nbPainGraineMoule)
            input.setNbDemiPainSimpleMoule(it.nbDemiPainsSimpleMoule.text.toString(), input.nbDemiPainSimpleMoule)
            input.setNbDemiPainGraineMoule(it.nbDemiPainsGraineMoule.text.toString(), input.nbDemiPainGraineMoule)
            input.setLevainActuel(it.levainActuel.text.toString(), input.levainActuel)
            viewModel.notifyDataChanged()
        }
    }

    companion object {
        private const val ARG_INPUT = "input"
        fun newInstance(input: Input?): InputFragment {
            val fragment = InputFragment()
            val args = Bundle()
            args.putSerializable(ARG_INPUT, input)
            fragment.arguments = args
            return fragment
        }
    }
}
