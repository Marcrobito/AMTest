package com.marcrobito.amtest.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcrobito.amtest.R
import com.marcrobito.amtest.pojos.MovieData
import com.marcrobito.amtest.utils.MovieRecyclerViewAdapter
import com.marcrobito.amtest.utils.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import android.view.KeyEvent.KEYCODE_BACK
import android.view.KeyEvent
import android.content.Intent




class MainActivity : AppCompatActivity(), MainContract.View, OnItemClickListener, BackToMainInterface {

    private val presenter: MainContract.Presenter by inject()

    private var  movieList:List<MovieData> = listOf()
    private var movieDetailFragment: MovieDetailFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieDetailFragment = MovieDetailFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, movieDetailFragment!!).commit()

    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onItemClick(position: Int) {
        Log.d("tag",movieList[position].title)
        if(movieDetailFragment != null){
            fragment_container.visibility = View.VISIBLE
            movieDetailFragment!!.setMovie(movieList[position])
        }
    }



    override fun presentTopMovies(movieList: List<MovieData>) {

        this.movieList = movieList

        val adapter = MovieRecyclerViewAdapter(this.movieList, this)
        val manager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        progressBar.visibility = View.GONE




    }

    override fun onBackPressed() {
        if(fragment_container.visibility == View.VISIBLE)
            fragment_container.visibility = View.GONE

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KEYCODE_BACK && event.repeatCount == 0
        ) {
            Log.d("CDA", "onKeyDown Called")
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun backToMAin() {
        onBackPressed()
    }
}
