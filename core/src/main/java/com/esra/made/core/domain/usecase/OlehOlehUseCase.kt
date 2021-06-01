package com.esra.made.core.domain.usecase

import com.esra.made.core.data.Resource
import com.esra.made.core.domain.model.MProduct
import kotlinx.coroutines.flow.Flow

interface OlehOlehUseCase {
    fun getAllProducts(page: Int, limit: Int): Flow<Resource<List<MProduct>>>
    fun getFavoriteProducts(): Flow<List<MProduct>>
    fun setFavoriteProduct(product: MProduct, isFavorite: Boolean)
}