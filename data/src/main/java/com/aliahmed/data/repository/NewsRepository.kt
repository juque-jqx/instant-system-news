package com.aliahmed.data.repository

import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.network.Const.DEFAULT_PAGE_NUM

interface NewsRepository  {
    suspend fun getNews(pageNumber: Int = DEFAULT_PAGE_NUM): List<Article>
    suspend fun getNewsFromDb(): List<Article>
}
