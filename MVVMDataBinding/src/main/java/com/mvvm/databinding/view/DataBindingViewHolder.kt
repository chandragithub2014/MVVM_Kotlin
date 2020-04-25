package com.mvvm.databinding.view

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.databinding.viewmodel.DataBidingViewModel
import com.mvvm.databinding.BR

class DataBindingViewHolder(binding:ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    private var binding:ViewDataBinding = binding
    fun bind(viewModel: DataBidingViewModel, position: Integer) {
        viewModel.fetchEmpList(position)
        binding.setVariable(BR.viewmodel, viewModel)
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }

}