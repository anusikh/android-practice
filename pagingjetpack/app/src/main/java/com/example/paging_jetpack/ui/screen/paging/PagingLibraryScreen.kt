package com.example.paging_jetpack.ui.screen.paging

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun PagingLibraryScreen() {
    val viewModel = hiltViewModel<PagingViewModel>()
    val context = LocalContext.current


    var articles = viewModel.getBreakingNews().collectAsLazyPagingItems()

    // https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary
    LazyColumn {
        items(count = articles.itemCount) { index ->
            val item = articles[index]
            Text(
                modifier = Modifier
                    .height(75.dp),
                text = item?.title ?: "",
            )
            Divider()
        }

        when (val state = articles.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show()
                // do something
                // state.error
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "refresh loading", modifier = Modifier.padding(8.dp))
                    }
                }
            }

            else -> {}
        }

        when (val state = articles.loadState.append) {
            is LoadState.Error -> {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT).show()
                // do something
                // state.error
            }

            is LoadState.Loading -> {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "pagination loading")
                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }
}
