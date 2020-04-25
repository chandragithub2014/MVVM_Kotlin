package com.mvvm.databinding.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.databinding.R
import com.mvvm.databinding.databinding.FragmentBasicDataBidingBinding
import com.mvvm.databinding.databinding.FragmentDataBindingListBinding
import com.mvvm.databinding.viewmodel.DataBidingViewModel
import kotlinx.android.synthetic.main.fragment_data_binding_list.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DataBindingListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DataBindingListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var dataBindingListView:View
    lateinit var fragmentListBinding: FragmentDataBindingListBinding
    private val dataBidingViewModel: DataBidingViewModel by viewModels()
    private var adapter:DataBindingListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_data_binding_list,container,false)
        fragmentListBinding.lifecycleOwner = this
        fragmentListBinding.viewmodel = dataBidingViewModel
        // lifecycle.addObserver(fragmentBinding.viewmodel)
        dataBindingListView = fragmentListBinding.root


        return  dataBindingListView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolBar(dataBindingListView)
        initViewModel()
        initAdapter()
        setAdapter()
        observeViewModel()

    }
private fun initAdapter(){
    adapter = DataBindingListAdapter(this@DataBindingListFragment.requireActivity(),dataBidingViewModel)
    //this@DataBindingListFragment.requireActivity()
}
    private fun setAdapter(){
        dataBindingListView.dataBindingList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        dataBindingListView.dataBindingList.adapter = adapter
    }
    private fun initViewModel(){
        dataBidingViewModel.createEmpList()
    }

    private fun observeViewModel(){
        dataBidingViewModel.fetchEmpList().observe(viewLifecycleOwner,object:Observer<MutableList<String>>{
            override fun onChanged(t: MutableList<String>?) {
                println("List Info::::$t")
                if (t != null) {
                    adapter?.setAdapterList(t)
                }
            }

        })
    }

    private fun initToolBar(view:  View){
        val toolbar = view.findViewById<Toolbar>(R.id.toolBar)
        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        com.mvvm.common.utils.initToolBar(
            toolbar,
            title,
            "DataBinding List",
            activity as AppCompatActivity
        )
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DataBindingListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DataBindingListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}