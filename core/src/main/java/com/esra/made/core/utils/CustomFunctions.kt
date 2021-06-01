package com.esra.made.core.utils

import android.content.Context
import android.widget.Toast
import java.text.NumberFormat
import java.util.*

class CustomFunctions {
    fun formatRupiah(number: Double): String? {
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        val finalNumber = formatRupiah.format(number)
        return finalNumber.replace(",00", "")
    }

    fun notifyShortToastInfo(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}