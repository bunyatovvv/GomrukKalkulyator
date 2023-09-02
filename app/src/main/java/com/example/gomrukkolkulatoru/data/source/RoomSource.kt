package com.example.gomrukkolkulatoru.data.source

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO

interface RoomSource {
    suspend fun getAllFavorites(): Resource<List<FavoritesDTO>>
    suspend fun addFavorites(favoritesDTO: FavoritesDTO)
    suspend fun deleteFavorites(favoritesDTO: FavoritesDTO)
}