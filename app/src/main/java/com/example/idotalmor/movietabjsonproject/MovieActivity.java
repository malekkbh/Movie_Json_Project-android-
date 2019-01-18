package com.example.idotalmor.movietabjsonproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.idotalmor.movietabjsonproject.Adapter.MoviePropertiesAdapter;
import com.example.idotalmor.movietabjsonproject.Models.Movie;
import com.example.idotalmor.movietabjsonproject.Models.MovieProperty;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    LinearLayout dotSlider;
    Movie movie;
    String [] imagesStringUrlArray;
    ListView properties_listview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_activity_layout);

        dotSlider = findViewById(R.id.dotSlider);
        properties_listview = findViewById(R.id.movie_property_listview);

        //get bundle for movie object
        Bundle bundle = getIntent().getExtras();
        String type = bundle.getString("Type");
        int position = bundle.getInt("Movie");

        if(type.equals("movie")){
            movie = MainActivity.movies.get(position);
        }else {
            movie = MainActivity.series.get(position);
        }

        setTitle(movie.title);//set activity title as movie title
        imagesStringUrlArray = movie.images;


        //creating dots images array with images url length
        final ImageView [] dotsImagesArray = new ImageView[imagesStringUrlArray.length];

        //for loop to initiate all imageView dots objects
        for(int i=0;i<dotsImagesArray.length;i++){
        dotsImagesArray[i] = new ImageView(this);
        dotsImagesArray[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(8, 0, 8, 0);
        dotSlider.addView(dotsImagesArray[i],params);}

        //the first dot is the active when creating the activity
        dotsImagesArray[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));


        //view pager for images
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImagePagerAdapter adapter = new ImagePagerAdapter();
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                //update dots to sync with the current image position
                for(int j = 0;j<dotsImagesArray.length;j++){
                    dotsImagesArray[j].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dotsImagesArray[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        //handeling listview & properties

        //creating linkedhashmap for the desire property we want to show(by order)
        List<MovieProperty> showen_propoerties = new ArrayList<MovieProperty>();
       // Map<String,String> showen_propoerties = new LinkedHashMap<String,String>();
        showen_propoerties.add(new MovieProperty("Plot",movie.plot));
        showen_propoerties.add(new MovieProperty("Year",String.valueOf(movie.year)));
        showen_propoerties.add(new MovieProperty("Runtime",movie.runtime));
        showen_propoerties.add(new MovieProperty("Director",movie.director));

        String[] actorsArray = movie.actors;
        String actors = "";
        for(int i=0;i<actorsArray.length;i++){
            actors += actorsArray[i];
        }
        showen_propoerties.add(new MovieProperty("Actors",actors));
        showen_propoerties.add(new MovieProperty("Language",movie.language));
        showen_propoerties.add(new MovieProperty("Awards",movie.awards));
        showen_propoerties.add(new MovieProperty("imdbID",movie.imdbID));

        properties_listview.setAdapter(new MoviePropertiesAdapter(MovieActivity.this,showen_propoerties));

    }

    //class for images horizontal scroll view
    private class ImagePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imagesStringUrlArray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Context context = MovieActivity.this;
            ImageView imageView = new ImageView(context);
            //set image from url array by position
            Picasso.get().load(Uri.parse(imagesStringUrlArray[position])).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
}


