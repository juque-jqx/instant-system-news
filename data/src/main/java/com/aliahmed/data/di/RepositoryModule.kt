package com.aliahmed.data.di

import com.aliahmed.data.repository.CurrentWeatherRepository
import com.aliahmed.data.repository.CurrentWeatherRepositoryImpl
import com.aliahmed.data.repository.NewsRepository
import com.aliahmed.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCurrentWeatherRepo(impl : CurrentWeatherRepositoryImpl) : CurrentWeatherRepository

    @Binds
    fun provideNewsRepo(impl : NewsRepositoryImpl) : NewsRepository
}