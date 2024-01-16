package com.example.news.di

import android.app.Application
import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.aliahmed.data.database.AppDatabaseService
import com.aliahmed.data.database.ArticleDatabase
import com.aliahmed.data.database.DatabaseService
import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.di.DbName
import com.aliahmed.data.network.Const
import com.example.core.coroutine.dispatcher.DefaultDispatcherProvider
import com.example.core.coroutine.dispatcher.DispatcherProvider
import com.example.core.networkhelper.NetworkHelper
import com.example.core.networkhelper.NetworkHelperImpl
import com.example.news.usecase.GetNewsUseCase
import com.example.news.usecase.GetNewsUseCaseImpl
import com.example.news.view.paging.NewsPagingSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsFeatureModule {

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    fun provideGetNewsUseCase(pager: Pager<Int,Article>) : GetNewsUseCase = GetNewsUseCaseImpl(pager)

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper = NetworkHelperImpl(context)

    @Provides
    @Singleton
    fun provideDatabaseService(articleDatabase: ArticleDatabase): DatabaseService = AppDatabaseService(articleDatabase)

    @Singleton
    @Provides
    fun provideArticleDatabase(
        application: Application,
        @DbName dbName: String
    ): ArticleDatabase {
        return Room.databaseBuilder(
            application,
            ArticleDatabase::class.java,
            dbName
        )
            .build()
    }

    @Provides
    @Singleton
    fun providePager(
        newsPagingSource: NewsPagingSource
    ): Pager<Int, Article> {
        return Pager(
            config = PagingConfig(
                Const.DEFAULT_QUERY_PAGE_SIZE
            )
        ) {
            newsPagingSource
        }
    }
}