package com.example.dogs.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogs.data.entities.DogPicture

@Database(
    entities = [DogPicture::class],
    version = 1,
    exportSchema = false
)
abstract class DogsDatabase : RoomDatabase() {
    abstract val dao: DogsDao

    companion object {
        private var instance: DogsDatabase? = null

        fun getInstance(context: Context): DogsDatabase = instance ?: Room.databaseBuilder(
            context,
            DogsDatabase::class.java,
            "DB"
        ).build()
    }

}