package com.example.swetry;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<DataClass> dataList;
    private boolean resizeItems;

    public MyAdapter(Context context, List<DataClass> dataList, boolean resizeItems){
        this.context = context;
        this.dataList = dataList;
        this.resizeItems = resizeItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        if (resizeItems) {
            int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
            int itemWidth = screenWidth / 2;  // Adjust this value as needed
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(itemWidth, RecyclerView.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass movie = dataList.get(position);
        holder.recTitle.setText(movie.getTitle());
        holder.recDesc.setText(movie.getRating());
        Picasso.get().load(movie.getPosterUrl()).into(holder.recImage); // Using Picasso to load images

        holder.itemView.setOnClickListener(v -> {
            Log.d("MyAdapter", "Movie: " + movie.getTitle() + ", Genres: " + movie.getGenre());

//            Intent intent = new Intent(context, DetailActivity.class);
//            intent.putExtra("MovieData", movie);
//            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView recImage;
        TextView recTitle, recDesc;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recImage = itemView.findViewById(R.id.recImage);
            recTitle = itemView.findViewById(R.id.recTitle);
            recDesc = itemView.findViewById(R.id.recDesc);
        }
    }

    public void setSearchList(List<DataClass> dataSearchList) {
        this.dataList = dataSearchList;
        notifyDataSetChanged();

    }



}
