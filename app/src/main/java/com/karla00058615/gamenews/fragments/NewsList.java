package com.karla00058615.gamenews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.gamenews.Adapters.NewsAdapter;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsList extends Fragment {

    ArrayList<New> news;
    ArrayList<New> favorits;
    RecyclerView recyclerView;
    ArrayList<Player> top;


    public NewsList() {

    }

    public static NewsList newInstance(ArrayList<New> news,ArrayList<New> favoritos) {
        NewsList fragmentFirst = new NewsList();
        Bundle args = new Bundle();
        args.putParcelableArrayList("News", news);
        args.putParcelableArrayList("Favorits",favoritos);
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
        favorits = bundle.getParcelableArrayList("Favorits");

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

        NewsAdapter adapter = new NewsAdapter(getContext(),news,favorits,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        return v;
    }

}
