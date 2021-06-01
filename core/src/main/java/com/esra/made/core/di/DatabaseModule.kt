package com.esra.made.core.di

import android.content.Context
import androidx.room.Room
import com.esra.made.core.data.source.local.room.OlehOlehDao
import com.esra.made.core.data.source.local.room.OlehOlehDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): OlehOlehDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            OlehOlehDatabase::class.java, "OlehOleh.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }


    @Provides
    fun provideOlehOlehDao(database: OlehOlehDatabase): OlehOlehDao = database.olehOlehDao()
}