package com.mvvm_kotlin.view

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.appnavigator.replaceFragment
import com.mvvm_kotlin.R
import com.mvvm_kotlin.viewmodel.RoomListViewModel
import com.room.db.userRepo.UserInfo
import kotlinx.android.synthetic.main.fragment_room_data_base.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomDataBaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomDataBaseFragment : Fragment(),LifecycleOwner {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var roomDBView:View
  //  private val viewModel by activityViewModels<RoomListViewModel>()
    private val viewModel: RoomListViewModel by viewModels()
    private val adapter = RoomDataBaseAdapter()
    var mContainerId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
      //  activity?.lifecycle?.addObserver(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        roomDBView =  inflater.inflate(R.layout.fragment_room_data_base, container, false)
        mContainerId = container?.id?:-1

        //toolbar.title = "Room DB List"
        return roomDBView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.lifecycle.addObserver(viewModel)
        initToolBar(view)
        view.roomList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        view.roomList.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        fetchDataFromViewModel()

    }

    private fun fetchDataFromViewModel(){
       // viewModel.fetchRoomData()
        viewModel.userFinalList.observe(this,
            Observer<MutableList<UserInfo>> {
                    t -> println("Received UserInfo List $t")
                adapter.setList(t)
            }
        )
    }
    private fun initToolBar(view: View){
        val toolbar: Toolbar = view.findViewById(R.id.toolBar) as Toolbar
        setHasOptionsMenu(true)
        //set toolbar appearance
        //set toolbar appearance
        toolbar.setBackgroundColor(Color.CYAN)
        var toolBarTitle : TextView = toolbar.findViewById(R.id.toolbar_title)
        toolBarTitle.text = "RoomDBList"
        //for crate home button
        //for crate home button
        val activity = activity as AppCompatActivity?
        activity?.setSupportActionBar(toolbar)
        activity?.let{
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            activity.supportActionBar?.title = ""
        }

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.getMenuInflater()?.inflate(R.menu.menu, menu);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        return if (id == R.id.action_settings) {
            Toast.makeText(activity,"Clicked on Add",Toast.LENGTH_LONG).show()
            var addUserFragment = AddUserFragment()
            activity?.replaceFragment(addUserFragment,mContainerId)
            true
        } else return super.onOptionsItemSelected(item)


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RoomDataBaseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RoomDataBaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}