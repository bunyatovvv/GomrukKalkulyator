package com.example.gomrukkolkulatoru.presentation.ui.favorites

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.common.base.BaseFragment
import com.example.gomrukkolkulatoru.common.util.Status
import com.example.gomrukkolkulatoru.common.util.gone
import com.example.gomrukkolkulatoru.common.util.visible
import com.example.gomrukkolkulatoru.databinding.FragmentFavoritesBinding
import com.example.gomrukkolkulatoru.presentation.ui.favorites.adapter.FavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FragmentFavoritesBinding>(FragmentFavoritesBinding::inflate) {

    private val favAdapter by lazy { FavoritesAdapter() }
    private lateinit var viewModel: FavoritesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[FavoritesViewModel::class.java]
        observeData()
        setupRecview()

        favAdapter.setOnItemClickListener { favorites ->
            val alertDialog = AlertDialog.Builder(requireContext()).setTitle(
                "Seçiləni sil"
            ).setPositiveButton("Bəli", DialogInterface.OnClickListener { dialog, which ->
                viewModel.deleteFavorites(favorites)
            }).setNegativeButton("Xeyr", DialogInterface.OnClickListener { dialog, which ->
            }).setMessage(R.string.eminsiniz)
            alertDialog.show()
        }
    }

    private fun observeData() {
        viewModel.apply {
            favorites.observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            favAdapter.favorites = it
                            getAllFavorites()
                            if (it.isNotEmpty()) {
                                binding.imageLayout.gone()
                            } else {
                                binding.imageLayout.visible()
                            }
                        }
                    }

                    Status.ERROR -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setupRecview() {
        binding.favoriteRecView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favAdapter
        }
    }
}