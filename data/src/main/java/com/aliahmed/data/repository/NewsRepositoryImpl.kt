package com.aliahmed.data.repository

import com.aliahmed.data.database.DatabaseService
import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.database.entity.apiArticleListToArticleList
import com.aliahmed.data.model.News
import com.aliahmed.data.model.NewsListResponse
import com.aliahmed.data.model.Sources
import com.aliahmed.data.network.Const.DEFAULT_PAGE_NUM
import com.aliahmed.data.network.NewsApiService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val database: DatabaseService,
) : NewsRepository {

    override suspend fun getNews(pageNumber: Int): List<Article> {
        val articles = apiService.getNews(
            pageNum = pageNumber
        ).articles.apiArticleListToArticleList()
        return if (pageNumber == DEFAULT_PAGE_NUM) {
            database.deleteAllAndInsertAll(articles)
            database.getAllArticles().first()
        } else {
            articles
        }
    }

    override suspend fun getNewsFromDb(): List<Article> {
        return database.getAllArticles().first()
    }

}
