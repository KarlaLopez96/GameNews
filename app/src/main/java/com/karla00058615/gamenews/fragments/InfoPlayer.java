package com.karla00058615.gamenews.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.Player;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoPlayer extends Fragment {

    TextView txt1,txt2;
    ImageView img;

    public static InfoPlayer newInstance(Player player) {
        InfoPlayer fragmentFirst = new InfoPlayer();
        Bundle args = new Bundle();
        args.putParcelable("Player", player);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public InfoPlayer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info_player, container, false);

        txt1 = v.findViewById(R.id.InfoPlayerName);
        txt2 = v.findViewById(R.id.InfoPlayerBiografia);
        img = v.findViewById(R.id.InfoPlayerImage);

        Player player = getArguments().getParcelable("Player");

        txt1.setText(player.getName());

        txt2.setText(player.getBiography());

        Picasso.with(getContext()).load(player.getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(img);

        return v;
    }

}
