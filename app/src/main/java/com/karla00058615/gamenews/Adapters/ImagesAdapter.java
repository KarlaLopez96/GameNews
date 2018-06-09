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

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{

    Context context;
    ArrayList<String> ImageArrayList;
    View view;
    Activity activity;
    int cont = 0;

    public ImagesAdapter(Context context, ArrayList<String> ImagesArrayList, Activity activity) {
        this.context = context;
        this.ImageArrayList = ImagesArrayList;
        this.activity = activity;
    }

    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.images_layout, parent, false);

        return new ViewHolder(v,context, ImageArrayList);
    }

    @Override
    public void onBindViewHolder(final ImagesAdapter.ViewHolder holder, final int position) {
        holder.titleTxtView.setText(ImageArrayList.get(position));
        //listener de cada imageButton con forma de estrella
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }});
    }

    @Override
    public int getItemCount() {
        return ImageArrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView titleTxtView,descriptionTxtView;
        ArrayList<String> Image = new ArrayList<>();
        Context ctx;
        ImageView fav;

        public ViewHolder(View itemView, Context ctx, ArrayList<String> imagenes) {
            super(itemView);
            this.Image = imagenes;
            this.ctx = ctx;
            //itemView.setOnClickListener(this);
            titleTxtView = itemView.findViewById(R.id.newTitle);
            descriptionTxtView = itemView.findViewById(R.id.newDescription);
            fav = itemView.findViewById(R.id.favIcon);
        }

    }
}

