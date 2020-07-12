package com.example.popularmoivestage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popularmoivestage1.model.Movie;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;

public class MovieDetails extends AppCompatActivity implements Serializable {


    private ImageView imageView;
    private TextView mDescription;
    private TextView mRatings;
    private TextView mDate;
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mDescription = (TextView) findViewById(R.id.description_tv);
        mRatings = (TextView) findViewById(R.id.rating_tv);
        mDate = (TextView) findViewById(R.id.date_TV);
        imageView = (ImageView) findViewById(R.id.imagePoster_iv);

        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("MyClass");

        if (intent == null) {
            closeOnError();
        }

        if (movie == null) {
            closeOnError();
            return;
        }

        populateUI(movie);
        Picasso.get()
                .load(movie.getImage())
                .into(imageView);

        setTitle(movie.getMovieTitle());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Movie movie) {
        mRatings.setText(Double.toString(movie.getRating()));
        mDescription.setText(movie.getPlotOverview());
        mDate.setText(movie.getDate());
    }
}
