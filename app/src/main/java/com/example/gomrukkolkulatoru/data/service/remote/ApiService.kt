package com.example.gomrukkolkulatoru.data.service.remote

import com.example.gomrukkolkulatoru.data.dto.remote.CalculatorDTO
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("dictionaries/calAutoDuty")
    suspend fun calculate(@Body calculatorModel: CalculatorModel): Response<CalculatorDTO>
}