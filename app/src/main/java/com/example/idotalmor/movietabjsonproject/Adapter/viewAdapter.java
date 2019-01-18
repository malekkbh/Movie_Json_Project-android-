package com.example.idotalmor.movietabjsonproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.idotalmor.movietabjsonproject.Models.Movie;
import com.example.idotalmor.movietabjsonproject.MovieActivity;
import com.example.idotalmor.movietabjsonproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class viewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Movie> movies;

    public viewAdapter(Context context,ArrayList<Movie> movies){

        this.context = context;
        this.movies = movies;

    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Movie movie = getItem(position);

        if (convertView == null){

            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.movie_layout, null);
        }

        ImageView imageView = convertView.findViewById(R.id.movieImg);
        Uri uri = Uri.parse(movie.poster);
        Picasso.get().load(uri).into(imageView);

        TextView titleTextView = convertView.findViewById(R.id.movieTTL);
        TextView imdbRatingTextView = convertView.findViewById(R.id.imdbRating);
        TextView plotTextView = convertView.findViewById(R.id.plot);
        TextView genreTextView = convertView.findViewById(R.id.genre);

        titleTextView.setText(movie.title);
        imdbRatingTextView.setText(movie.imdbRating);
        plotTextView.setText(movie.plot);

        String combinedGenre = "";
        for(int i=0;i<movie.genre.length;i++){
            combinedGenre += movie.genre[i];
        }
        genreTextView.setText(combinedGenre);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MovieActivity.class);
                intent.putExtra("Movie",position);
                intent.putExtra("Type",movie.type);
                context.startActivity(intent);
            }
        });
        convertView.setClickable(true);



        return convertView;
    }
}
