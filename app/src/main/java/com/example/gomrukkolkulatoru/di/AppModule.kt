package com.example.gomrukkolkulatoru.di

import android.content.Context
import androidx.room.Room
import com.example.gomrukkolkulatoru.common.util.BASE_URL
import com.example.gomrukkolkulatoru.data.repo.ApiRepositoryImpl
import com.example.gomrukkolkulatoru.data.repo.RoomRepositoryImpl
import com.example.gomrukkolkulatoru.data.service.local.RoomDAO
import com.example.gomrukkolkulatoru.data.service.local.RoomDB
import com.example.gomrukkolkulatoru.data.service.remote.ApiService
import com.example.gomrukkolkulatoru.data.source.ApiSource
import com.example.gomrukkolkulatoru.data.source.ApiSourceImpl
import com.example.gomrukkolkulatoru.data.source.RoomSource
import com.example.gomrukkolkulatoru.data.source.RoomSourceImpl
import com.example.gomrukkolkulatoru.domain.repo.ApiRepository
import com.example.gomrukkolkulatoru.domain.repo.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RoomDB::class.java, "FavoritesDB").build()

    @Singleton
    @Provides
    fun provideRoomDAO(roomDB: RoomDB) = roomDB.getDao()

    @Singleton
    @Provides
    fun provideRoomSource(dao: RoomDAO) = RoomSourceImpl(dao) as RoomSource

    @Singleton
    @Provides
    fun provideApiSource(api: ApiService) = ApiSourceImpl(api) as ApiSource

    @Singleton
    @Provides
    fun provideRoomRepo(roomSource: RoomSource) = RoomRepositoryImpl(roomSource) as RoomRepository

    @Singleton
    @Provides
    fun provideApiRepo(apiSource: ApiSource) = ApiRepositoryImpl(apiSource) as ApiRepository
}