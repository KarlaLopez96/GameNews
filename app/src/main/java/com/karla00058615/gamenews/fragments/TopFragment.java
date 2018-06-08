package com.karla00058615.gamenews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.gamenews.Adapters.NewsAdapter;
import com.karla00058615.gamenews.Adapters.TopAdapter;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;

import java.util.ArrayList;

public class TopFragment extends Fragment {

    private RecyclerView recyclerViewTop;
    ArrayList<Player> top;

    public TopFragment() {
        // Required empty public constructor
    }

    public static TopFragment newInstance(ArrayList<Player> top) {
        TopFragment fragment = new TopFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("Top", top);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);

        recyclerViewTop = v.findViewById(R.id.recyclerViewTop);

        Bundle bundle = getArguments();
        top = bundle.getParcelableArrayList("Top");

        TopAdapter adapter = new TopAdapter(getContext(),top,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewTop.setLayoutManager(linearLayoutManager);
        recyclerViewTop.setAdapter(adapter);
        return v;
    }

}
