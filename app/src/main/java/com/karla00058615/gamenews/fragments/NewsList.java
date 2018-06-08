package com.karla00058615.gamenews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

    }

    public static NewsList newInstance(ArrayList<New> news) {
        NewsList fragmentFirst = new NewsList();
        Bundle args = new Bundle();
        args.putParcelableArrayList("News", news);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        Bundle bundle = getArguments();
        news = bundle.getParcelableArrayList("News");

        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position % 3 == 0){
                    return 2;
                }else {
                    return 1;
                }
            }
        });

        Adapter adapter = new Adapter(getContext(),news,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        return v;
    }

}
