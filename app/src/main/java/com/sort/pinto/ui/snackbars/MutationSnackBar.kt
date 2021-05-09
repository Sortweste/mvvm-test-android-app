package com.sort.pinto.ui.snackbars

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.sort.pinto.R
import kotlinx.android.synthetic.main.mutation_snackbar.view.*

class MutationSnackBar(
        parent: ViewGroup,
        content: CustomSnackBarView
): BaseTransientBottomBar<MutationSnackBar>(parent, content, content){

    private fun setAction(listener: View.OnClickListener): MutationSnackBar {
        val contentLayout = this.view.getChildAt(0) as CustomSnackBarView
        contentLayout.setOnClickListener {
            listener.onClick(it)
            dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION)
        }
        return this
    }

    companion object {
        fun make(
                viewGroup: ViewGroup,
                editMessage: String,
                deleteMessage: String,
                editAction: () -> Unit,
                deleteAction: () -> Unit
        ): MutationSnackBar{
            val customView =
                    LayoutInflater.from(viewGroup.context)
                            .inflate(R.layout.custom_snackbar_layout, viewGroup, false)
                            as CustomSnackBarView
            customView.edit_text.text = editMessage
            customView.delete_text.text = deleteMessage
            customView.edit.setOnClickListener { editAction() }
            customView.delete.setOnClickListener { deleteAction() }
            return MutationSnackBar(viewGroup, customView)
                    .setDuration(Snackbar.LENGTH_INDEFINITE)
        }
    }

}