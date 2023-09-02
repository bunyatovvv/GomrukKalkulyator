package com.example.gomrukkolkulatoru.domain.repo

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import kotlinx.coroutines.flow.Flow

interface RoomRepository {
    suspend fun getAllFavorites(): Flow<Resource<List<FavoritesDTO>>>
    suspend fun addFavorites(favoritesDTO: FavoritesDTO)
    suspend fun deleteFavorites(favoritesDTO: FavoritesDTO)
}