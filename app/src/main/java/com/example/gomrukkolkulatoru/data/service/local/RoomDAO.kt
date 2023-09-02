package com.example.gomrukkolkulatoru.data.service.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO

@Dao
interface RoomDAO {
    @Query("SELECT * FROM favorites_table")
    suspend fun getAllFavorites(): List<FavoritesDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(favoritesDTO: FavoritesDTO)

    @Delete
    suspend fun deleteFavorites(favoritesDTO: FavoritesDTO)
}