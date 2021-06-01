package com.esra.made.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id")
    var _id: String,

    @ColumnInfo(name = "createAt")
    var createAt: String,

    @ColumnInfo(name = "descProduct")
    var descProduct: String,

    @ColumnInfo(name = "idSellerAccount")
    var idSellerAccount: String,

    @ColumnInfo(name = "imgProduct")
    var imgProduct: String,

    @ColumnInfo(name = "isAvailable")
    var isAvailable: Boolean,

    @ColumnInfo(name = "lastUpdateAt")
    var lastUpdateAt: String,

    @ColumnInfo(name = "nameProduct")
    var nameProduct: String,

    @ColumnInfo(name = "priceProduct")
    var priceProduct: String,

    @ColumnInfo(name = "priceServiceRange")
    var priceServiceRange: String? = "",

    @ColumnInfo(name = "productCategory")
    var productCategory: String,

    @ColumnInfo(name = "productWeight")
    var productWeight: Int,

    @ColumnInfo(name = "promoPrice")
    var promoPrice: String?="",

    @ColumnInfo(name = "sumCountView")
    var sumCountView: Int,

    @ColumnInfo(name = "umkmTags")
    var umkmTags: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)