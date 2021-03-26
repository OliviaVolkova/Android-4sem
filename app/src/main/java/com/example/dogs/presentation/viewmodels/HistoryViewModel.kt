package com.example.dogs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.data.entities.DogPicture
import com.example.dogs.data.repositories.DogRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HistoryViewModel(
    private val dogRepository: DogRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel(){
    private val dogPictures: MutableLiveData<List<DogPicture>> = MutableLiveData()

    fun getPictures() : LiveData<List<DogPicture>> = dogPictures

    fun refreshData() = viewModelScope.launch(coroutineContext){
        val list = dogRepository.getPictures()
        dogPictures.postValue(list)
    }
}