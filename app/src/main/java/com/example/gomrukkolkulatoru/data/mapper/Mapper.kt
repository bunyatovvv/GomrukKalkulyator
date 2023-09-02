package com.example.gomrukkolkulatoru.data.mapper

import com.example.gomrukkolkulatoru.data.dto.remote.Duty
import com.example.gomrukkolkulatoru.domain.model.DutyModel

fun List<Duty>.toDutyModel() = map {
    DutyModel(
        name = it.name,
        value = it.value
    )
}
