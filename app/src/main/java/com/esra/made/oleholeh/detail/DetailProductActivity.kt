package com.esra.made.oleholeh.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.utils.Constants.Companion.BUCKET_PRODUCT_URL
import com.esra.made.core.utils.CustomFunctions
import com.esra.made.oleholeh.databinding.ActivityDetailProductBinding
import com.tuonbondol.textviewutil.strike
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DetailProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    private val detailProductViewModel: DetailProductViewModel by viewModels()
    private val customFunctions = CustomFunctions()
    private var isFavorite = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        val detailProduct = intent.getParcelableExtra<MProduct>(PRODUCT_DATA)
        showDetailProduct(detailProduct)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailProduct(product: MProduct?) {
        product.let {
            if (product != null) {
                isFavorite = product.isFavorite
                if (!isFavorite) {
                    binding.btnAddToCart.apply {
                        text = "Tambah ke Keranjang"
                    }
                } else if (isFavorite) {
                    binding.btnAddToCart.apply {
                        text = "Hapus dari Keranjang"
                    }
                }

                binding.btnAddToCart.setOnClickListener {
                    if (isFavorite) {
                        binding.btnAddToCart.text = "Hapus dari Keranjang"
                        product.let { detailProductViewModel.setFavoriteProduct(it, false) }
                        isFavorite = false
                        customFunctions.notifyShortToastInfo(
                            "Sudah dihapus dari keranjang",
                            binding.root.context
                        )
                        binding.btnAddToCart.apply {
                            text = "Tambah ke Keranjang"
                        }
                    } else if (!isFavorite) {
                        binding.btnAddToCart.text = "Tambah ke Keranjang"
                        product.let { detailProductViewModel.setFavoriteProduct(it, true) }
                        isFavorite = true
                        customFunctions.notifyShortToastInfo(
                            "Sudah dimasukkan ke keranjang",
                            binding.root.context
                        )

                        binding.btnAddToCart.apply {
                            text = "Hapus dari Keranjang"
                        }
                    }
                }

                binding.txtNameProduct.text = product.nameProduct
                binding.txtWeightProduct.text = "${product.productWeight} gram"
                binding.txtTypeProduct.text =
                    product.productCategory.capitalize(Locale.getDefault())
                binding.txtDescriptionProduct.text = product.descProduct

                Glide.with(binding.root.context)
                    .load(BUCKET_PRODUCT_URL + product.imgProduct)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .priority(Priority.HIGH)
                    .into(binding.imgDetailProduct)

                when {
                    product.promoPrice.equals("null") -> {
                        binding.lnrPromo.visibility = android.view.View.GONE
                        binding.txtCurrentPrice.text =
                            customFunctions.formatRupiah(product.priceProduct.toDouble())
                    }
                    product.promoPrice?.toInt() == 0 -> {
                        binding.lnrPromo.visibility = View.GONE
                        binding.txtCurrentPrice.text =
                            customFunctions.formatRupiah(product.priceProduct.toDouble())
                    }

                    product.promoPrice?.toInt()!! > 0 -> {
                        binding.lnrPromo.visibility = View.VISIBLE
                        val sumCount =
                            (product.promoPrice?.toFloat()?.div(product.priceProduct.toFloat()))
                        val countPercentage = (100 - ((sumCount?.times(100))?.toInt() ?: 0))
                        binding.txtPromoPrice.strike()
                        binding.percentagePromo.text = "$countPercentage %"
                        binding.txtPromoPrice.text =
                            customFunctions.formatRupiah(product.promoPrice.toString().toDouble())
                        binding.txtCurrentPrice.text =

                            customFunctions.formatRupiah(product.priceProduct.toDouble())
                    }

                    else -> {
                        binding.lnrPromo.visibility = android.view.View.GONE
                        binding.txtCurrentPrice.text =
                            customFunctions.formatRupiah(product.priceProduct.toDouble())
                    }
                }

            }
        }
    }

    companion object {
        const val PRODUCT_DATA = "product_data"
    }
}