package com.totof.bread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.totof.bread.data.Input
import com.totof.bread.databinding.FragmentRecipeBinding
import com.totof.bread.process.InputInterface
import com.totof.bread.viewmodel.BreadViewModel

class RecipeFragment : Fragment(), InputInterface {
    private var _binding: FragmentRecipeBinding? = null
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
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.poidsPain.setHint(input.poidsPain.toString())
        binding.pourcentageLevain.setHint(input.pourcentageLevain.toString())
        binding.pourcentageEau.setHint(input.pourcentageEau.toString())
        binding.pourcentageEauLevain.setHint(input.pourcentageEauLevain.toString())
        binding.pourcentageSel.setHint(input.pourcentageSel.toString())
        binding.levainAGarder.setHint(input.levainAGarder.toString())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is MainFragmentCallBack) {
            throw RuntimeException("$context must implement MainFragmentCallBack")
        }
    }

    override fun update() {
        _binding?.let {
            input.setPoidsPain(it.poidsPain.text.toString(), input.poidsPain)
            input.setPourcentageLevain(it.pourcentageLevain.text.toString(), input.pourcentageLevain)
            input.setPourcentageEau(it.pourcentageEau.text.toString(), input.pourcentageEau)
            input.setPourcentageEauLevain(it.pourcentageEauLevain.text.toString(), input.pourcentageEauLevain)
            input.setPourcentageSel(it.pourcentageSel.text.toString(), input.pourcentageSel)
            input.setLevainAGarder(it.levainAGarder.text.toString(), input.levainAGarder)
            viewModel.notifyDataChanged()
        }
    }

    companion object {
        private const val ARG_INPUT = "input"
        fun newInstance(input: Input?): RecipeFragment {
            val fragment = RecipeFragment()
            val args = Bundle()
            args.putSerializable(ARG_INPUT, input)
            fragment.arguments = args
            return fragment
        }
    }
}
