package com.aliahmed.data.database.entity

import com.aliahmed.data.model.ApiArticle
import com.aliahmed.data.model.ApiSource

fun Article.articleToSavedArticleEntity() = SavedArticleEntity(
    id = id,
    source = source.sourceToSavedSourceEntity(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun SavedArticleEntity.savedArticleEntityToArticle() = Article(
    id = id,
    source = source.savedSourceEntityToSource(),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun Source.sourceToSavedSourceEntity() = SavedSourceEntity(
    id = id,
    name = name
)

fun SavedSourceEntity.savedSourceEntityToSource() = Source(
    id = id,
    name = name
)

fun ApiArticle.apiArticleToArticle() = Article(
    source = source,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)

fun List<ApiArticle>.apiArticleListToArticleList(): List<Article> = this.map { apiArticle ->
    apiArticle.apiArticleToArticle()
}

fun ApiSource.apiSourceToSource() = Source(
    id = id,
    name = name
)

fun List<ApiSource>.apiSourceListToSourceList(): List<Source> = this.map { apiSource -> apiSource.apiSourceToSource() }

fun List<SavedArticleEntity>.savedArticlesListToArticles(): List<Article> = this.map { savedArticleEntity -> savedArticleEntity.savedArticleEntityToArticle() }