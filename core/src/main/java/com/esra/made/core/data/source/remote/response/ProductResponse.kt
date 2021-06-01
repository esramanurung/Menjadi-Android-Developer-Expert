package com.esra.made.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @field:SerializedName("_id")
    val _id: String,

    @field:SerializedName("createAt")
    val createAt: String,

    @field:SerializedName("descProduct")
    val descProduct: String,

    @field:SerializedName("idSellerAccount")
    val idSellerAccount: String,

    @field:SerializedName("imgProduct")
    val imgProduct: String,

    @field:SerializedName("isAvailable")
    val isAvailable: Boolean,

    @field:SerializedName("lastUpdateAt")
    val lastUpdateAt: String,

    @field:SerializedName("nameProduct")
    val nameProduct: String,

    @field:SerializedName("priceProduct")
    val priceProduct: String,

    @field:SerializedName("priceServiceRange")
    val priceServiceRange: String? = "",

    @field:SerializedName("productCategory")
    val productCategory: String,

    @field:SerializedName("productWeight")
    val productWeight: Int,

    @field:SerializedName("promoPrice")
    val promoPrice: String? = "",

    @field:SerializedName("promotionTags")
    val promotionTags: List<String>,

    @field:SerializedName("sumCountView")
    val sumCountView: Int,

    @field:SerializedName("umkmTags")
    val umkmTags: String? = ""
)