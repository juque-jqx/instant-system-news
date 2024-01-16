package com.aliahmed.data.network

import com.aliahmed.data.model.News
import com.aliahmed.data.network.Const.DEFAULT_COUNTRY
import com.aliahmed.data.network.Const.DEFAULT_PAGE_NUM
import com.aliahmed.data.network.Const.DEFAULT_QUERY_PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("country") country: String = DEFAULT_COUNTRY,
        @Query("page") pageNum: Int = DEFAULT_PAGE_NUM,
        @Query("pageSize") pageSize: Int = DEFAULT_QUERY_PAGE_SIZE,
    ): News
}