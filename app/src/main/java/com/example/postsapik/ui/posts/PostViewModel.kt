package com.example.postsapik.ui.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.postsapik.data.model.PostResponseItem
import com.example.postsapik.data.source.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostViewModel : ViewModel() {
    private val _postsLiveData = MutableLiveData<List<PostResponseItem>>()
    val postsLiveData get() = _postsLiveData

    private val _messageLiveData = MutableLiveData<String>()
    val messageLiveData get() = _messageLiveData

    fun getPosts() {
        RetrofitClient.getService()?.getPosts()?.enqueue(object: Callback<List<PostResponseItem>> {
            override fun onResponse(
                    call: Call<List<PostResponseItem>>?,
                    response: Response<List<PostResponseItem>>?
                    ) {
                response?.let { postResponse ->
                    if (postResponse.isSuccessful)
                        _postsLiveData.value = postResponse.body()
                }
            }
            override fun onFailure (call: Call<List<PostResponseItem>>?, t: Throwable?) {
                t?.let {
                    _messageLiveData.value = t.localizedMessage
                }

            }

        })
    }
}