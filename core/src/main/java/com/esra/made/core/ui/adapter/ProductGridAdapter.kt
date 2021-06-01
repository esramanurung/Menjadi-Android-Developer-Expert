package com.esra.made.core.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.esra.made.core.databinding.ItemProductBinding
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.ui.listener.ProductClickListener
import com.esra.made.core.utils.Constants.Companion.BUCKET_PRODUCT_URL
import com.esra.made.core.utils.CustomFunctions
import com.tuonbondol.textviewutil.strike

class ProductGridAdapter :
    RecyclerView.Adapter<ProductGridAdapter.GridProductViewHolder>() {

    private var onItemClickCallback: ProductClickListener? = null
    fun setOnClickProductItemCallback(onItemClickCallback: ProductClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class GridProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(product: MProduct) {
            binding.imgProduct.setBackgroundColor(Color.rgb(217, 217, 217))

            Glide.with(itemView.context)
                .load(BUCKET_PRODUCT_URL + product.imgProduct)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .priority(Priority.HIGH)
                .into(binding.imgProduct)

            binding.txtCurrentPrice.text =
                CustomFunctions().formatRupiah(product.priceProduct.toDouble())
            binding.txtNameProduct.text = product.nameProduct
            when (product.promoPrice?.toIntOrNull()) {
                0 -> {
                    binding.lnrPromo.visibility = View.INVISIBLE
                    binding.txtPromoPrice.visibility = View.GONE
                }
                null -> {
                    binding.lnrPromo.visibility = View.INVISIBLE
                    binding.txtPromoPrice.visibility = View.GONE
                }
                else -> {
                    binding.txtPromoPrice.text =
                        CustomFunctions().formatRupiah(product.priceProduct.toDouble())
                    binding.txtCurrentPrice.text =
                        CustomFunctions().formatRupiah(product.promoPrice.toDouble())
                    binding.lnrPromo.visibility = View.VISIBLE
                    val sumCount =
                        (product.promoPrice.toFloat().div(product.priceProduct.toFloat()))
                    val countPercentage = (100 - (sumCount.times(100)).toInt())
                    binding.txtPromoPrice.strike()
                    binding.percentagePromo.text = "$countPercentage %"
                }
            }
            itemView.setOnClickListener {
                onItemClickCallback?.onClickProduct(product)
            }
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<MProduct>() {
        override fun areItemsTheSame(oldItem: MProduct, newItem: MProduct): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: MProduct, newItem: MProduct): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridProductViewHolder =
        GridProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GridProductViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = differ.currentList.size
}