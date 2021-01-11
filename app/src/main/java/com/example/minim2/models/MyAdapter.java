package com.example.minim2.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minim2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Repo> reposList;
    Context context;

    public MyAdapter(Context ct, List<Repo> reposList) {
        context =ct;
        this.reposList=reposList;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_repos_row,parent,false);
        return new MyViewHolder(view);

    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textNameRepo.setText(reposList.get(position).getName());
        holder.textLanguageName.setText(reposList.get(position).getLanguage());

    }

    public int getItemCount() {
        return reposList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textNameRepo;
        TextView textLanguageName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNameRepo = (TextView) itemView.findViewById(R.id.textNameRepo);
            textLanguageName = (TextView) itemView.findViewById(R.id.textNameLang);

        }
    }
}
