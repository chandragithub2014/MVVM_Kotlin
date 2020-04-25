package com.mvvm_kotlin.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mvvm_kotlin.R
import com.mvvm_kotlin.viewmodel.RoomListViewModel
import com.room.db.userRepo.UserInfo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddUserFragment : Fragment() ,LifecycleOwner{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var addUserView:View
   // private val viewModel by activityViewModels<RoomListViewModel>()
    private val viewModel: RoomListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
       // activity?.lifecycle?.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addUserView =  inflater.inflate(R.layout.add_user_layout, container, false)
        return  addUserView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.lifecycle.addObserver(viewModel)
        setHasOptionsMenu(true)
        initToolBar(view)
        val name = view.findViewById<TextView>(R.id.ed_name)
        val passWord = view.findViewById<TextView>(R.id.ed_password)
        val city = view.findViewById<TextView>(R.id.ed_city)
        val age = view.findViewById<TextView>(R.id.ed_age)
        val mobile = view.findViewById<TextView>(R.id.phone)
        val saveBtn = view.findViewById<Button>(R.id.save)

        saveBtn.setOnClickListener {
            var userAge = -1
            if(!age.text.toString().isNullOrEmpty()){
                userAge = age.text.toString().toInt()
            }
            val user = UserInfo(name.text.toString(),
                passWord.text.toString().hashCode(),
                userAge,
                city.text.toString(),
                mobile.text.toString()
            )
            onSaveClick(user)
        }
        observeViewModel()
    }

    private fun onSaveClick(userInfo: UserInfo){
        viewModel.insertUser(userInfo)
    }

    private  fun observeViewModel(){
        viewModel.error.observe(viewLifecycleOwner,
            Observer<String> { t -> Toast.makeText(activity,t,Toast.LENGTH_LONG).show() })

        viewModel.insertedId.observe(viewLifecycleOwner,
            Observer<Long> { t ->
                if(t != -1L){
                    Toast.makeText(activity,"Inserted Successfully",Toast.LENGTH_LONG).show()
                    activity?.let{

                        activity?.supportFragmentManager?.popBackStack()
                    }

                }else{
                    Toast.makeText(activity,"Insert Failed",Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun initToolBar(view: View){
        val toolbar: Toolbar = view.findViewById(R.id.toolBar) as Toolbar

        //set toolbar appearance
        //set toolbar appearance
        toolbar.setBackgroundColor(Color.CYAN)
        var toolBarTitle : TextView = toolbar.findViewById(R.id.toolbar_title)
        toolBarTitle.text = "Add User"
        //for crate home button
        //for crate home button
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.let{
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            activity.supportActionBar?.title = ""
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddUserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddUserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}