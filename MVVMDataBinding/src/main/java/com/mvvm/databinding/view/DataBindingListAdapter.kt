package com.mvvm.databinding.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.databinding.R
import com.mvvm.databinding.databinding.FragmentDataBindingListItemBinding
import com.mvvm.databinding.viewmodel.DataBidingViewModel

class DataBindingListAdapter(var context: Context,dataBindingViewModel: DataBidingViewModel) :
    RecyclerView.Adapter<DataBindingViewHolder>() {
    private var list:MutableList<String> =  mutableListOf()
    var dataBindingViewModel:DataBidingViewModel = dataBindingViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {

        val binding:FragmentDataBindingListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_data_binding_list_item, parent, false)
        return DataBindingViewHolder(binding)
        /*
         val binding: ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_item, parent, false)
        return ListInfoAdapter.ViewHolder(binding)
         */
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bind(dataBindingViewModel, Integer(position));
    }

    fun setAdapterList(list: MutableList<String>) {
        this.list = list
        notifyDataSetChanged()
    }


}