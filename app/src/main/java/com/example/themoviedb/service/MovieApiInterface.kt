package com.example.themoviedb.service

import retrofit2.Call
import com.example.themoviedb.model.MovieResponse
import retrofit2.http.GET

interface MovieApiInterface {
    @GET("/3/movie/popular?api_key=e757b66e34e6d42f7432b96dde62ef7e")
    fun getMovieList(): Call<MovieResponse>
}