package com.esra.made.core.data.source.remote.network

import com.esra.made.core.data.source.remote.response.ListProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("buyer/products/popular")
    suspend fun getListProducts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ListProductResponse
}