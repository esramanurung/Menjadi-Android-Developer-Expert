package com.esra.made.oleholeh.di

import com.esra.made.core.domain.usecase.OlehOlehInteractor
import com.esra.made.core.domain.usecase.OlehOlehUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideOlehOlehUseCase(olehOlehInteractor: OlehOlehInteractor): OlehOlehUseCase
}