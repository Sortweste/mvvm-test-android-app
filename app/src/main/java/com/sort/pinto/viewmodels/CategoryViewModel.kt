package com.sort.pinto.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sort.pinto.data.Category
import com.sort.pinto.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository): ViewModel(){

    private val _getCategories = repository.getCategories()

    val categories: LiveData<List<Category>>
    get() = _getCategories

}