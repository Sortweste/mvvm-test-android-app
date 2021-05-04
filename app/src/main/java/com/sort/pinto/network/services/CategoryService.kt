package com.sort.pinto.network.services

import com.sort.pinto.constants.CATEGORIES
import com.sort.pinto.network.responses.PaginatedCategoriesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryService {

    @GET(CATEGORIES)
    suspend fun getCategories(
            @Query("page") page: String,
            @Query("limit") limit: String = "10"
    ): Response<PaginatedCategoriesResponse>

}