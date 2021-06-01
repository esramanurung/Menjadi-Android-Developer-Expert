package com.esra.made.core.data.source.remote

import android.util.Log
import com.esra.made.core.data.source.remote.network.ApiResponse
import com.esra.made.core.data.source.remote.network.ApiService
import com.esra.made.core.data.source.remote.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllProducts(page: Int, limit: Int): Flow<ApiResponse<List<ProductResponse>>> {
        return flow {
            try {
                val response = apiService.getListProducts(page, limit)
                val dataArray = response.result.docs
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.result.docs))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}