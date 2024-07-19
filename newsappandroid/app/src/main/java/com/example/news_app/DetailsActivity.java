package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_app.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView text_detail_title, text_detail_author, text_detail_time, text_detail_detail, text_detail_content;
    ImageView img_detail_news;
    NewsHeadlines newsHeadlines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        text_detail_title = findViewById(R.id.text_detail_title);
        text_detail_author = findViewById(R.id.text_detail_author);
        text_detail_time = findViewById(R.id.text_detail_time);
        text_detail_detail = findViewById(R.id.text_detail_detail);
        text_detail_content = findViewById(R.id.text_detail_content);
        img_detail_news = findViewById(R.id.img_detail_news);

        newsHeadlines = (NewsHeadlines) getIntent().getSerializableExtra("data");

        text_detail_title.setText(newsHeadlines.getTitle());
        text_detail_author.setText(newsHeadlines.getAuthor());
        text_detail_time.setText(newsHeadlines.getPublishedAt());
        text_detail_detail.setText(newsHeadlines.getDescription());
        text_detail_content.setText(newsHeadlines.getContent());
        Picasso.get().load(newsHeadlines.getUrlToImage()).into(img_detail_news);

    }
}