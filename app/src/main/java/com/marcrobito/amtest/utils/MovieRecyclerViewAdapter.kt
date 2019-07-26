package com.marcrobito.amtest.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcrobito.amtest.pojos.MovieData
import kotlinx.android.synthetic.main.movie_view_holder.view.*


class MovieRecyclerViewAdapter(private val items:List<MovieData>, private val listener: OnItemClickListener):RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>(), OnItemClickListener {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent, this)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onItemClick(position: Int) {
        Log.d("clicked", position.toString())
        listener.onItemClick(position)
    }

    class ViewHolder(parent: ViewGroup, private val listener: OnItemClickListener) :
         RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
             .inflate(com.marcrobito.amtest.R.layout.movie_view_holder, parent, false)), View.OnClickListener {

        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition )
        }

        //val animation = Animator




        fun bind(item:MovieData) = with(itemView){
            itemView.setOnClickListener(this@ViewHolder)
            itemView.id = item.id


            Glide.with(context).load(Build().BASE_IMAGE_URL+item.posterUrl).into(imageView)

            //imageView.startAnimation()


        }


     }
}

interface OnItemClickListener {
    fun onItemClick(position:Int)
}