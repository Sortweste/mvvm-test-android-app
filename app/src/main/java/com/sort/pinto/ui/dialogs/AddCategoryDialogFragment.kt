package com.sort.pinto.ui.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.sort.pinto.R
import com.sort.pinto.databinding.DialogAddCategoryBinding
import com.sort.pinto.viewmodels.AddCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class AddCategoryDialogFragment : DialogFragment() {

    private var _binding: DialogAddCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCategoryViewModel by viewModels()
    private var selectedImageUri: Uri? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddCategoryBinding.inflate(LayoutInflater.from(context))
        // add view model to ui
        val dialog =  activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                    .setView(binding.root)
                    .setTitle(getString(R.string.add_category_dialog_title))
                    .setPositiveButton("Send") { dialog, id -> uploadData() }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog?.cancel() } //clear stored data

            binding.uploadButton.setOnClickListener { uploadImage() }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private val selectImage =  registerForActivityResult(ActivityResultContracts.GetContent()){
        it?.let { uri ->
            selectedImageUri = uri
            binding.uploadedImage.setImageURI(uri)
        }
    }

    @SuppressLint("InlinedApi")
    private fun uploadImage(){
        selectImage.launch("image/*")
    }

    @SuppressLint("NewApi")
    private fun uploadData(){
        val parcelFileDescriptor = context?.contentResolver?.openFileDescriptor(selectedImageUri!!, "r",null)
        val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
        /*val file = File("", context?.contentResolver)
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}