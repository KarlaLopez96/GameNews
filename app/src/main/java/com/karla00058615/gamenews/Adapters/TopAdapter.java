package com.karla00058615.gamenews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.activities.MainActivity;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.classes.Player;
import com.karla00058615.gamenews.fragments.InfoFragment;
import com.karla00058615.gamenews.fragments.InfoPlayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Karla on 8/6/2018.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>{

    Context context;
    ArrayList<Player> topArrayList;
    View view;
    Activity activity;
    int cont = 0;

    public TopAdapter(Context context, ArrayList<Player> topArrayList, Activity activity) {
        this.context = context;
        this.topArrayList = topArrayList;
        this.activity = activity;
    }

    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.top_layout, parent, false);

        return new TopAdapter.ViewHolder(v,context, topArrayList);
    }

    @Override
    public void onBindViewHolder(final TopAdapter.ViewHolder holder, final int position) {
        holder.titleTxtView.setText(topArrayList.get(position).getName());
        Picasso.with(context).load(topArrayList.get(position).getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return topArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView titleTxtView;
        ArrayList<Player> Top = new ArrayList<>();
        Context ctx;

        public ViewHolder(View itemView, Context ctx, ArrayList<Player> top) {
            super(itemView);
            this.Top = top;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            img = itemView.findViewById(R.id.PlayerAvatar);
            titleTxtView = itemView.findViewById(R.id.topName);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            //Maneja los fragmentos.
            FragmentManager fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();

            //Crea una nueva trasacción.
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            InfoPlayer fragment = InfoPlayer.newInstance(Top.get(position));

            transaction.replace(R.id.Fragment, fragment);

            //Realizando cambios.
            transaction.commit();
        }
    }
}

