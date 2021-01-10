package com.example.minim2.models;

import android.content.Context;
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

    List<User> followerlist;
    Context context;

    public MyAdapter(Context ct, List<User> Users) {
        context =ct;
        followerlist=Users;
    }

    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);

    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameText.setText(followerlist.get(position).getLogin());
        Picasso.get().load(followerlist.get(position).getAvatar_url()).into(holder.myImage);

    }

    public int getItemCount() {
        return followerlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.followerName);
            myImage = (ImageView) itemView.findViewById(R.id.imageFollower);
        }
    }
}
