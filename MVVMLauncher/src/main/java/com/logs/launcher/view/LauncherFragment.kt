package com.logs.launcher.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.logs.launcher.R
import com.logs.launcher.viewmodel.LauncherViewModel
import com.mvvm.appnavigator.openActivity
import kotlinx.android.synthetic.main.fragment_launcher.view.*
import java.lang.Exception

class LauncherFragment : Fragment(), LifecycleOwner,AdapterClickListener {

    var launcherView : View ? = null
    private val viewModel by activityViewModels<LauncherViewModel>()
    private val adapter = LauncherAdapter(this)
    private var mContainer:Int? = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.lifecycle?.addObserver(viewModel)
    //    Toast.makeText(activity,"In onCreate() method",Toast.LENGTH_LONG).show()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContainer = container?.id ?: -1
      //  Toast.makeText(activity,"In onCreateView() method",Toast.LENGTH_LONG).show()
        launcherView = inflater.inflate(R.layout.fragment_launcher, container, false)
            return  launcherView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.launcher_list.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        view.launcher_list.adapter = adapter

    }


    override fun onResume() {
        super.onResume()
       // Toast.makeText(activity,"In onResume() method",Toast.LENGTH_LONG).show()
        fetchLauncherLiveData()
    }


    private fun fetchLauncherLiveData(){
      //  Toast.makeText(activity,"In FetchLauncherData() method",Toast.LENGTH_LONG).show()
        viewModel.launcherMutableLiveData.observe(this,
            Observer<MutableList<String>> {
                    t ->
                Log.d("Launcher Fragment","List of Launchers ${t.toString()}")
                adapter.setList(t)
            })
    }

    override fun adapterClicked(position: Int, clickedItem: String?) {
        try {
            when (clickedItem) {

                "MVVM Login" -> context?.openActivity(Class.forName("com.mvvmlogin.view.LoginActivity"))
                "MVVM Room"  -> context?.openActivity(Class.forName("com.mvvm_kotlin.RoomDBActivity"))
                "MVVM Network" -> context?.openActivity(Class.forName("com.rxretrofit.view.RetroRxActivity"))
                "MVVM DataBinding"-> context?.openActivity(Class.forName("com.mvvm.databinding.view.BasicDataBindingActivity"))
                "MVVM DataBindingList" -> context?.openActivity(Class.forName("com.mvvm.databinding.view.DataBindingListActivity"))
                "MVVM Navigation" -> context?.openActivity(Class.forName("com.mvvm.navigator.NavigationMainActivity"))
                "MVVM Coroutines" -> context?.openActivity(Class.forName("com.rxretrofit.view.RetroRxCoroutineActivity"))
                "ConstructorInjection" -> context?.openActivity(Class.forName("com.dagger.practice.ConstructorInjectionActivity"))
                "FieldInjection" -> context?.openActivity(Class.forName("com.dagger.practice.FieldInjectionActivity"))
                "InterfaceInjection" -> context?.openActivity(Class.forName("com.dagger.practice.InterfaceInjectionActivity"))
                "NamedInjection" -> context?.openActivity(Class.forName("com.dagger.practice.NamedInjectionActivity"))
                "SingletonInjection" -> context?.openActivity(Class.forName("com.dagger.practice.SingletonInjectionActivity"))
                "SubComponentInjection" -> context?.openActivity(Class.forName("com.dagger.practice.CricketActivity"))
                "BottomSheetSamples" -> context?.openActivity(Class.forName("com.bottomsheet.BottomSheetActivity"))
                "KotlinPractice"-> context?.openActivity(Class.forName("com.kotlinpractice.KotlinPracticeActivity"))
                "MVVM CleanArchitecture"-> context?.openActivity(Class.forName("com.mvvm.cleanarchitecture.presentation.CleanArchitectureActivity"))
                "MVVM RetrofitCoroutines"-> context?.openActivity(Class.forName("com.mvvmcoroutine.retrofit.login.view.RetrofitLoginActivity"))
                "RxJavaPractice"->context?.openActivity(Class.forName("com.rxjava.practice.RxJavaPracticeActivity"))
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}