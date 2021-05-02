package com.sort.pinto.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.pinto.R
import com.sort.pinto.data.Category
import com.sort.pinto.databinding.CardViewCategoryBinding
import com.sort.pinto.interfaces.BindAdapter
import com.sort.pinto.interfaces.RecyclerViewClickListener

/*RecyclerViewAdapter class using databinding*/
class RecyclerViewAdapter(private val listener: RecyclerViewClickListener<Category>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(),
    BindAdapter<Category> {

    private var items: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewCategoryBinding = DataBindingUtil.inflate(view, R.layout.card_view_category,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Category){
            binding.category = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onCardViewClick(it, item) }
        }
    }

    override fun setData(items: List<Category>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }

}