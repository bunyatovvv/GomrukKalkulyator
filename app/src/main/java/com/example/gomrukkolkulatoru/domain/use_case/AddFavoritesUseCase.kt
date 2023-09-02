package com.example.gomrukkolkulatoru.domain.use_case

import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.domain.repo.RoomRepository
import javax.inject.Inject

class AddFavoritesUseCase @Inject constructor(
    private val repo : RoomRepository
) {

    suspend operator fun invoke(favoritesDTO : FavoritesDTO) = repo.addFavorites(favoritesDTO)
}