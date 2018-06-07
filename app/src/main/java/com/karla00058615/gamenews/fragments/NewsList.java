package com.karla00058615.gamenews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.gamenews.Adapters.Adapter;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsList extends Fragment {

    ArrayList<New> news;
    RecyclerView recyclerView;


    public NewsList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        Bundle bundle = getArguments();
        news = bundle.getParcelableArrayList("News");

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        Adapter adapter = new Adapter(getContext(),news,getActivity());
        recyclerView.setAdapter(adapter);

        //Creando el manager que manejará el formato de las noticias.
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        return v;
    }

}
