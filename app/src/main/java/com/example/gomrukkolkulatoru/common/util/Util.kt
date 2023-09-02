package com.example.gomrukkolkulatoru.common.util

import okhttp3.ResponseBody
import org.json.JSONObject

fun findExceptionMessage(errorBody: ResponseBody?): String {
    if (errorBody != null) {
        val errorObj = JSONObject(errorBody.charStream().readText())
        return errorObj.getString("JSON Error message")
    }
    return "Error JSON"
}

