package com.example.postsapik.data.source.remote

import com.example.postsapik.data.model.PostResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET ("posts")
    fun getPosts():Call<List<PostResponseItem>>
}