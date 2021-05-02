package com.sort.pinto.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sort.pinto.constants.GET_ALL_CATEGORIES
import com.sort.pinto.data.Category
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CategoryDao : BaseDao<Category> {
    @Query(GET_ALL_CATEGORIES)
    abstract fun getAllCategories(): Flow<List<Category>>
}