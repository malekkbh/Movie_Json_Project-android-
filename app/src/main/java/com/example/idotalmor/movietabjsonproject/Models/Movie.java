package com.example.idotalmor.movietabjsonproject.Models;

import org.json.JSONArray;
import org.json.JSONObject;

public class Movie {

    public String title,rated,released,runtime,director,plot,language,country,awards,poster,imdbID,type,imdbRating,year;
    public int metascore,imdbVotes;
    public String [] genre,writer,actors,images;
    public boolean comingSoon;


    public Movie(String title, String rated, String released, String runtime, String director, String plot, String language
    , String country, String awards, String poster, String imdbID, String type, String year, int metascore, int imdbVotes, String imdbRating,
                 String[] genre, String[] writer, String[] actors, String[] images, boolean comingSoon){

        this.title = title;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.director = director;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.imdbID = imdbID;
        this.type = type;
        this.year = year;
        this.metascore = metascore;
        this.imdbVotes = imdbVotes;
        this.imdbRating = imdbRating;
        this.genre = genre;
        this.writer = writer;
        this.actors = actors;
        this.images = images;
        this.comingSoon = comingSoon;

    }

    public static Movie CreateMovieFromJson(JSONObject jsonObject){

        String title,rated,released,runtime,director,plot,language,country,awards,poster,imdbID,type,imdbRating,year;
        int metascore,imdbVotes;
        String [] genre,writer,actors,images;
        boolean comingSoon;


            title = jsonObject.optString("Title");
            rated = jsonObject.optString("Rated");
            released = jsonObject.optString("Released");
            runtime = jsonObject.optString("Runtime");
            director = jsonObject.optString("Director");
            plot = jsonObject.optString("Plot");
            language = jsonObject.optString("Language");
            country = jsonObject.optString("Country");
            awards = jsonObject.optString("Awards");
            poster = jsonObject.optString("Poster");
            imdbID = jsonObject.optString("imdbID");
            type = jsonObject.optString("Type");
            year = jsonObject.optString("Year");
            metascore = jsonObject.optInt("Metascore",-1);
            String votestr = jsonObject.optString("imdbVotes");
            if(votestr.equals("N/A")){imdbVotes = -1;}else{
            imdbVotes = Integer.parseInt(votestr.replace(",",""));}
            imdbRating = jsonObject.optString("imdbRating");
            genre = jsonObject.optString("Genre").split(",");
            writer = jsonObject.optString("Writer").split(",");
            actors = jsonObject.optString("Actors").split(",");
            JSONArray ja =  jsonObject.optJSONArray("Images");
            images = new String[ja.length()];
            for (int i=0;i<ja.length();i++){
             images[i] = ja.optString(i);
            }
            comingSoon = jsonObject.optBoolean("ComingSoon");



        return new Movie(title,rated,released,runtime,director,plot,language,country,awards,poster,imdbID,type,year,metascore,imdbVotes,imdbRating,genre,writer,actors,images,comingSoon);
    }



}
