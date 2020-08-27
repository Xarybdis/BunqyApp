package com.example.bunqyapp.ui._money_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.request_inquiry_result.InquiryDetailResponse
import kotlinx.android.synthetic.main.fragment_inquiry_detail_dialog.*

class InquiryDetail(private val inquiryResponse: InquiryDetailResponse) :
    DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_inquiry_detail_dialog, container, false)
    }

    override fun onStart() {
        super.onStart()
        setUi()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goBackButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    private fun setUi() {
        inquiryResponse.response?.get(0)?.requestInquiry.let {
            paymentStatus.text = it?.status
            paymentId.text = it?.id.toString()
            paymentCreated.text = it?.created?.split(".")?.get(0) ?: ""
            it?.amountİnquired.let { it ->
                paymentAmount.text = ("${it?.value} ${it?.currency}")
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(inquiryResponse: InquiryDetailResponse) =
            InquiryDetail(inquiryResponse)
    }
}