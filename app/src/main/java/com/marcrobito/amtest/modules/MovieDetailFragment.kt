package com.marcrobito.amtest.modules


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.marcrobito.amtest.R
import com.marcrobito.amtest.pojos.MovieData
import com.marcrobito.amtest.utils.Build
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*


class MovieDetailFragment : Fragment() {

    private var listener: BackToMainInterface? = null

    override fun onAttach(context: Context?) {
        if (context is BackToMainInterface)
            listener = context
        else
            throw Exception("You must implement BackToMainInterface")
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        v.backToParent.setOnClickListener{
            if(listener!=null)
                listener!!.backToMAin()
        }
        return v
    }

    fun setMovie(movie:MovieData){
        Glide.with(activity!!.baseContext)
            .load(Build().BASE_IMAGE_URL+movie.backdropPath).into(poster)
        movieTitle.text = movie.title + " (" + movie.releaseDate.take(4) + ")"
        overview.text = movie.overview
    }



}

interface BackToMainInterface{
    fun backToMAin()
}


