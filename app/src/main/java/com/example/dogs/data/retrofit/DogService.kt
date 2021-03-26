package com.example.dogs.data.retrofit

import com.example.dogs.data.entities.DogResponse
import retrofit2.http.GET

interface DogService {

    @GET("random")
    suspend fun getDogResponse(): DogResponse
}