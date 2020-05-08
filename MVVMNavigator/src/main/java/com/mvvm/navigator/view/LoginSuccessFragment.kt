package com.mvvm.navigator.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mvvm.navigator.R
import kotlinx.android.synthetic.main.fragment_login_success.*


class LoginSuccessFragment : Fragment() {

    private val args: LoginSuccessFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_success, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       args?.let{
            val loginSuccessMessage = args.successMessage
            success_tv.text = loginSuccessMessage
        }

    }


}