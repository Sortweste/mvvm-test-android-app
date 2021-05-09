package com.sort.pinto.viewmodels

import androidx.lifecycle.ViewModel
import com.sort.pinto.repositories.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.lifecycle.viewModelScope
import com.sort.pinto.data.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream

@HiltViewModel
class AddCategoryViewModel @Inject constructor(private val repository: CategoryRepository): ViewModel(){

    fun createCategory(parcelFileDescriptor: ParcelFileDescriptor){
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        /*val file = File(cacheDir)
        // call networkRequest
        // viewModel scope launch ?
        insert() */
    }

    fun insert(category: Category) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCategory(category)
    }

}