package com.sort.pinto.ui.snackbars

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import com.sort.pinto.R

class CustomSnackBarView @JvmOverloads constructor(
        context: Context,
        attributeSet: AttributeSet? = null,
        defaultStyle: Int = 0
): ConstraintLayout(context, attributeSet, defaultStyle), ContentViewCallback {

    private var actionView: Button? = null

    init {
        View.inflate(context, R.layout.mutation_snackbar, this)
        clipToPadding =false
    }

    fun getActionView() = actionView

    override fun animateContentIn(delay: Int, duration: Int) {}

    override fun animateContentOut(delay: Int, duration: Int) {}

}