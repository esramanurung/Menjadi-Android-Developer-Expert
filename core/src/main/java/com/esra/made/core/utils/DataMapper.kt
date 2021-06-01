package com.esra.made.core.utils

import com.esra.made.core.data.source.local.entity.ProductEntity
import com.esra.made.core.data.source.remote.response.ProductResponse
import com.esra.made.core.domain.model.MProduct

object DataMapper {
    fun mapProductResponsesToEntities(input: List<ProductResponse>): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        input.map {
            val product = ProductEntity(
                _id = it._id,
                createAt = it.createAt,
                descProduct = it.descProduct,
                idSellerAccount = it.idSellerAccount,
                imgProduct = it.imgProduct,
                isAvailable = it.isAvailable,
                lastUpdateAt = it.lastUpdateAt,
                nameProduct = it.nameProduct,
                priceProduct = it.priceProduct,
                priceServiceRange = it.priceServiceRange,
                productCategory = it.productCategory,
                productWeight = it.productWeight,
                promoPrice = it.promoPrice.toString(),
                sumCountView = it.sumCountView,
                umkmTags = it.umkmTags.toString(),
                isFavorite = false
            )
            productList.add(product)
        }
        return productList
    }

    fun mapProductEntitiesToDomain(input: List<ProductEntity>): List<MProduct> =
        input.map {
            MProduct(
                _id = it._id,
                createAt = it.createAt,
                descProduct = it.descProduct,
                idSellerAccount = it.idSellerAccount,
                imgProduct = it.imgProduct,
                isAvailable = it.isAvailable,
                lastUpdateAt = it.lastUpdateAt,
                nameProduct = it.nameProduct,
                priceProduct = it.priceProduct,
                priceServiceRange = it.priceServiceRange.toString(),
                productCategory = it.productCategory,
                productWeight = it.productWeight,
                promoPrice = it.promoPrice.toString(),
                sumCountView = it.sumCountView,
                umkmTags = it.umkmTags,
                isFavorite = it.isFavorite
            )
        }

    fun mapProductDomainToEntity(input: MProduct) = ProductEntity(
        _id = input._id,
        createAt = input.createAt,
        descProduct = input.descProduct,
        idSellerAccount = input.idSellerAccount,
        imgProduct = input.imgProduct,
        isAvailable = input.isAvailable,
        lastUpdateAt = input.lastUpdateAt,
        nameProduct = input.nameProduct,
        priceProduct = input.priceProduct,
        priceServiceRange = input.priceServiceRange,
        productCategory = input.productCategory,
        productWeight = input.productWeight,
        promoPrice = input.promoPrice.toString(),
        sumCountView = input.sumCountView,
        umkmTags = input.umkmTags,
        isFavorite = input.isFavorite
    )
}