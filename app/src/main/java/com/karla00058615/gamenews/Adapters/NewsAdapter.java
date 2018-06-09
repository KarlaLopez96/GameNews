package com.karla00058615.gamenews.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karla00058615.gamenews.R;
import com.karla00058615.gamenews.classes.New;
import com.karla00058615.gamenews.interfaces.ComunicationIF;

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
        holder.titleTxtView.setText(newArrayList.get(position).getTitle());
        //listener de cada imageButton con forma de estrella
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i<favorits.size();i++){
                    if (favorits.get(i).getId() == newArrayList.get(position).getId()){
                        flag = true;
                    }
                }
                if (flag){
                    CF.remove(newArrayList.get(position));
                }else {
                    CF.addfav(newArrayList.get(position));
                }
                flag = false;
            }});
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titleTxtView,descriptionTxtView;
        ArrayList<New> News = new ArrayList<>();
        Context ctx;
        ImageView fav;

        public ViewHolder(View itemView, Context ctx, ArrayList<New> contactos) {
            super(itemView);
            this.News = contactos;
            this.ctx = ctx;
            //itemView.setOnClickListener(this);
            titleTxtView = itemView.findViewById(R.id.newTitle);
            descriptionTxtView = itemView.findViewById(R.id.newDescription);
            fav = itemView.findViewById(R.id.favIcon);
        }

    }
}
