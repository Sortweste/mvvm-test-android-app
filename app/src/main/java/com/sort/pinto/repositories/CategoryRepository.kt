package com.sort.pinto.repositories

import com.sort.pinto.data.Category
import com.sort.pinto.data.dao.CategoryDao
import com.sort.pinto.network.remotes.CategoryRemoteDataSource
import com.sort.pinto.utils.performDeleteOperation
import com.sort.pinto.utils.performGetOperation
import com.sort.pinto.utils.performPatchOperation
import com.sort.pinto.utils.performPostOperation
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource, private val categoryDao: CategoryDao){

    fun getCategories(pages: String = "1") = performGetOperation(
            databaseQuery = { categoryDao.getAllCategories() },
            networkCall = { categoryRemoteDataSource.getCategories(pages) },
            saveCallResult = { categoryDao.insertMany(*it.items.toTypedArray()) } // Auto updates displayed info due to LiveData
    )

    fun insertCategory(image: MultipartBody.Part, categoryName: RequestBody) =
            performPostOperation { categoryRemoteDataSource.createCategory(image, categoryName) }

    fun deleteCategory(id: String, category: Category) = performDeleteOperation(
            networkCall = { categoryRemoteDataSource.deleteCategory(id) },
            saveCallResult = { categoryDao.delete(category) }
    )

    fun updateCategory(category: Category) =  performPatchOperation(
            networkCall = { categoryRemoteDataSource.updateCategory(category.id.toString(), category) },
            saveCallResult = { categoryDao.update(it) }
    )

    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

}