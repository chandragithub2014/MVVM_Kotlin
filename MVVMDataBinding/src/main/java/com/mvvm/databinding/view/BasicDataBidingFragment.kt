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
import com.mvvm.common.utils.initToolBar
import com.mvvm.databinding.R
import com.mvvm.databinding.databinding.FragmentBasicDataBidingBinding
import com.mvvm.databinding.viewmodel.DataBidingViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BasicDataBidingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BasicDataBidingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var  dataBindingView:View
    lateinit var fragmentBinding: FragmentBasicDataBidingBinding
    private val dataBidingViewModel: DataBidingViewModel by viewModels()

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
        fragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_basic_data_biding,container,false)
        fragmentBinding.lifecycleOwner = this
        fragmentBinding.viewmodel = dataBidingViewModel
       // lifecycle.addObserver(fragmentBinding.viewmodel)
        dataBindingView = fragmentBinding.root
        initToolBar(dataBindingView)
        return  dataBindingView

    }

    fun initToolBar(view:  View){
        val toolbar = view.findViewById<Toolbar>(R.id.toolBar)
        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
         initToolBar(toolbar, title, "DataBiding Form", activity as AppCompatActivity)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BasicDataBidingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BasicDataBidingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}