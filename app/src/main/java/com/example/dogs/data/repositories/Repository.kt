package com.example.dogs.data.repositories

import com.example.dogs.data.entities.DogPicture
import com.example.dogs.data.entities.DogResponse
import com.example.dogs.data.retrofit.DogService
import com.example.dogs.data.room.DogsDao

class Repository(
    private val dogService: DogService,
    private val dogsDao: DogsDao
) : DogRepository {

    override suspend fun getRandomDog(): DogPicture = DogPicture(url = dogService.getDogResponse().url).also {
        dogsDao.insertPicture(it)
    }

    override suspend fun getPictures(): List<DogPicture> = dogsDao.getPictures()


}