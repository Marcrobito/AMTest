package com.marcrobito.amtest.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.marcrobito.amtest.R;
import com.marcrobito.amtest.pojos.MovieData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieJRecyclerViewAdapter extends RecyclerView.Adapter<MovieJRecyclerViewAdapter.MyViewHolder> implements OnItemClickListenerJ {

    private List<MovieData> list;

    private OnItemClickListenerJ listener;

    public MovieJRecyclerViewAdapter(List<MovieData> list, OnItemClickListenerJ listener){
        this.list = list;
        this.listener = listener;
    }

    @Override
    public MovieJRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view_holder, parent, false);
        return new MyViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {
        holder.bind(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(Integer position) {
        listener.onItemClick(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.imageView)
        @Nullable ImageView imageView;

        OnItemClickListenerJ listener;

        View itemView;

        MyViewHolder(@NonNull View itemView, OnItemClickListenerJ listener) {
            super(itemView);

            this.listener = listener;

            this.itemView = itemView;
            ButterKnife.bind( this, this.itemView);


        }

        void bind(MovieData movieData){
            itemView.setOnClickListener(this);
            itemView.setId(movieData.getId());

            Glide.with(itemView).load("https://image.tmdb.org/t/p/w1280" + movieData.getPosterUrl()).into(imageView);

        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }

    }
}


