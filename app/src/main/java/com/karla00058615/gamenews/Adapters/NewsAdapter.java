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
import com.karla00058615.gamenews.fragments.InfoFragment;
import com.karla00058615.gamenews.interfaces.ComunicationIF;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Karla on 5/6/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    boolean flag = false;
    Context context;
    ArrayList<New> newArrayList;
    ArrayList<New> favorits;
    View view;
    Activity activity;
    ComunicationIF CF;
    int cont = 0;

    public NewsAdapter(Context context, ArrayList<New> newArrayList,ArrayList<New> favorits, Activity activity) {
        this.context = context;
        this.favorits = favorits;
        this.newArrayList = newArrayList;
        this.activity = activity;
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.news_card_view, parent, false);
        CF = (ComunicationIF) activity;
        return new ViewHolder(v,context, newArrayList);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        for (int i = 0; i<favorits.size();i++){
            if (favorits.get(i).get_id().equals(newArrayList.get(position).get_id())){
                flag = true;
            }
        }
        if (flag){
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo1));
            flag=false;
        }else {
            holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo));
        }
        holder.titleTxtView.setText(newArrayList.get(position).getTitle());
        holder.descriptionTxtView.setText(newArrayList.get(position).getDescription());

        //Obteniendo las imágenes.
        Picasso.with(context).load(newArrayList.get(position).getCoverImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).into(holder.img);

        //listener de cada imageButton con forma de estrella
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<favorits.size();i++){
                    if (favorits.get(i).get_id().equals(newArrayList.get(position).get_id())){
                        flag = true;
                    }
                }
                if (flag){
                    //Removiendolo de la lista de favoritos.
                    CF.remove(newArrayList.get(position));
                    holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo));
                }else {
                    //Agregandolo a la lista de favoritos.
                    CF.addfav(newArrayList.get(position));

                    holder.fav.setImageDrawable(context.getResources().getDrawable(R.drawable.favo1));
                }
                flag = false;
            }});
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView img;
        TextView titleTxtView,descriptionTxtView;
        ArrayList<New> News = new ArrayList<>();
        Context ctx;
        ImageView fav;

        public ViewHolder(View itemView, Context ctx, ArrayList<New> contactos) {
            super(itemView);
            this.News = contactos;
            this.ctx = ctx;
            itemView.setOnClickListener(this);
            img = itemView.findViewById(R.id.new_image);
            titleTxtView = itemView.findViewById(R.id.newTitle);
            descriptionTxtView = itemView.findViewById(R.id.newDescription);
            fav = itemView.findViewById(R.id.favIcon);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            //Maneja los fragmentos.
            FragmentManager fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();

            //Crea una nueva trasacción.
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            InfoFragment fragment = InfoFragment.newInstance(News.get(position));

            transaction.replace(R.id.Fragment, fragment);

            //Realizando cambios.
            transaction.commit();
        }
    }
}
