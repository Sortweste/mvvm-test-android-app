package com.sort.pinto.network

import com.sort.pinto.storage.AuthManager
import com.sort.pinto.utils.Resource
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class Authenticator: Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        /*return runBlocking {
            when(val refreshResponse = ){
                Resource.Status.SUCCESS -> {
                    response.request().newBuilder()
                        .header("Authorizarion", "Bearer ${}")
                        .build()
                }
                else -> null
            }
        }*/
        TODO("Find how to inject authManager")
    }

}