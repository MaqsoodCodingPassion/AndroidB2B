package com.spider.b2b.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spider.b2b.B2BRepository
import com.spider.b2b.B2BViewModel
import javax.inject.Inject

class ViewModelFactory
    @Inject constructor(private val repository: B2BRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(B2BViewModel::class.java) -> B2BViewModel(
                repository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}