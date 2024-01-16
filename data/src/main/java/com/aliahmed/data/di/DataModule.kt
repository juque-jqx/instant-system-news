package com.aliahmed.data.di

import com.aliahmed.data.network.ApiKeyInterceptor
import com.aliahmed.data.network.WeatherApiService
import com.aliahmed.data.network.BaseHeadersInterceptor
import com.aliahmed.data.network.NewsApiService
import com.aliahmed.data.network.Const
import com.aliahmed.data.network.WeatherBaseURL
import com.aliahmed.data.network.ResultCallAdapterFactory
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class ApiKey

@Qualifier
annotation class BaseUrl

@Qualifier
annotation class DbName

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(BaseHeadersInterceptor())
            addInterceptor(apiKeyInterceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }.build()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): WeatherApiService {
        return Retrofit.Builder()
            .baseUrl(WeatherBaseURL.URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(WeatherApiService::class.java)
    }

    @ApiKey
    @Provides
    fun provideApiKey(): String = Const.API_KEY

    @DbName
    @Provides
    fun provideDbName(): String = Const.DB_NAME

    @Provides
    @Singleton
    fun provideNewsApiService(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient,
    ): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .client(okHttpClient)
            .build()
            .create(NewsApiService::class.java)
    }
}