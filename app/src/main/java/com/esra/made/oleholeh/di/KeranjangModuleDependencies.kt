package com.esra.made.oleholeh.di

import com.esra.made.core.domain.usecase.OlehOlehUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface KeranjangModuleDependencies {

    fun olehOlehUseCase(): OlehOlehUseCase
}