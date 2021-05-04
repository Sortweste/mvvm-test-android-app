package com.sort.pinto.repositories

import com.sort.pinto.data.Category
import com.sort.pinto.data.dao.CategoryDao
import com.sort.pinto.network.remotes.CategoryRemoteDataSource
import com.sort.pinto.utils.performGetOperation
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource, private val categoryDao: CategoryDao){

    fun getCategories(pages: String = "1") = performGetOperation(
            databaseQuery = { categoryDao.getAllCategories() },
            networkCall = { categoryRemoteDataSource.getCategories(pages) },
            saveCallResult = { categoryDao.insertMany(*it.items.toTypedArray()) } // Auto updates displayed info due to LiveData
    )

    suspend fun insertCategory(category: Category){
        categoryDao.insert(category)
    }

}