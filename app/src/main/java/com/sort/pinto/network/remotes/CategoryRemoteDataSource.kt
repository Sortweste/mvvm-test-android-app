package com.sort.pinto.network.remotes

import com.sort.pinto.data.Category
import com.sort.pinto.network.services.CategoryService
import com.sort.pinto.utils.SafeApiRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(private val categoryService: CategoryService): SafeApiRequest() {

    suspend fun getCategories(pages: String) = apiRequest { categoryService.getCategories(pages) }

    suspend fun createCategory(image: MultipartBody.Part, categoryName: RequestBody) =
            apiRequest { categoryService.createCategory(image, categoryName) }

    suspend fun deleteCategory(id: String) =
            apiRequest { categoryService.deleteCategory(id) }

    suspend fun updateCategory(id: String, category: Category) =
            apiRequest { categoryService.updateCategory(id, category) }

}