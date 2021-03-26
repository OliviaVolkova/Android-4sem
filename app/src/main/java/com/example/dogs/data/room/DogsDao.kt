package com.example.dogs.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.dogs.data.entities.DogPicture
import retrofit2.http.GET

@Dao
interface DogsDao {

    @Insert
    fun insertPicture(picture: DogPicture)

    @Query("SELECT * FROM DogPicture")
    fun getPictures():List<DogPicture>

    @Query("DELETE FROM DogPicture")
    fun deletePictures()

}