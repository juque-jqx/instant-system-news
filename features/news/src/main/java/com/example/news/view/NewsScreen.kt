package com.example.news.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.aliahmed.core_ui.ErrorDialog
import com.aliahmed.core_ui.FullScreenLoading
import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.model.Day
import com.aliahmed.data.model.NewsListResponse
import com.aliahmed.data.network.Resource
import com.example.core.networkhelper.NoInternetException
import com.example.news.R
import com.example.news.usecase.GetNewsUseCaseImpl
import com.example.news.view.paging.NewsScreenPaging
import com.example.news.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    navController: NavController,
    viewModel: NewsViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getNews()
    }

    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    when (val pageState = state) {
        is Resource.Loading -> {
            FullScreenLoading()
        }

        is Resource.Error -> {
            ErrorDialog(message = pageState.message, {})
        }

        is Resource.Success -> {
            NewsScreenPaging(
                newsClicked = {article ->
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(article.url)
                    }
                    context.startActivity(intent)
                },
                newsViewModel = viewModel
            )
        }

        else -> {}
    }

}