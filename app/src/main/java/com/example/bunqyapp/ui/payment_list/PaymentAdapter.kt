package com.example.bunqyapp.ui.payment_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.payment_list.Payment
import kotlinx.android.synthetic.main.item_payment.view.*

class PaymentAdapter(private val paymentList: List<Payment>, private val onItemClickListener: PaymentItemClickListener) :
    RecyclerView.Adapter<PaymentAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_payment, parent, false)
        return PaymentAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int = paymentList.size

    override fun onBindViewHolder(holder: PaymentAdapterViewHolder, position: Int) {
        holder.bindView(paymentList[position], onItemClickListener)
    }

}

class PaymentAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(paymentItem: Payment, clickListener: PaymentItemClickListener) {
        itemView.paymentDate.text = paymentItem.created?.split(".")?.get(0) ?: ""
        itemView.receiverName.text = paymentItem.counterpartyAlias?.displayName
        itemView.receivedAmount.text = ("${paymentItem.amount?.value} ${paymentItem.amount?.currency}")

        itemView.setOnClickListener {
            clickListener.openPaymentDetail(paymentItem)
        }
    }
}