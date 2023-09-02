package com.example.gomrukkolkulatoru.domain.use_case

import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.domain.repo.RoomRepository
import javax.inject.Inject

class DeleteFavoritesUseCase @Inject constructor(
    private val repo : RoomRepository
) {
    suspend operator fun invoke(favoritesDTO: FavoritesDTO) = repo.deleteFavorites(favoritesDTO)
}