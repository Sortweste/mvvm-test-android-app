package com.sort.pinto.network.services

import com.sort.pinto.constants.CATEGORIES
import com.sort.pinto.constants.CATEGORY_BY_ID
import com.sort.pinto.data.Category
import com.sort.pinto.network.responses.PaginatedCategoriesResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface CategoryService {

    @GET(CATEGORIES)
    suspend fun getCategories(
            @Query("page") page: String,
            @Query("limit") limit: String = "10"
    ): Response<PaginatedCategoriesResponse>

    @Multipart
    @POST(CATEGORIES)
    suspend fun createCategory(
            @Part image: MultipartBody.Part,
            @Part("name") categoryName: RequestBody
    ): Response<Category>

    @PATCH(CATEGORY_BY_ID)
    suspend fun updateCategory(
            @Path("id") id: String,
            @Body category: Category
    ): Response<Category>

    @DELETE(CATEGORY_BY_ID)
    suspend fun deleteCategory(
            @Path("id") id: String
    ): Response<Unit>


}