package com.example.gomrukkolkulatoru.data.repo

import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.common.util.Status
import com.example.gomrukkolkulatoru.data.mapper.toDutyModel
import com.example.gomrukkolkulatoru.data.source.ApiSource
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import com.example.gomrukkolkulatoru.domain.model.DutyModel
import com.example.gomrukkolkulatoru.domain.repo.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiSource: ApiSource
) : ApiRepository {
    override suspend fun calculate(calculatorModel: CalculatorModel): Flow<Resource<List<DutyModel>>> =
        flow {
            emit(Resource.loading(null))
            val response = apiSource.calculate(calculatorModel)

            when (response.status) {
                Status.ERROR -> {
                    emit(Resource.error(response.message ?: "Error", null))
                }

                Status.SUCCESS -> {
                    emit(Resource.success(response.data?.data?.autoDuty?.duties?.toDutyModel()))
                }

                else -> {}
            }
        }
}