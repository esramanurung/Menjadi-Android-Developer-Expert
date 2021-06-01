package com.esra.made.core.data

import com.esra.made.core.data.source.local.LocalDataSource
import com.esra.made.core.data.source.remote.RemoteDataSource
import com.esra.made.core.data.source.remote.network.ApiResponse
import com.esra.made.core.data.source.remote.response.ProductResponse
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.domain.repository.IOlehOlehRepository
import com.esra.made.core.utils.AppExecutors
import com.esra.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OlehOlehRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IOlehOlehRepository {

    override fun getAllProducts(page: Int, limit: Int): Flow<Resource<List<MProduct>>> =
        object : NetworkBoundResource<List<MProduct>, List<ProductResponse>>() {
            override fun loadFromDB(): Flow<List<MProduct>> {
                return localDataSource.getAllProducts().map {
                    DataMapper.mapProductEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MProduct>?): Boolean {
//                data == null || data.isEmpty()
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ProductResponse>>> =
                remoteDataSource.getAllProducts(page, limit)

            override suspend fun saveCallResult(data: List<ProductResponse>) {
                val productList = DataMapper.mapProductResponsesToEntities(data)
                localDataSource.insertProducts(productList)
            }
        }.asFlow()

    override fun getFavoriteProduct(): Flow<List<MProduct>> {
        return localDataSource.getFavoriteProducts().map {
            DataMapper.mapProductEntitiesToDomain(it)
        }
    }

    override fun setFavoriteProduct(product: MProduct, isFavorite: Boolean) {
        val productEntity = DataMapper.mapProductDomainToEntity(product)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteProduct(productEntity, isFavorite) }
    }
}