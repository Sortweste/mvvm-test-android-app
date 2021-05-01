package com.sort.pinto.utils

import android.net.Uri
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sort.pinto.interfaces.BindAdapter

/*Set data for Recyclerview*/
@BindingAdapter("data")
@Suppress("UNCHECKED_CAST")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<T>?){
    if (recyclerView.adapter is BindAdapter<*>) {
        (recyclerView.adapter as BindAdapter<T>).setData(data)
    }
}

/*Fetch image from URL and set it to ImageView*/
@BindingAdapter("setImageUrl")
fun bindImageUrl(view: ImageView, url: String?){
    if(!url.isNullOrBlank())
        Glide.with(view.context).asBitmap().load(Uri.parse(url)).fitCenter().into(view)
}

/*Same as before, but centerCrop*/
@BindingAdapter("setImageUrlCrop")
fun bindImageUrlCrop(view: ImageView, url: String?){
    if(!url.isNullOrBlank())
        Glide.with(view.context).asBitmap().load(Uri.parse(url)).centerCrop().into(view)
}

/*Hide widget*/
@BindingAdapter("goneUnless")
fun goneUnless(view: View, notVisible: Boolean){
    view.visibility = if(notVisible) View.GONE else View.VISIBLE
}


/*Databinding for Spinner Modal*/
@Suppress("UNCHECKED_CAST")
@BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
fun setSpinnerSelectedValue(spinner: Spinner, selectedValue: String?, newTextAttrChanged: InverseBindingListener){
    spinner.onItemSelectedListener = object : OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {}
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) = newTextAttrChanged.onChange()
    }
    val pos = (spinner.adapter as ArrayAdapter<String?>).getPosition(selectedValue)
    spinner.setSelection(pos, true)
}

@InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
fun getSpinnerSelectedValue(spinner: Spinner): String = spinner.selectedItem.toString()
