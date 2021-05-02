package com.sort.pinto.network.services

import com.sort.pinto.constants.CATEGORIES
import com.sort.pinto.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryService {

    @POST(CATEGORIES)
    suspend fun getCategories(@Body loginDTO: LoginDTO): Response<AuthResponse>

}