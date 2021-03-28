package br.com.texeratx.bitcoinprice.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.texeratx.bitcoinprice.databinding.FragmentMainBinding

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
            Log.d("MainFragment", it.joinToString())
        }

        return binding.root
    }
}