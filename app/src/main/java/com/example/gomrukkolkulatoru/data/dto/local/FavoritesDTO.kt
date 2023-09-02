package com.example.gomrukkolkulatoru.data.dto.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class FavoritesDTO(
    @ColumnInfo("title")
    val title : String,
    @ColumnInfo("brand")
    val brand : String,
    @ColumnInfo("model")
    val model : String,
    @ColumnInfo("year")
    val year : String,
    @ColumnInfo("engine")
    val engine : String,
    @ColumnInfo("engine_type")
    val engineType : String,
    @ColumnInfo("price")
    val price : String,
    @ColumnInfo("customs_price")
    val customsPrice : Double
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}