package com.sort.pinto.network.services

import com.sort.pinto.constants.AUTH_LOGIN
import com.sort.pinto.constants.AUTH_LOGOUT
import com.sort.pinto.constants.AUTH_REFRESH
import com.sort.pinto.network.dtos.AuthDTO
import com.sort.pinto.network.dtos.LoginDTO
import com.sort.pinto.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.*

interface AuthService {

    @POST(AUTH_LOGIN)
    suspend fun login(@Body loginDTO: LoginDTO): Response<AuthResponse>

    @POST(AUTH_LOGOUT)
    suspend fun logout(): Response<Unit>

    @POST(AUTH_REFRESH)
    suspend fun refresh(@Body authDTO: AuthDTO): Response<AuthResponse>

}