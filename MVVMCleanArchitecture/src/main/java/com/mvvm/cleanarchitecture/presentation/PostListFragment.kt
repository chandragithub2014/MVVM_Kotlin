package com.mvvm.cleanarchitecture.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.cleanarchitecture.R
import com.mvvm.cleanarchitecture.framework.PostListViewModel
import com.mvvm.cleanarchitecture.framework.PostViewModel
import com.mvvmcore.data.PostModel
import kotlinx.android.synthetic.main.fragment_post_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostListFragment : Fragment(),ListClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val postListAdapter:PostListAdapter = PostListAdapter(arrayListOf(),this)
    private lateinit var viewModel : PostListViewModel
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
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = postListAdapter
        }
        addPost.setOnClickListener {
            goToPostDetails()
        }

        viewModel =  ViewModelProviders.of(this).get(PostListViewModel::class.java)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPostsFromDB()
    }

    private fun observeViewModel(){
        viewModel.fetchPostsList().observe(viewLifecycleOwner, Observer { postLists ->
            postListAdapter.updatePosts(postLists.sortedByDescending { it.updateTime })
        })

        viewModel.fetchProgress().observe(viewLifecycleOwner, Observer {
            if(!it){
                loadingView.visibility = View.GONE
            }
        })



    }
    private fun goToPostDetails(postId: Long = 0L){
        val action:NavDirections = PostListFragmentDirections.actionPostListFragmentToPostFragment(postId)
        Navigation.findNavController(postListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToPostDetails(id)
    }
}