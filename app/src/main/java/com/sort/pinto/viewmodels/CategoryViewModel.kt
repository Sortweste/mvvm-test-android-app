package com.sort.pinto.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sort.pinto.data.Category
import com.sort.pinto.repositories.CategoryRepository
import com.sort.pinto.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository): ViewModel(){

    private val _getCategories = repository.getCategories()

    val categories: LiveData<Resource<List<Category>>>
    get() = _getCategories

    private fun insert(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCategory(category)
    }

     fun updateCategory(category: Category) = viewModelScope.launch(Dispatchers.IO) {
         repository.updateCategory(category)
    }

    fun delete(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCategory(category.id.toString(), category)
    }

    // TODO: Use init to Live data page value

}