package com.esra.made.core.domain.repository

import com.esra.made.core.data.Resource
import com.esra.made.core.domain.model.MProduct
import kotlinx.coroutines.flow.Flow

interface IOlehOlehRepository {

    fun getAllProducts(page: Int, limit: Int): Flow<Resource<List<MProduct>>>

    fun getFavoriteProduct(): Flow<List<MProduct>>

    fun setFavoriteProduct(product: MProduct, isFavorite: Boolean)
}