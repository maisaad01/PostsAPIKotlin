package com.example.postsapik.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postsapik.R
import com.example.postsapik.databinding.FragmentPostsBinding
import com.example.postsapik.ui.posts.adapter.PostsAdapter


class PostsFragment : Fragment() {
    private var _binding : FragmentPostsBinding?=null
    private val binding get() = _binding!!
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var postViewModel: PostViewModel
    private fun setUp ()
    {
        postsAdapter = PostsAdapter()
        binding.RecyclerViewPosts.apply {
            adapter = postsAdapter
            layoutManager = LinearLayoutManager (requireContext())
        }
    }

    private fun observe (){
        postViewModel.postsLiveData.observe(viewLifecycleOwner){posts ->
            postsAdapter?.addPosts(posts)
        }
        postViewModel.postsLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(),""+it,Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.getPosts()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding =FragmentPostsBinding.bind(view)
        setUp()
        observe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}