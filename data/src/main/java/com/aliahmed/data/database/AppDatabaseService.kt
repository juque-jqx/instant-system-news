package com.aliahmed.data.database

import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.database.entity.SavedArticleEntity
import com.aliahmed.data.database.entity.articleToSavedArticleEntity
import com.aliahmed.data.database.entity.savedArticlesListToArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatabaseService(
    private val articleDatabase: ArticleDatabase,
) : DatabaseService {
    override suspend fun upsert(article: Article) {
        articleDatabase.getSavedArticleDao().upsert(article.articleToSavedArticleEntity())
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDatabase.getSavedArticleDao().getSavedArticles()
            .map { list: List<SavedArticleEntity> ->
                list.savedArticlesListToArticles()
            }
    }

    override suspend fun deleteArticle(article: Article) {
        articleDatabase.getSavedArticleDao().deleteArticle(article.articleToSavedArticleEntity())
    }

    override fun getAllArticles(): Flow<List<Article>> = articleDatabase.getArticleDao().getAllArticles()

    override fun deleteAllAndInsertAll(articles: List<Article>) {
        articleDatabase.getArticleDao().deleteAllAndInsertAll(articles)
    }

}