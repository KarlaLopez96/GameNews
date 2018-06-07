package com.karla00058615.gamenews.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.gamenews.Adapters.Adapter;
import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;

import java.util.ArrayList;

public class ManagerFragment extends Fragment {

    ArrayList<New> news;
    RecyclerView recyclerView;

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

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        Adapter adapter = new Adapter(getContext(),news,getActivity());
        recyclerView.setAdapter(adapter);

        //Creando el manager que manejará el formato de las noticias.
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
