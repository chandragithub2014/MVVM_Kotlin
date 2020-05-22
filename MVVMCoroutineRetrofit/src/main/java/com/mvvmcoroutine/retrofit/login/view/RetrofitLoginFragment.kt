package com.mvvmcoroutine.retrofit.login.view

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvm.appnavigator.hideKeyboard
import com.mvvmcoroutine.retrofit.R
import com.mvvmcoroutine.retrofit.login.model.LoginModel
import com.mvvmcoroutine.retrofit.login.viewmodel.LoginViewModel
import com.mvvmcoroutine.retrofit.login.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.fragment_retrofit_login.*
import kotlinx.android.synthetic.main.fragment_retrofit_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RetrofitLoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RetrofitLoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var loginView : View? = null
    lateinit var viewModel : LoginViewModel

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
        loginView =  inflater.inflate(R.layout.fragment_retrofit_login, container, false)
        return loginView
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        retrologin_email.setText("eve.holt@reqres.in")
        retrologin_password.setText("cityslicka")

        retro_login_btn.setOnClickListener {
            retro_login_btn.hideKeyboard()
            login_progress.visibility = View.VISIBLE
            viewModel.validateLogin(LoginModel(retrologin_email.text.toString(),retrologin_password.text.toString()))
        }

        observeViewModel()
    }


    private fun initViewModel(){
        var loginViewModelFactory = LoginViewModelFactory()
        viewModel = ViewModelProviders.of(this, loginViewModelFactory).get(LoginViewModel::class.java)
    }


    private fun observeViewModel(){
        viewModel.fetchTokenStatus().observe(viewLifecycleOwner, Observer {
           it?.let {
               println("ResponseToken is ${it.token}")
           }
        })

        viewModel.fetchLoadStatus().observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    login_progress.visibility = View.VISIBLE
                }else{
                    login_progress.visibility = View.GONE
                }
            }
        })

        viewModel.fetchError().observe(viewLifecycleOwner, Observer {
            it?.let {
                if(!TextUtils.isEmpty(it)){
                    Toast.makeText(context,"$it",Toast.LENGTH_LONG).show()
                }

            }
        })
    }

}