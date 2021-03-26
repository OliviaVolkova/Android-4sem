package com.example.dogs.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogPicture(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var url: String
)