package com.example.gomrukkolkulatoru.data.repo

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.common.util.Status
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO
import com.example.gomrukkolkulatoru.data.source.RoomSource
import com.example.gomrukkolkulatoru.domain.repo.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(
    private val roomSource: RoomSource
) : RoomRepository {
    override suspend fun getAllFavorites(): Flow<Resource<List<FavoritesDTO>>> = flow {
        Resource.loading(null)
        val response = roomSource.getAllFavorites()

        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error get all favorites", null))
            }

            else -> {}
        }
    }

    override suspend fun addFavorites(favoritesDTO: FavoritesDTO) {
        roomSource.addFavorites(favoritesDTO)
    }

    override suspend fun deleteFavorites(favoritesDTO: FavoritesDTO) {
        roomSource.deleteFavorites(favoritesDTO)
    }
}