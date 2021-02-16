package com.example.githubapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DBItem::class),version=1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():ItemDAO
}