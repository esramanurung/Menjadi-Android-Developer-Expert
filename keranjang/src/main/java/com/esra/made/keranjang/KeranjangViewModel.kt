package com.esra.made.keranjang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.domain.usecase.OlehOlehUseCase

class KeranjangViewModel(private val olehOlehUseCase: OlehOlehUseCase) :
    ViewModel() {
    val favoriteProducts = olehOlehUseCase.getFavoriteProducts().asLiveData()

    fun setFavoriteProduct(product: MProduct, isFavorite: Boolean) =
        olehOlehUseCase.setFavoriteProduct(product, isFavorite)
}