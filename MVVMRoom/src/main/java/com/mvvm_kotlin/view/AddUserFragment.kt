package com.mvvm_kotlin.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mvvm.common.modalbottomsheetdialog.ModalCustomBottomSheet
import com.mvvm_kotlin.R
import com.mvvm_kotlin.viewmodel.RoomListViewModel
import com.room.db.userRepo.UserInfo
import com.utilslibrary.loggerutils.LoggerHelper
import com.utilslibrary.sharedprefsutils.SharedPreferencesHelper


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val TAG = "AddUserFragment"
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
   private var userInfo:UserInfo?=null
    private fun onSaveClick(userInfo: UserInfo){
    //    viewModel.insertUser(userInfo)
        this.userInfo = userInfo
        showCustomModalBottomSheet()

    }




    private  fun observeViewModel(){
        viewModel.error.observe(viewLifecycleOwner,
            Observer<String> { t -> Toast.makeText(activity,t,Toast.LENGTH_LONG).show() })

        viewModel.insertedId.observe(viewLifecycleOwner,
            Observer<Long> { t ->
                if(t != -1L){
                   // Toast.makeText(activity,"Inserted Successfully",Toast.LENGTH_LONG).show()
                    context?.applicationContext?.let{  SharedPreferencesHelper.invoke(it).saveLongPreferences("insertedID",t)}

                    context?.applicationContext?.let { LoggerHelper().displayToast(it,"Inserted Successfully in DB") }
                    activity?.let{

                        activity?.supportFragmentManager?.popBackStack()
                    }

                }else{
                   // Toast.makeText(activity,"Insert Failed",Toast.LENGTH_LONG).show()
                    context?.applicationContext?.let { LoggerHelper().displayToast(it,"Inserted Successfully") }
                }
                val sharedPreferencesVal = context?.applicationContext?.let{SharedPreferencesHelper.invoke(it).fetchLongPreferences("insertedID")}
                LoggerHelper().displayDebugLog(TAG,"Inserted Id:::${sharedPreferencesVal}")
                println("Shared Preferences Data:::::${sharedPreferencesVal}")
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

/*    override fun providesBottomSheetView(view: View) {
        view.ok_tv.setOnClickListener {
            userInfo?.let { it1 -> viewModel.insertUser(it1) }
        }
        view.cancel_tv.setOnClickListener {
            modalBottomSheet?.dismiss()
        }
    }*/


    private var modalBottomSheet : ModalCustomBottomSheet?=null
    private fun showCustomModalBottomSheet(){
         modalBottomSheet = ModalCustomBottomSheet.newInstance(false,R.layout.confirmation_bottom_sheet)
        activity?.supportFragmentManager?.let { modalBottomSheet?.show(it, ModalCustomBottomSheet.TAG) }


    }


    private val activityReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
           println("Received ${intent?.getStringExtra("itemName")}")
            when(intent?.getStringExtra("itemName")){
                "OK" ->{
                    modalBottomSheet?.dismiss()
                    userInfo?.let { viewModel.insertUser(it) }
                }
                "Cancel"  -> modalBottomSheet?.dismiss()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(activityReceiver,  IntentFilter("KEY"));
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(activityReceiver)
    }


}