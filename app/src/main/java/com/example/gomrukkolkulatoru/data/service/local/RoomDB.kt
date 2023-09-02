package com.example.gomrukkolkulatoru.data.service.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO

@Database(entities = [FavoritesDTO::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun getDao() : RoomDAO
}