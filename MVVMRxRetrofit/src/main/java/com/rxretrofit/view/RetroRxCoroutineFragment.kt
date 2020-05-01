package com.rxretrofit.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.common.utils.initToolBar
import com.rxretrofit.R
import com.rxretrofit.model.RetroRxModel
import com.rxretrofit.viewmodel.RetroCoroutineViewModel
import com.rxretrofit.viewmodel.RetroCoroutineViewModelFactory
import kotlinx.android.synthetic.main.fragment_retro_rx.view.*
import androidx.lifecycle.ViewModelProviders

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RetroRxCoroutineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RetroRxCoroutineFragment : Fragment() , LifecycleOwner{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel:RetroCoroutineViewModel
    lateinit var  retroCoroutineView: View
    private val adapter = RetrofitAdapter()

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
        retroCoroutineView =  inflater.inflate(R.layout.fragment_retro_rx, container, false)
        return retroCoroutineView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        this.lifecycle.addObserver(viewModel)
        val toolbar = view.findViewById<Toolbar>(R.id.toolBar)
        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        initToolBar(toolbar,title,"Retrofit", activity as AppCompatActivity)
        view.retroList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        view.retroList.adapter = adapter
        observeViewModel()
    }

    fun initViewModel() {
        var retroViewModelFactory = RetroCoroutineViewModelFactory()
        viewModel = ViewModelProviders.of(this, retroViewModelFactory).get(RetroCoroutineViewModel::class.java)
    }

    private fun observeViewModel(){

        viewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer<Boolean> { t ->
            if(t == false){
                retroCoroutineView.progressBar.visibility = View.GONE
            }else if(t == true){
                retroCoroutineView.progressBar.visibility = View.VISIBLE
            }
        })


        viewModel.fetchError().observe(viewLifecycleOwner, Observer<String> { t ->
            if(!t.isNullOrEmpty()){
                Toast.makeText(activity,"Error in API call $t", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.fetchPostLiveData().observe(viewLifecycleOwner,
            Observer<List<RetroRxModel>> {
                    t -> println("API Response:::$t")
                adapter.setList(t)
            }
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RetroRxCoroutineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RetroRxCoroutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}