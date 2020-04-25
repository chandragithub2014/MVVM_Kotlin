package com.mvvmlogin.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mvvmlogin.R
import com.mvvmlogin.model.LoginModel
import com.mvvmlogin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(),LifecycleOwner {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var loginView:View
    private val viewModel by activityViewModels<LoginViewModel>()

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
        loginView =  inflater.inflate(R.layout.fragment_login, container, false)
        return  loginView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerViewModel()
        view.login_btn.setOnClickListener {
            onLoginClick(view)

        }
    }

    private fun observerViewModel(){
      viewModel.loginStatus.observe(viewLifecycleOwner, Observer<String> {
                t ->
          Toast.makeText(activity,t,Toast.LENGTH_LONG).show()
        })
        }

     private fun onLoginClick(view: View){
         var loginModel = LoginModel(view.userName_editText.text.toString(),
             view.password_editText.text.toString())
         viewModel.validateLogin(loginModel)
     }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}