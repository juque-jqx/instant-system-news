package com.example.news.view.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.paging.compose.LazyPagingItems
import com.aliahmed.core_ui.ErrorDialog
import com.aliahmed.data.database.entity.Article
import com.example.news.R
import com.example.news.view.Article

@Composable
fun NewsPagingAppend(
    pagingResponse: LazyPagingItems<Article>,
    newsClicked: (Article) -> Unit,
) {
    LazyColumn {
        items(pagingResponse.itemCount) { index ->
            pagingResponse[index]?.let { article: Article ->
                Article(article) { clickedArticle ->
                    newsClicked(clickedArticle)
                }
            }
        }
        pagingResponse.apply {
            when (loadState.append) {
                is Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center),
                                strokeWidth = 1.dp
                            )
                        }
                    }
                }

                is Error -> {
                    item {
                        ErrorDialog(
                            message = stringResource(id = R.string.retry_on_pagination),
                            onRetry = {
                                pagingResponse.retry()
                            }
                        )
                    }
                }

                else -> {}
            }
        }
    }
}