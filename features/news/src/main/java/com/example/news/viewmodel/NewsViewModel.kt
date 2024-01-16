package com.example.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.aliahmed.data.database.entity.Article
import com.aliahmed.data.network.Resource
import com.example.news.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val useCase: GetNewsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<PagingData<Article>>>(Resource.Success(PagingData.empty()))
    val state: StateFlow<Resource<PagingData<Article>>> = _state

    private val _newsItemPaging = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val newsItemPaging: StateFlow<PagingData<Article>> = _newsItemPaging

    fun getNews() {
        viewModelScope.launch {
            _state.value = Resource.Loading()
            useCase.fetchNewsList(this).collect { pagingData: PagingData<Article> ->
                _state.value = Resource.Success(pagingData)
                _newsItemPaging.value = pagingData
            }
        }
    }

}