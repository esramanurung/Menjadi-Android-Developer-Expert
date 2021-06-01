package com.esra.made.oleholeh.dashboard

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.esra.made.core.domain.usecase.OlehOlehUseCase

class DashboardViewModel @ViewModelInject constructor(olehOlehUseCase: OlehOlehUseCase) :
    ViewModel() {
    var page = 1
    var limit = 15
    val products = olehOlehUseCase.getAllProducts(page, limit).asLiveData()
}