package com.example.gomrukkolkulatoru.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gomrukkolkulatoru.common.util.Resource
import com.example.gomrukkolkulatoru.domain.model.CalculatorModel
import com.example.gomrukkolkulatoru.domain.model.DutyModel
import com.example.gomrukkolkulatoru.domain.use_case.AddFavoritesUseCase
import com.example.gomrukkolkulatoru.domain.use_case.CalculateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val calculateUseCase: CalculateUseCase,
    private val addFavoritesUseCase: AddFavoritesUseCase
) : ViewModel() {

    private val _calculateData = MutableLiveData<Resource<List<DutyModel>>>()
    val calculateData : LiveData<Resource<List<DutyModel>>>
        get() = _calculateData

    val calculateModel : MutableLiveData<CalculatorModel> = MutableLiveData()

    fun calculate(calculatorModel: CalculatorModel) = viewModelScope.launch {
        val response = calculateUseCase.invoke(calculatorModel)
        response.collectLatest {
            _calculateData.value = it
        }
    }
}