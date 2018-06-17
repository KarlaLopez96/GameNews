package com.karla00058615.gamenews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    TextView txt1,txt2,txt3,txt4;
    ImageView img;

    public static InfoFragment newInstance(New news) {
        InfoFragment fragmentFirst = new InfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("New", news);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }


    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        txt1 = v.findViewById(R.id.InfoTitle);
        img = v.findViewById(R.id.InfoImage);

        New news = getArguments().getParcelable("New");

        txt1.setText("Title: "+news.getTitle()+"\n\nBody:"+news.getBody()+"\n\nDescription: "+
        news.getDescription()+"\n\nCreated Date: "+news.getCreated_date());
        txt1.setMovementMethod(new ScrollingMovementMethod());
        Picasso.with(getContext()).load(news.getCoverImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(img);
        return v;
    }

}
