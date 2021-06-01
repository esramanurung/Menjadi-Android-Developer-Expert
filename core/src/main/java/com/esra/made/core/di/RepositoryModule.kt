package com.esra.made.core.di

import com.esra.made.core.data.OlehOlehRepository
import com.esra.made.core.domain.repository.IOlehOlehRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(olehOlehRepository: OlehOlehRepository): IOlehOlehRepository
}