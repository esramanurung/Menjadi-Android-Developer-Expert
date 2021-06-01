package com.esra.made.keranjang

import android.content.Context
import com.esra.made.oleholeh.di.KeranjangModuleDependencies
import dagger.BindsInstance
import dagger.Component
import dagger.hilt.android.scopes.ActivityScoped

@Component(dependencies = [KeranjangModuleDependencies::class])
interface KeranjangComponent {

    fun inject(activity: KeranjangActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(keranjangModuleDependencies: KeranjangModuleDependencies): Builder
        fun build(): KeranjangComponent
    }
}