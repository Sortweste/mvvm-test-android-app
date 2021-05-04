package com.sort.pinto.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.sort.pinto.R
import com.sort.pinto.adapters.RecyclerViewAdapter
import com.sort.pinto.data.Category
import com.sort.pinto.databinding.FragmentCategoryBinding
import com.sort.pinto.interfaces.RecyclerViewClickListener
import com.sort.pinto.utils.isTablet
import com.sort.pinto.viewmodels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment(), RecyclerViewClickListener<Category> {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding.categoryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CategoryFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.categories.observe(viewLifecycleOwner, Observer{
                if (it.isNotEmpty())
                    adapter.setData(it)
            })
        }
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewAdapter(this)
        binding.recyclerViewArticle.also {
            it.setHasFixedSize(true)
            if(isTablet(requireContext().applicationContext)){
                it.layoutManager = GridLayoutManager(requireContext(), 3)
            }else{
                it.layoutManager = GridLayoutManager(requireContext(), 2)
            }
            it.adapter = adapter
        }
    }

    override fun onCardViewClick(view: View, obj: Category) {

    }
}