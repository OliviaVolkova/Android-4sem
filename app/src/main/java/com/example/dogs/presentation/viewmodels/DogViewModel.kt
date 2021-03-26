package com.example.dogs.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.data.entities.DogPicture
import com.example.dogs.data.repositories.DogRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DogViewModel(
    private val dogRepository: DogRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val dogResponse : MutableLiveData<DogPicture> = MutableLiveData()

    fun getResponse():LiveData<DogPicture> = dogResponse

    fun updateDog() = viewModelScope.launch(coroutineContext) {
        val dog = dogRepository.getRandomDog()
        dogResponse.postValue(dog)
    }
}
