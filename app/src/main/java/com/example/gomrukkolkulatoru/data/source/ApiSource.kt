package com.example.gomrukkolkulatoru.data.source

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.data.dto.remote.CalculatorDTO
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel

interface ApiSource {
    suspend fun calculate(calculatorModel: CalculatorModel): Resource<CalculatorDTO>
}