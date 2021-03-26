package com.example.dogs.data.entities

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message")
    val url: String,
    @SerializedName("status")
    val status: String
)