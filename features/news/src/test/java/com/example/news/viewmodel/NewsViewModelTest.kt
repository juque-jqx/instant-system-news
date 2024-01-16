package com.example.news.viewmodel

import app.cash.turbine.test
import com.aliahmed.data.network.Resource
import org.junit.Assert.*


import com.aliahmed.data.repository.NewsRepository
import com.example.core.coroutine.dispatcher.DispatcherProvider
import com.example.core.coroutine.dispatcher.TestDispatcherProvider
import com.example.news.usecase.GetNewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @Mock
    private lateinit var getNewsUseCase: GetNewsUseCase

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        Dispatchers.setMain(dispatcherProvider.main)
    }

    @Test
    fun `fetch news with repository response success should set Success Ui State`(){
        runTest {
            val viewModel = NewsViewModel(
                getNewsUseCase
            )

            viewModel.state.test {
                assertTrue(awaitItem() is Resource.Success)
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}