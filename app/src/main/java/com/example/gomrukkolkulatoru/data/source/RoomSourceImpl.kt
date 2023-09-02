package com.example.gomrukkolkulatoru.data.source

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.data.service.local.RoomDAO
import java.lang.Exception
import javax.inject.Inject

class RoomSourceImpl @Inject constructor(
    private val dao: RoomDAO
) : RoomSource {
    override suspend fun getAllFavorites(): Resource<List<FavoritesDTO>> {
        return try {
            val data = dao.getAllFavorites()
            Resource.success(data)
        } catch (e: Exception) {
            Resource.error(e.localizedMessage, null)
        }
    }

    override suspend fun addFavorites(favoritesDTO: FavoritesDTO) {
        dao.addFavorites(favoritesDTO)
    }

    override suspend fun deleteFavorites(favoritesDTO: FavoritesDTO) {
        dao.deleteFavorites(favoritesDTO)
    }
}