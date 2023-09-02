package com.example.gomrukkolkulatoru.presentation.ui.result

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.common.base.BaseFragment
import com.example.gomrukkolkulatoru.common.util.Status
import com.example.gomrukkolkulatoru.common.util.format
import com.example.gomrukkolkulatoru.common.util.gone
import com.example.gomrukkolkulatoru.common.util.visible
import com.example.gomrukkolkulatoru.databinding.FragmentResultBinding
import com.example.gomrukkolkulatoru.presentation.ui.home.HomeViewModel
import com.example.gomrukkolkulatoru.presentation.ui.result.adapter.ResultAdapter
import com.example.gomrukkolkulatoru.presentation.ui.save.SaveDialogFragment
import com.example.gomrukkolkulatoru.presentation.ui.save.SaveViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private val resultAdapter by lazy { ResultAdapter() }
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var saveViewModel: SaveViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.gone()
        binding.resultLayout.gone()
        binding.buttonLayout.gone()
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }

        saveViewModel = ViewModelProvider(requireActivity())[SaveViewModel::class.java]
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        observeData()
        setupRecview()
        onBackPressed()

        binding.saveButton.setOnClickListener {
            SaveDialogFragment().show(childFragmentManager, "save")
        }
    }

    private fun observeData() {
        with(homeViewModel) {
            calculateData.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.SUCCESS -> {
                        it.data?.let {
                            binding.progressBar.gone()
                            binding.resultLayout.visible()
                            binding.buttonLayout.visible()
                            resultAdapter.differ.submitList(it)
                            val cemi = resultAdapter.differ.currentList.sumByDouble {
                                it.value
                            }
                            saveViewModel.totalCustoms.value = cemi

                            binding.total.text = "${cemi.format(2)} AZN"
                        }
                    }

                    Status.LOADING -> {
                        binding.progressBar.visible()
                    }
                }
            })
        }
    }

    private fun onBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setupRecview() {
        binding.recView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = resultAdapter
        }
    }
}