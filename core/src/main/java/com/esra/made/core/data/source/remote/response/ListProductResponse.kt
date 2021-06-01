package com.esra.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListProductResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("result")
    val result: Result,

    @field:SerializedName("success")
    val success: Boolean

)

data class Result(
    @field:SerializedName("docs")
    val docs: List<ProductResponse>,

    @field:SerializedName("hasNextPage")
    val hasNextPage: Boolean,

    @field:SerializedName("hasPrevPage")
    val hasPrevPage: Boolean,

    @field:SerializedName("limit")
    val limit: Int,

    @field:SerializedName("nextPage")
    val nextPage: Int,

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("pagingCounter")
    val pagingCounter: Int,

    @field:SerializedName("prevPage")
    val prevPage: Int,

    @field:SerializedName("totalDocs")
    val totalDocs: Int,

    @field:SerializedName("totalPages")
    val totalPages: Int
)