package com.example.newsjetpack.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsjetpack.R
import com.example.newsjetpack.data.entity.Article
import com.example.newsjetpack.data.entity.NewsResponse
import com.example.newsjetpack.ui.theme.Purple40

@Composable
fun Loader() {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp).padding(10.dp), color = Purple40
        )
    }
}

@Composable
fun NewsList(response: NewsResponse) {
    LazyColumn {
        items(response.articles) {
            NormalText(textVal = it.title)
        }
    }
}

@Composable
fun NormalText(textVal: String) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(10.dp).wrapContentHeight(), text = textVal, style = TextStyle(
            fontSize = 18.sp, fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun NewsRowComponent(page: Int, article: Article) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp).background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(240.dp),
            model = article.urlToImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.dog),
            error = painterResource(R.drawable.dog)
        )

        Spacer(modifier = Modifier.size(20.dp))
        NormalText(textVal = article.title?:"")

        Spacer(modifier = Modifier.size(10.dp))
        NormalText(textVal = article.content?:"")
    }
}
