package com.example.bunqyapp.ui.payment_list

import com.example.bunqyapp.network.model.payment_list.Payment

interface PaymentItemClickListener {
    fun openPaymentDetail(payment:Payment)
}