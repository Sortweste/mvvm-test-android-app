package com.sort.pinto.network

import com.sort.pinto.storage.AuthManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor @Inject constructor(private val authManager: AuthManager): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Content-Type", "application/json; charset=utf-8")
        authManager.getAccessToken()?.let {
            builder.header("Authorization", "Bearer $it")
        }
        return chain.proceed(builder.build())
    }
}