package com.example.news.view.paging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliahmed.core_ui.ErrorDialog
import com.aliahmed.core_ui.FullScreenLoading
import com.aliahmed.data.database.entity.Article
import com.example.core.networkhelper.NoInternetException
import com.example.news.R
import com.example.news.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreenPaging(
    newsViewModel: NewsViewModel,
    newsClicked: (Article) -> Unit
) {
    val pagingResponse = newsViewModel.newsItemPaging.collectAsLazyPagingItems()

    var isRefreshing by rememberSaveable { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            newsViewModel.getNews()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when (pagingResponse.loadState.refresh) {
            is LoadState.Loading -> {
                if (!isRefreshing)
                    FullScreenLoading()
            }

            is LoadState.Error -> {
                isRefreshing = false
                var errorText = stringResource(id = R.string.something_went_wrong)
                if ((pagingResponse.loadState.refresh as LoadState.Error).error is NoInternetException) {
                    errorText = stringResource(id = R.string.no_internet_available)
                }
                ErrorDialog(
                    message = errorText,
                    onRetry = {
                        newsViewModel.getNews()
                    }
                )
            }
            else -> {
                isRefreshing = false
                NewsPagingAppend(pagingResponse, newsClicked)
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }

}