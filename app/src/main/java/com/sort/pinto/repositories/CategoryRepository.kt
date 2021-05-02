package com.sort.pinto.repositories

import com.sort.pinto.data.Category
import com.sort.pinto.data.dao.CategoryDao
import com.sort.pinto.network.remotes.CategoryRemoteDataSource
import com.sort.pinto.utils.performGetOperation
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryRemoteDataSource: CategoryRemoteDataSource, private val categoryDao: CategoryDao){

    val getLocalCategories = categoryDao.getAllCategories()

    fun getCategories() = performGetOperation{ categoryRemoteDataSource.getCategories() }

    suspend fun insertCategories(categories: List<Category>){
        categoryDao.insertMany(*categories.toTypedArray())
    }

}