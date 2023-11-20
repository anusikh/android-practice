package com.example.rxjavaretrofitexample.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaretrofitexample.R;

public class PostLayoutHolder extends RecyclerView.ViewHolder {

    public TextView txt_title, txt_content, txt_author;

    public PostLayoutHolder(@NonNull View itemView) {
        super(itemView);

        txt_title = itemView.findViewById(R.id.txt_title);
        txt_content = itemView.findViewById(R.id.txt_content);
        txt_author = itemView.findViewById(R.id.txt_author);
    }
}
