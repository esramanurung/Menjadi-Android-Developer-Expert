package com.esra.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MProduct(
    val _id: String,
    val createAt: String,
    val descProduct: String,
    val idSellerAccount: String,
    val imgProduct: String,
    val isAvailable: Boolean,
    val lastUpdateAt: String,
    val nameProduct: String,
    val priceProduct: String,
    val priceServiceRange: String,
    val productCategory: String,
    val productWeight: Int,
    val promoPrice: String? = "0",
    val sumCountView: Int,
    val umkmTags: String,
    val isFavorite: Boolean
) : Parcelable