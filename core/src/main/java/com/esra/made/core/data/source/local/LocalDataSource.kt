package com.esra.made.core.data.source.local

import com.esra.made.core.data.source.local.entity.ProductEntity
import com.esra.made.core.data.source.local.room.OlehOlehDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val olehOlehDao: OlehOlehDao) {

    fun getAllProducts(): Flow<List<ProductEntity>> = olehOlehDao.getAllProduct()

    fun getFavoriteProducts(): Flow<List<ProductEntity>> = olehOlehDao.getFavoriteProduct()

    suspend fun insertProducts(products: List<ProductEntity>) =
        olehOlehDao.insertProducts(products)

    fun setFavoriteProduct(product: ProductEntity, isFavorite: Boolean) {
        product.isFavorite = isFavorite
        olehOlehDao.updateFavoriteProduct(product)
    }
}