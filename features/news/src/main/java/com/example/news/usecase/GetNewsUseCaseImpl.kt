package com.example.news.usecase

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.aliahmed.data.database.entity.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val pager: Pager<Int, Article>,
) : GetNewsUseCase {

    override suspend fun fetchNewsList(
        coroutineScope: CoroutineScope,
    ): Flow<PagingData<Article>> {
        return pager.flow.cachedIn(coroutineScope)
            .map {
                it.filter { article ->
                    article.title?.isNotEmpty() == true &&
                        article.urlToImage?.isNotEmpty() == true
                }
            }
    }

}