package com.sort.pinto.utils

import retrofit2.Response

/*Display information if Status is SUCCESS*/
abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): Resource<T> {
        val response = call.invoke()
        return if (response.isSuccessful)
            Resource.success(response.body()!!)
        else
        //error(response.message(), response.code())
            error(response.code())
    }

    private fun <T> error(code: Int): Resource<T> {
        var message = ""
        when(code){
            404 -> {message = "No se ha encontrado"}
            400 -> {message = "Datos enviados invalidos"}
            500 -> {message = "Error en el servidor"}
        }
        return Resource.error(message)
    }

}
