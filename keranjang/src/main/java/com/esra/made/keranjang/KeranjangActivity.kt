package com.esra.made.keranjang

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.ui.adapter.KeranjangListAdapter
import com.esra.made.core.ui.listener.ProductClickListener
import com.esra.made.keranjang.databinding.ActivityKeranjangBinding
import com.esra.made.oleholeh.detail.DetailProductActivity
import com.esra.made.oleholeh.di.KeranjangModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class KeranjangActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val keranjangViewModel: KeranjangViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityKeranjangBinding

    private lateinit var keranjangProductAdapter: KeranjangListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerKeranjangComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    KeranjangModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityKeranjangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productKeranjangRecyclerview()
        getProductFromDatabase()
        keranjangProductAdapter.setOnClickProductItemCallback(object : ProductClickListener {
            override fun onClickProduct(itemSelected: MProduct) {
                val intent = Intent(binding.root.context, DetailProductActivity::class.java)
                intent.putExtra(DetailProductActivity.PRODUCT_DATA, itemSelected)
                startActivity(intent)
            }
        })

        keranjangProductAdapter.setOnClickProductItemDeleteCallback(object :
            ProductClickListener {
            override fun onClickProduct(itemSelected: MProduct) {
                keranjangViewModel.setFavoriteProduct(itemSelected, false)
                getProductFromDatabase()
            }
        })
    }

    private fun productKeranjangRecyclerview() {
        keranjangProductAdapter = KeranjangListAdapter()
        binding.rvKeranjang.apply {
            adapter = keranjangProductAdapter
            layoutManager = LinearLayoutManager(binding.root.context)
        }
    }

    private fun getProductFromDatabase() {
        keranjangViewModel.favoriteProducts.observe(this, { response ->
            if (response.isNullOrEmpty()) {
                binding.rvKeranjang.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
                binding.txtEmptyId.visibility = View.VISIBLE
            } else {
                binding.imageView.visibility = View.GONE
                binding.txtEmptyId.visibility = View.GONE
                keranjangProductAdapter.differ.submitList(response)
            }
        })
    }
}