package com.sort.pinto.interfaces

/*Provides interface for setting data to Recyclerview in LiveData Observer*/
interface BindAdapter<T> {
    fun setData(items: List<T>?)
}