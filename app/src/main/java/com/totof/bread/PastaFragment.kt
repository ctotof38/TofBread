package com.totof.bread

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.totof.bread.data.Input
import com.totof.bread.databinding.FragmentPastaBinding
import com.totof.bread.viewmodel.BreadViewModel

class PastaFragment : Fragment() {
    private var _binding: FragmentPastaBinding? = null
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
        _binding = FragmentPastaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context !is MainFragmentCallBack) {
            throw RuntimeException("$context must implement MainFragmentCallBack")
        }
    }

    companion object {
        private const val ARG_INPUT = "input"
        fun newInstance(input: Input?): PastaFragment {
            val fragment = PastaFragment()
            val args = Bundle()
            args.putSerializable(ARG_INPUT, input)
            fragment.arguments = args
            return fragment
        }
    }
}
