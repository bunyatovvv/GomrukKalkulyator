package com.example.gomrukkolkulatoru.data.source

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.common.util.findExceptionMessage
import com.example.gomrukkolkulatoru.data.dto.remote.CalculatorDTO
import com.example.gomrukkolkulatoru.data.service.remote.ApiService
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(
    private val api: ApiService
) : ApiSource {
    override suspend fun calculate(calculatorModel: CalculatorModel): Resource<CalculatorDTO> {
        return try {
            val response = api.calculate(calculatorModel)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.success(it)
                } ?: Resource.error("Error calculate response", null)
            } else {
                val errorMessage = findExceptionMessage(response.errorBody())
                Resource.error(errorMessage, null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Error message", null)
        }
    }
}