package com.karla00058615.gamenews.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.gamenews.Adapters.ViewPagerAdapter;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;

import java.util.ArrayList;

public class ManagerFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<New> news;
    private ArrayList<Player> top;
    private OnFragmentInteractionListener mListener;

    public ManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflando el layout para el fragment_manager.
        View v = inflater.inflate(R.layout.fragment_manager, container, false);
        Bundle bundle = getArguments();
        news = bundle.getParcelableArrayList("News");
        top = bundle.getParcelableArrayList("Top");

        tabLayout = v.findViewById(R.id.tablayout_id);
        viewPager = v.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.AddFragments(NewsList.newInstance(news),"News");
        adapter.AddFragments(TopFragment.newInstance(top),"Top Players");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
