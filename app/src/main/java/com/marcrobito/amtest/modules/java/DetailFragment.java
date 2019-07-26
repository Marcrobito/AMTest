package com.marcrobito.amtest.modules.java;


import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.marcrobito.amtest.R;
import com.marcrobito.amtest.pojos.MovieData;
import com.marcrobito.amtest.utils.Build;
import kotlin.jvm.Throws;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {



    public DetailFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.poster)
    ImageView poster;

    @BindView(R.id.movieTitle)
    TextView movieTitle;

    @BindView(R.id.overview)
    TextView overview;
    BackToMainInterfaceJ interfaceJ;

    @OnClick(R.id.backToParent)
    public void submit(View view){
        interfaceJ.backToMain();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BackToMainInterfaceJ){
            this.interfaceJ = (BackToMainInterfaceJ)context;
        }else{
            try {
                throw new MyOwnException("You must implement BackToMainInterface");
            } catch (MyOwnException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    public void setMovie(MovieData movie){
        Glide.with(getActivity())
                .load("https://image.tmdb.org/t/p/w1280" + movie.getBackdropPath())
                .into(poster);
        movieTitle.setText(movie.getTitle());
        overview.setText(movie.getOverview());
    }

}
class MyOwnException extends Exception {
    public MyOwnException(String msg){
        super(msg);
    }
}
