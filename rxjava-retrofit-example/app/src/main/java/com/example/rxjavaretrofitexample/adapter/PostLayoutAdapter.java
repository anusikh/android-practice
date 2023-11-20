package com.example.rxjavaretrofitexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaretrofitexample.R;
import com.example.rxjavaretrofitexample.holder.PostLayoutHolder;
import com.example.rxjavaretrofitexample.model.Posts;

import java.util.List;

public class PostLayoutAdapter extends RecyclerView.Adapter<PostLayoutHolder> {

    Context context;
    List<Posts> postsList;

    public PostLayoutAdapter(Context context, List<Posts> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostLayoutHolder(LayoutInflater.from(context).inflate(R.layout.post_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostLayoutHolder holder, int position) {
        holder.txt_author.setText(String.valueOf(postsList.get(position).getUserId()));
        holder.txt_title.setText(String.valueOf(postsList.get(position).getTitle()));
        holder.txt_content.setText(new StringBuilder(postsList.get(position).getBody().substring(0, 20))
                .append("...").toString());
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}
