package com.esra.made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esra.made.core.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class OlehOlehDatabase : RoomDatabase() {
    abstract fun olehOlehDao(): OlehOlehDao
}