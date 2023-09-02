package com.example.gomrukkolkulatoru.domain.use_case

import com.example.gomrukkolkulatoru.domain.repo.RoomRepository
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
    private val repo : RoomRepository
) {
    suspend operator fun invoke() = repo.getAllFavorites()
}