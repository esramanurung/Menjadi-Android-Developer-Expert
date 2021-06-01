package com.esra.made.keranjang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esra.made.core.domain.usecase.OlehOlehUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val olehOlehUseCase: OlehOlehUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(KeranjangViewModel::class.java) -> {
                KeranjangViewModel(olehOlehUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}