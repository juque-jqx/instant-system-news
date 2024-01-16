package com.example.news.usecase

import androidx.paging.PagingData
import com.aliahmed.data.database.entity.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetNewsUseCase {
    suspend fun fetchNewsList(
        coroutineScope: CoroutineScope,
    ): Flow<PagingData<Article>>
}