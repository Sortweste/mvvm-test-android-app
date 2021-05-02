package com.sort.pinto.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sort.pinto.data.converters.Converters
import com.sort.pinto.data.dao.CategoryDao

/* Database configuration*/
@Database(entities = [Category::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
}