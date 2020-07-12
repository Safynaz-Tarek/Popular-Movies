package com.example.popularmoivestage1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmoivestage1.model.Movie;
import com.example.popularmoivestage1.utils.JSONUtils;
import com.example.popularmoivestage1.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private TextView errorMessage;
    private ProgressBar progressBar;

    private final String POPULAR_QUERY = "popular";
    private final String TOP_RATED_QUERY = "top_rated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        errorMessage = (TextView) findViewById(R.id.tv_error_message_display);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2);
        movieAdapter = new MovieAdapter(getApplicationContext());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(movieAdapter);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMovieData();

    }
    private void loadMovieData(){
        if(isOnline()){
            // i chose to load the popular query first as a defualt
           new FetchMovieData().execute(POPULAR_QUERY);
        }else{
            showErrorMessage();
        }
    }
    private void showMovieData() {
        errorMessage.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort_popular) {
            new FetchMovieData().execute(POPULAR_QUERY);
            return true;
        }else if(id == R.id.action_sort_top_rated){
            new FetchMovieData().execute(TOP_RATED_QUERY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
// Code to check if there is access to the internet or not
// https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public class FetchMovieData extends AsyncTask<String, Void, Movie[]>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... strings) {
            String movieResultsJson = null;

            try {
                URL url = NetworkUtils.buildUrl(strings[0]);
                movieResultsJson = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                return null;
            }

            return JSONUtils.parseMovieJson(movieResultsJson);
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            progressBar.setVisibility(View.INVISIBLE);
            if (movies != null) {
                movieAdapter.setMovies(movies);
                showMovieData();

            } else {
                showErrorMessage();
            }
        }

    }
}

