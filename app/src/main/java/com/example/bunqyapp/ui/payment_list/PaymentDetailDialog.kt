package com.example.bunqyapp.ui.payment_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.payment_list.Payment
import kotlinx.android.synthetic.main.fragment_inquiry_detail_dialog.*

class PaymentDetailDialog(private val paymentItem: Payment) :
    DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_payment_detail_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        // setDialogConfigurations()
        setUi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBackButton.setOnClickListener {
            fragmentManager?.popBackStackImmediate()
        }
    }

    private fun setUi() {
        paymentItem.let {
            paymentId.text = it.id.toString()
            paymentCreated.text = it.created?.split(".")?.get(0) ?: ""
            paymentAmount.text = ("${it.amount?.value} ${it.amount?.currency}")
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(payment: Payment) =
            PaymentDetailDialog(payment)
    }
}