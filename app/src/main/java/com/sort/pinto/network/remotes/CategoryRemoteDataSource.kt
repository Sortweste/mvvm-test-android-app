package com.sort.pinto.network.remotes

import com.sort.pinto.network.services.CategoryService
import com.sort.pinto.utils.SafeApiRequest
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(private val categoryService: CategoryService): SafeApiRequest() {

    suspend fun getCategories() = apiRequest { categoryService.getCategories() }

}