package com.example.dogs.data.repositories

import com.example.dogs.data.entities.DogPicture
import com.example.dogs.data.entities.DogResponse

interface DogRepository {
    suspend fun getRandomDog(): DogPicture
    suspend fun getPictures(): List<DogPicture>
}