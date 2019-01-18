package com.example.idotalmor.movietabjsonproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.idotalmor.movietabjsonproject.Models.MovieProperty;
import com.example.idotalmor.movietabjsonproject.R;
import java.util.List;

public class MoviePropertiesAdapter extends BaseAdapter {

    Context context;
    List<MovieProperty> movieProperties;

    public MoviePropertiesAdapter(Context context,List<MovieProperty> movieProperties){

        this.context = context;
        this.movieProperties = movieProperties;

    }

    @Override
    public int getCount() {
        return movieProperties.size();
    }

    @Override
    public MovieProperty getItem(int position) {

        return movieProperties.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MovieProperty movieProperty = getItem(position);

        if (convertView == null){

            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.movie_property_layout, null);
        }

        TextView title = convertView.findViewById(R.id.movie_property_title);
        TextView value = convertView.findViewById(R.id.movie_property_value);
        ImageView imdb_image = convertView.findViewById(R.id.movie_property_imdbImg);

        if(movieProperty.title.equals("imdbID")){

            title.setVisibility(View.GONE);
            value.setVisibility(View.GONE);

            //define image on click and visibility
            imdb_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://www.imdb.com/title/"+movieProperty.value;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(browserIntent);
                }
            });

            imdb_image.setVisibility(View.VISIBLE);
        }else{

            title.setVisibility(View.VISIBLE);
            value.setVisibility(View.VISIBLE);
            imdb_image.setVisibility(View.GONE);

            title.setText(movieProperty.title);
            value.setText(movieProperty.value);

        }

        return convertView;
    }
}
