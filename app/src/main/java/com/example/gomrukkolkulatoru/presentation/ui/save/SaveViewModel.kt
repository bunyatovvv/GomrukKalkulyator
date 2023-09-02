package com.example.gomrukkolkulatoru.presentation.ui.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.domain.use_case.AddFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(
    private val addFavoritesUseCase: AddFavoritesUseCase
) : ViewModel() {

    val totalCustoms: MutableLiveData<Double> = MutableLiveData()

    suspend fun addToFavorites(favoritesDTO: FavoritesDTO) = viewModelScope.launch {
        addFavoritesUseCase(favoritesDTO)
    }
}