package com.dicoding.asclepius.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.repo.HistoryRepository

class HistViewModel(application: Application) : ViewModel() {

    private val mHistoryRepository: HistoryRepository = HistoryRepository(application)

    fun insert(history: History) {
        mHistoryRepository.insert(history)
    }


}