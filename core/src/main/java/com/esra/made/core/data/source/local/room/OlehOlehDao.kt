package com.esra.made.core.data.source.local.room

import androidx.room.*
import com.esra.made.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OlehOlehDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE isFavorite = 1")
    fun getFavoriteProduct(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Update
    fun updateFavoriteProduct(product: ProductEntity)
}