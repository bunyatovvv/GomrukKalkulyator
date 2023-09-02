package com.example.gomrukkolkulatoru.presentation.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.domain.use_case.DeleteFavoritesUseCase
import com.example.gomrukkolkulatoru.domain.use_case.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoritesUseCase: DeleteFavoritesUseCase
) : ViewModel() {

    private val _favorites = MutableLiveData<Resource<List<FavoritesDTO>>>()
    val favorites : LiveData<Resource<List<FavoritesDTO>>>
        get() = _favorites

    init {
        getAllFavorites()
    }

    fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoritesUseCase.invoke().collectLatest {
                _favorites.postValue(it)
            }
        }
    }

    fun deleteFavorites(favoritesDTO: FavoritesDTO){
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoritesUseCase(favoritesDTO)
        }
    }
}