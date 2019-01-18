package com.example.idotalmor.movietabjsonproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.idotalmor.movietabjsonproject.Adapter.viewAdapter;

public class MoviesTab extends Fragment {

    ListView listView;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_list_layout,container,false);
        listView = view.findViewById(R.id.moviesListView);

        context = view.getContext();
        listView.setAdapter(new viewAdapter(view.getContext(),MainActivity.movies));

        return view;
    }


}
