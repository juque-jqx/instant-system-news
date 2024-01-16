package com.aliahmed.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.UUID

data class NewsListResponse(
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("articles")
    val articles: List<NewsListModel> = emptyList(),
) : Serializable

data class NewsListModel  (
    @SerializedName("title")
    var title: String = UUID. randomUUID().toString(),
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("source") var source : Source? = null ) : Serializable

data class Source(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String? = ""
)
