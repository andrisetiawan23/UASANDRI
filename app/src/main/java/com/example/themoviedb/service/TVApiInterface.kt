package com.example.themoviedb.service

import retrofit2.Call
import com.example.themoviedb.model.TVResponse
import retrofit2.http.GET

interface TVApiInterface {
    @GET("/3/tv/popular?api_key=e757b66e34e6d42f7432b96dde62ef7e")
    fun getTVList(): Call<TVResponse>
}