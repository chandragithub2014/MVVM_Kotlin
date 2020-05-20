package com.mvvm.cleanarchitecture.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.mvvm.appnavigator.hideKeyboard
import com.mvvm.cleanarchitecture.R
import com.mvvm.cleanarchitecture.framework.PostViewModel
import com.mvvmcore.data.PostModel
import kotlinx.android.synthetic.main.fragment_post.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var postId:Long  = 0L

    private lateinit var viewModel:PostViewModel
    private var currentPost = PostModel("","",creationTime = 0L,updateTime = 0L)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        arguments?.let {
            postId = PostFragmentArgs.fromBundle(it).postId
        }
        if(postId != 0L){
            viewModel.getPost(postId)
        }
        checkButton.setOnClickListener {
            if(titleView.text.toString() != "" || contentView.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentPost.title = titleView.text.toString()
                currentPost.body =  contentView.text.toString()
                currentPost.updateTime = time
                if(currentPost.id == 0L){
                    currentPost.creationTime = time
                }
                viewModel.savePost(currentPost)
            }else{
                Navigation.findNavController(it).popBackStack()
            }

        }
        observeViewModel()
    }


    private fun observeViewModel(){
        viewModel.fetchSavedLiveData().observe(viewLifecycleOwner, Observer {

            if(it){
                Toast.makeText(context,"Save Done",Toast.LENGTH_LONG).show()
                titleView.hideKeyboard()
                Navigation.findNavController(titleView).popBackStack()
            }else{
                Toast.makeText(context,"Save Failed Try Again !!!!",Toast.LENGTH_LONG).show()
            }
        })

        viewModel.fetchCurrentPostLiveData().observe(viewLifecycleOwner, Observer { post ->
            post?.let {
                currentPost = it
                titleView.setText(it.title,TextView.BufferType.EDITABLE)
                contentView.setText(it.body,TextView.BufferType.EDITABLE)

            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote -> {
                if(context != null && postId != 0L){
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete ?")
                        .setPositiveButton("Yes"){dialog: DialogInterface?, which: Int ->  viewModel.deletePost(currentPost)}
                        .setNegativeButton("No"){dialog: DialogInterface?, which: Int ->  }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}