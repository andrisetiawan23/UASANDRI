package com.example.themoviedb

import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.model.Movie
import com.example.themoviedb.model.MovieResponse
import com.example.themoviedb.service.MovieApiInterface
import com.example.themoviedb.service.MovieApiService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MovieFragment : Fragment() {
    private val movies = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movies_list.layoutManager = LinearLayoutManager(this.context)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }
        showRecyclerView()
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable){
            }
            override fun onResponse(call: Call<MovieResponse>, response:Response<MovieResponse>){
                return callback(response.body()!!.movies)
            }
        })
    }

    private fun showRecyclerView(){
        rv_movies_list.LayoutManager = LinearLayoutManager(this.context)
        rv_movies_list.adapter = MovieAdapter(movies)
    }
}