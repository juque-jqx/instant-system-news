package com.aliahmed.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aliahmed.data.database.dao.ArticleDao
import com.aliahmed.data.database.dao.SavedArticleDao
import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.database.entity.SavedArticleEntity

@Database(entities = [SavedArticleEntity::class, Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getArticleDao(): ArticleDao

}