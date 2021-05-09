package com.sort.pinto.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sort.pinto.R
import com.sort.pinto.adapters.RecyclerViewAdapter
import com.sort.pinto.data.Category
import com.sort.pinto.databinding.FragmentCategoryBinding
import com.sort.pinto.interfaces.DeleteActionDialogListener
import com.sort.pinto.interfaces.RecyclerViewClickListener
import com.sort.pinto.interfaces.RecyclerViewOnLongClickListener
import com.sort.pinto.ui.dialogs.AddCategoryDialogFragment
import com.sort.pinto.ui.dialogs.DeleteDialogFragment
import com.sort.pinto.ui.snackbars.MutationSnackBar
import com.sort.pinto.utils.Resource
import com.sort.pinto.utils.isTablet
import com.sort.pinto.viewmodels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment :
        Fragment(),
        RecyclerViewClickListener<Category>,
        RecyclerViewOnLongClickListener<Category>,
        DeleteActionDialogListener,
        View.OnClickListener {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        binding.categoryViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        binding.fab.setOnClickListener(this)
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
            viewModel.categories.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (it.data.isNullOrEmpty())
                            adapter.setData(it.data)
                    }
                }
            })
        }
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewAdapter(this, this)
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

    private fun showDialog(){
        val dialog = AddCategoryDialogFragment()
        dialog.show(activity?.supportFragmentManager!!, "AddCategoryFragment")
    }

    override fun onCardViewClick(view: View, obj: Category) {
        val action = CategoryFragmentDirections.actionCategoryFragmentToCategoryListFragment(obj.id.toString())
        view.findNavController().navigate(action)
    }

    override fun onLongCardViewClick(view: View, obj: Category): Boolean {
        MutationSnackBar.make(
                binding.categoryCoordinatorLayout as ViewGroup,
                getString(R.string.edit_Category),
                getString(R.string.delete_Category),
                { viewModel.updateCategory(obj) },
                { showDeleteDialog(obj) }
        ).show()
        return true
    }

    private fun showDeleteDialog(obj: Category){
        val deleteDialog = DeleteDialogFragment(this, obj)
        deleteDialog.show(requireActivity().supportFragmentManager, "delete_category")
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.fab.id -> {
                showDialog()
            }
        }
    }

    override fun onDeleteAction(obj: Any) {
        viewModel.delete(obj as Category)
    }

}