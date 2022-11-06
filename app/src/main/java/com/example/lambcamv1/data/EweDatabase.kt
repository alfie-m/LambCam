package com.example.lambcamv1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lambcamv1.models.Ewe

@Database(entities = [Ewe::class], version = 1, exportSchema = false)
abstract class EweDatabase: RoomDatabase() {

    // Connect ewe database and ewe data access object
    abstract fun eweDao(): EweDao

    companion object{
        @Volatile
        private var INSTANCE: EweDatabase? = null

        fun getDatabase(context: Context): EweDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EweDatabase::class.java,
                    "ewe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}