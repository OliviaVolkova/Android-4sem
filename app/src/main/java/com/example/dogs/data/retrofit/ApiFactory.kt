package com.example.dogs.data.retrofit

import android.util.Log
import com.example.dogs.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    val retrofit by lazy{
        Log.d("MYTAG", BuildConfig.API_URI)
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service: DogService by lazy {
        retrofit.create(DogService::class.java)
    }


}