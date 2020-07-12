package com.example.popularmoivestage1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.popularmoivestage1.model.Movie;
import com.example.popularmoivestage1.utils.JSONUtils;
import com.squareup.picasso.Picasso;

import org.w3c.dom.ls.LSOutput;

import java.io.Serializable;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> implements Serializable {

    private Movie[] movies;
    private Context context;

    public MovieAdapter( Context context){
        this.context = context;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, Serializable {

        public ImageView imageView;
        public MovieAdapterViewHolder( View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view) ;
            imageView.setClickable(true);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(v.getContext(), MovieDetails.class);
             int currentMoviePosition = getAdapterPosition();
             Movie currentMovie = movies[currentMoviePosition];
            intent.putExtra("MyClass", (Serializable) currentMovie);
            v.getContext().startActivity(intent);
        }
    }


    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context currentContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(currentContext);
        boolean shouldAttachToParentImmediatly = false;

        View view = inflater.inflate(R.layout.movie_list_item,parent,shouldAttachToParentImmediatly);
        MovieAdapterViewHolder holder = new MovieAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( MovieAdapterViewHolder holder, int position) {

        Picasso.get()
                .load(movies[position].getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (movies != null )
            return movies.length;
        return 0;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }


}
