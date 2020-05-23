package com.mvvmcoroutine.retrofit.userlist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.mvvmcoroutine.retrofit.R
import com.mvvmcoroutine.retrofit.userlist.model.Data
import com.mvvmcoroutine.retrofit.userlist.viewmodel.UserListViewModel
import com.mvvmcoroutine.retrofit.userlist.viewmodel.UserListViewModelFactory
import kotlinx.android.synthetic.main.fragment_user_list_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserListDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var userDetailView : View ? = null
    lateinit var viewModel:UserListViewModel

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
        userDetailView =  inflater.inflate(R.layout.fragment_user_list_detail, container, false)
        return  userDetailView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detail_progressBar.visibility = View.VISIBLE
        initViewModel()
    }

    private fun initViewModel(){
        var userListViewModelFactory = UserListViewModelFactory()
        viewModel = ViewModelProviders.of(this, userListViewModelFactory).get(UserListViewModel::class.java)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        param1?.let {
            viewModel.fetchUserDetailInfo(it.toInt())
        }
    }

    private fun observeViewModel(){
        viewModel.selectedUserMutableLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                populateUserInterface(it)
            }
        })

        viewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            if(!it){
                detail_progressBar.visibility = View.GONE
            }
        })
    }

    private fun populateUserInterface(data: Data){
        Glide.with(this).load(data.avatar).placeholder(R.drawable.ic_sync).into(user_avatar)
        firstName_ed.text = data.first_name
        lastName_ed.text = data.last_name
        email_ed.text = data.email
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserListDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserListDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}