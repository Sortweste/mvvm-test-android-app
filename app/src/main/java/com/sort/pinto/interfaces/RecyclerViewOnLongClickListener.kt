package com.sort.pinto.interfaces

import android.view.View

interface RecyclerViewOnLongClickListener<T> {
    fun onLongCardViewClick(view: View, obj: T): Boolean
}