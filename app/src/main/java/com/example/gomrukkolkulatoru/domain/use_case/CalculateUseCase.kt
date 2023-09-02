package com.example.gomrukkolkulatoru.domain.use_case

import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import com.example.gomrukkolkulatoru.domain.repo.ApiRepository
import javax.inject.Inject

class CalculateUseCase @Inject constructor(
    private val repo: ApiRepository
) {
    suspend operator fun invoke(calculatorModel: CalculatorModel) = repo.calculate(calculatorModel)

}