package com.example.gomrukkolkulatoru.presentation.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.databinding.FragmentSaveBinding
import com.example.gomrukkolkulatoru.presentation.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveDialogFragment : DialogFragment(R.layout.fragment_save) {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var saveViewModel: SaveViewModel
    private var _binding: FragmentSaveBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        saveViewModel = ViewModelProvider(requireActivity())[SaveViewModel::class.java]
        observeData()
        setup()
    }

    private fun setup() {
        binding.apply {
            saveButton.setOnClickListener {
                val favorite = FavoritesDTO(
                    binding.titleEditText.text.toString(),
                    binding.brandEditText.text.toString(),
                    binding.modelEditText.text.toString(),
                    binding.issueDateEditText.text.toString(),
                    binding.engineSizeEditText.text.toString(),
                    binding.engineTypeEditText.text.toString(),
                    binding.priceEditText.text.toString(),
                    saveViewModel.totalCustoms.value!!
                )
                lifecycleScope.launch {
                    saveViewModel.addToFavorites(favorite)
                }
                dismiss()
                findNavController().navigate(R.id.action_resultFragment_to_favoritesFragment)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog

        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window!!.setLayout(width, height)
        }
    }

    private fun observeData() {
        with(homeViewModel) {
            calculateModel.observe(viewLifecycleOwner, Observer {
                binding.engineTypeEditText.setText(it.autoType)
                binding.engineSizeEditText.setText(it.engine.toString())
                binding.issueDateEditText.setText(it.issueDate)
                binding.priceEditText.setText("${it.price}$")
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}