package com.esra.made.core.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.esra.made.core.R
import com.esra.made.core.databinding.ItemKeranjangBinding
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.ui.listener.ProductClickListener
import com.esra.made.core.utils.Constants.Companion.BUCKET_PRODUCT_URL
import com.esra.made.core.utils.CustomFunctions

class KeranjangListAdapter : RecyclerView.Adapter<KeranjangListAdapter.CartViewHolder>() {
    private var onItemClickCallback: ProductClickListener? = null
    private var onItemDeleteClickCallback: ProductClickListener? = null

    fun setOnClickProductItemCallback(onItemClickCallback: ProductClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setOnClickProductItemDeleteCallback(onItemDeleteCallback: ProductClickListener) {
        this.onItemDeleteClickCallback = onItemDeleteCallback
    }

    inner class CartViewHolder(private val binding: ItemKeranjangBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemCart: MProduct) {
            binding.txtValueNameProduct.text = itemCart.nameProduct
            binding.txtValuePriceProduct.text =
                CustomFunctions().formatRupiah(itemCart.priceProduct.toDouble())

            binding.btnDelete.setOnClickListener {
                onItemDeleteClickCallback?.onClickProduct(itemCart)
            }

            Glide.with(itemView.context)
                .load(BUCKET_PRODUCT_URL + itemCart.imgProduct)
                .placeholder(R.drawable.loading_animation)
                .into(binding.imgThumbnailProduct)

            itemView.setOnClickListener {
                onItemClickCallback?.onClickProduct(itemCart)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder =
        CartViewHolder(
            ItemKeranjangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}