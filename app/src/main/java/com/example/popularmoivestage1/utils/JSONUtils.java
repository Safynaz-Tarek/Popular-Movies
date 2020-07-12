package com.example.popularmoivestage1.utils;

import com.example.popularmoivestage1.model.Movie;


import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    public static Movie[] parseMovieJson(String json) {

        final String RESULTS = "results";
        final String MOVIE_TITLE = "original_title";
        final String IMAGE = "poster_path";
        final String DESCRIPTION = "overview";
        final String RATING = "vote_average";
        final String RELEASE_DATE = "release_date";

        JSONObject obj = null;
        JSONArray dataArray = null;
        Movie[] movies;

        try {
            obj = new JSONObject(json);
            dataArray = obj.getJSONArray(RESULTS);
            movies = new Movie[dataArray.length()];

            for (int i = 0; i < dataArray.length(); i++) {
                movies[i] = new Movie();
                JSONObject movieInfo = dataArray.getJSONObject(i);

                movies[i].setMovieTitle(movieInfo.getString(MOVIE_TITLE));
                movies[i].setImage(movieInfo.getString(IMAGE));
                movies[i].setPlotOverview(movieInfo.getString(DESCRIPTION));
                movies[i].setRating(movieInfo.getDouble(RATING));
                movies[i].setDate(movieInfo.getString(RELEASE_DATE));
            }
            }catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        return movies;
    }
}
