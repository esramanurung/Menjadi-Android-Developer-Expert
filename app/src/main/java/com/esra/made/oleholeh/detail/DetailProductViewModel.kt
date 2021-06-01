package com.esra.made.oleholeh.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.domain.usecase.OlehOlehUseCase

class DetailProductViewModel @ViewModelInject constructor(private val olehOlehUseCase: OlehOlehUseCase) :
    ViewModel() {
    fun setFavoriteProduct(product: MProduct, isFavorite: Boolean) =
        olehOlehUseCase.setFavoriteProduct(product, isFavorite)
}