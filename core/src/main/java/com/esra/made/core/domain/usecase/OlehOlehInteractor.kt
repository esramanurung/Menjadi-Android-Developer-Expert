package com.esra.made.core.domain.usecase

import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.domain.repository.IOlehOlehRepository
import javax.inject.Inject

class OlehOlehInteractor @Inject constructor(private val olehOlehRepository: IOlehOlehRepository) :
    OlehOlehUseCase {
    override fun getAllProducts(page: Int, limit: Int) =
        olehOlehRepository.getAllProducts(page, limit)

    override fun getFavoriteProducts() = olehOlehRepository.getFavoriteProduct()

    override fun setFavoriteProduct(product: MProduct, isFavorite: Boolean) =
        olehOlehRepository.setFavoriteProduct(product, isFavorite)

}