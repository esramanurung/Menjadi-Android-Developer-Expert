package com.esra.made.core.ui.listener

import com.esra.made.core.domain.model.MProduct

interface ProductClickListener {

    fun onClickProduct(itemSelected: MProduct)
}