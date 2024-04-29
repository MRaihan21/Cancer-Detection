package com.dicoding.asclepius.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.database.NewsItem
import com.dicoding.asclepius.repo.NewsRepository

class NewsViewModel : ViewModel() {
    private val newsRepository = NewsRepository()

    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList: LiveData<List<NewsItem>> = _newsList

    private val _isLoadingIndicator = MutableLiveData<Boolean>()
    val isLoadingIndicator: LiveData<Boolean> = _isLoadingIndicator

    fun fetchHealthNews() {
        _isLoadingIndicator.value = true
        newsRepository.getHealthNews(
            onSuccess = { newsList ->
                _isLoadingIndicator.value = false
                _newsList.postValue(newsList)
            },
            onFailure = { errorMessage ->
                _isLoadingIndicator.value = false
            }
        )
    }
}
