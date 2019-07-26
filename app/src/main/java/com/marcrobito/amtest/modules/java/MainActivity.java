package com.marcrobito.amtest.modules.java;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.marcrobito.amtest.AppJava;
import com.marcrobito.amtest.R;
import com.marcrobito.amtest.pojos.MovieData;
import com.marcrobito.amtest.utils.MovieJRecyclerViewAdapter;
import com.marcrobito.amtest.utils.OnItemClickListenerJ;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainJContract.View, OnItemClickListenerJ, BackToMainInterfaceJ {

    @Inject
    public MainJContract.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.fragment_container)
    View container;


    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    List<MovieData> movieData;
    DetailFragment detailFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppJava)getApplication()).getComponent().inject(this);
        setContentView(R.layout.activity_main);

        detailFragment = new DetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, detailFragment).commit();
        ButterKnife.bind(this);



    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0
        ) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if(container.getVisibility() == View.VISIBLE){
            container.setVisibility(View.GONE);
        }
    }

    @Override
    public void presentTopMovies(List<MovieData> movieList) {
        this.movieData = movieList;

        MovieJRecyclerViewAdapter adapter = new MovieJRecyclerViewAdapter(movieData, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Integer position) {
        container.setVisibility(View.VISIBLE);
        detailFragment.setMovie(movieData.get(position));
    }

    @Override
    public void backToMain() {
        onBackPressed();
    }


}
