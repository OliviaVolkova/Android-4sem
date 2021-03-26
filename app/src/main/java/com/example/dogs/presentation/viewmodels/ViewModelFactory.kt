package com.example.dogs.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogs.data.repositories.DogRepository
import kotlin.coroutines.CoroutineContext

class ViewModelFactory(
    private val reposotiry: DogRepository,
    private val coroutineContext: CoroutineContext
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when{
            modelClass.isAssignableFrom(DogViewModel::class.java) -> {
                DogViewModel(reposotiry, coroutineContext) as? T
                    ?: throw IllegalStateException("unknown view model class")
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(reposotiry, coroutineContext) as? T
                    ?: throw IllegalStateException("unknown view model class")
            }
            else -> throw IllegalStateException("unknown view model class")
        }
}