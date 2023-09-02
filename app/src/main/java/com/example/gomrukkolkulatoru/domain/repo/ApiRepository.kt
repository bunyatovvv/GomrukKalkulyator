package com.example.gomrukkolkulatoru.domain.repo

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import com.example.gomrukkolkulatoru.domain.model.DutyModel
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    suspend fun calculate(calculatorModel: CalculatorModel): Flow<Resource<List<DutyModel>>>
}