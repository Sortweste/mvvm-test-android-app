package com.sort.pinto.interfaces

import android.view.View

import android.view.MotionEvent

/*Provide interface for OnLongPress in all elements of RecyclerView*/
interface RecyclerViewOnTouchListener<T> {

    //fun onImageTouch(flag: Boolean, obj: T, motionEvent: MotionEvent)
    fun onImageTouch(v: View, obj: T)

}