package com.sort.pinto.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.sort.pinto.databinding.DialogDeleteWarningBinding
import com.sort.pinto.interfaces.DeleteActionDialogListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteDialogFragment(
        private val listener: DeleteActionDialogListener,
        private val obj: Any
): DialogFragment() {

    private var _binding: DialogDeleteWarningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogDeleteWarningBinding.inflate(LayoutInflater.from(context))
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                    .setView(binding.root)
            binding.cancelDeleteButton.setOnClickListener { this.dialog?.cancel() }
            binding.confirmDeleteButton.setOnClickListener { listener.onDeleteAction(obj) }
            builder.create()
        }  ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}