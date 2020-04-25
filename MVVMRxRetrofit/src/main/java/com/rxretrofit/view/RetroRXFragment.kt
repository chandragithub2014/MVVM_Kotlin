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
import com.rxretrofit.R
import com.rxretrofit.model.RetroRxModel
import com.rxretrofit.viewmodel.RetroRXViewModel
import kotlinx.android.synthetic.main.fragment_retro_rx.view.*
import com.mvvm.common.utils.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RetroRXFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RetroRXFragment : Fragment() ,LifecycleOwner{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private  val viewModel : RetroRXViewModel by viewModels()
    lateinit var retrorxView : View
    private val adapter = RetrofitAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        activity?.lifecycle?.addObserver(viewModel)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        retrorxView =  inflater.inflate(R.layout.fragment_retro_rx, container, false)
        return retrorxView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("In onViewCreated().........")
        setHasOptionsMenu(true)
        val toolbar = view.findViewById<Toolbar>(R.id.toolBar)
        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        initToolBar(toolbar,title,"Retrofit", activity as AppCompatActivity)
        view.retroList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        view.retroList.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
       // println("In onResume().........")
        observeViewModel()


    }



    private fun observeViewModel(){
        viewModel.loading.observe(viewLifecycleOwner, Observer<Boolean> { t ->
            if(t == false){
                retrorxView.progressBar.visibility = View.GONE
            }else if(t == true){
                retrorxView.progressBar.visibility = View.VISIBLE
            }
        })

        viewModel.postLoadError.observe(viewLifecycleOwner, Observer<String> { t ->
            if(!t.isNullOrEmpty()){
                Toast.makeText(activity,"Error in API call $t",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.fetchRetroResponseLiveData().observe(viewLifecycleOwner,
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
         * @return A new instance of fragment RetroRXFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RetroRXFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}