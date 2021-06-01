package com.esra.made.oleholeh.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.esra.made.core.data.Resource
import com.esra.made.core.domain.model.MProduct
import com.esra.made.core.ui.adapter.ProductGridAdapter
import com.esra.made.core.ui.listener.ProductClickListener
import com.esra.made.oleholeh.databinding.FragmentDashboardBinding
import com.esra.made.oleholeh.detail.DetailProductActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val dashboardViewModel: DashboardViewModel by viewModels()

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var dashboardProductAdapter: ProductGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        productDashboardRecyclerview()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun productDashboardRecyclerview() {
        dashboardProductAdapter = ProductGridAdapter()
        binding.rvProducts.apply {
            adapter = dashboardProductAdapter
            layoutManager = GridLayoutManager(binding.root.context, 2)
        }
    }

    private fun getProductFromRemote() {
        dashboardViewModel.products.observe(viewLifecycleOwner, { response ->
            if (response != null) {
                when (response) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        hideLoading()
                        dashboardProductAdapter.differ.submitList(response.data)
                    }
                    is Resource.Error -> {
                        hideLoading()
                        showError()
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            getProductFromRemote()
            dashboardProductAdapter.setOnClickProductItemCallback(object : ProductClickListener {
                override fun onClickProduct(itemSelected: MProduct) {
                    val intent = Intent(activity, DetailProductActivity::class.java)
                    intent.putExtra(DetailProductActivity.PRODUCT_DATA, itemSelected)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading() {
        binding.loadingProgress.loadingProgress.visibility =
            View.VISIBLE
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    private fun hideLoading() {
        binding.loadingProgress.loadingProgress.visibility = View.GONE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    private fun showError() {
        Snackbar.make(binding.root, "Jaringan Lemah!", Snackbar.LENGTH_LONG)
            .setAction("Ulang") {
                getProductFromRemote()
                showLoading()
            }
            .show()
    }
}