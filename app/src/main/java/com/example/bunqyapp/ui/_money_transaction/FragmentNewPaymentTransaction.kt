package com.example.bunqyapp.ui._money_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.money_inquiry.AmountInquired
import com.example.bunqyapp.network.model.money_inquiry.CounterpartyAlias
import com.example.bunqyapp.network.model.money_inquiry.InquiryRequest
import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import com.example.bunqyapp.util.StringUtils
import com.example.bunqyapp.util.replaceSource
import com.example.bunqyapp.util.visible
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_new_transaction.*

class FragmentNewPaymentTransaction(val position: Int) : Fragment() {
    var type: String = EMAIL
    private var monetaryAccountId: Int = 0
    lateinit var viewModel: GeneralViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GeneralViewModel::class.java)
        viewModel.getMonetaryAccountDetails(StringUtils.CURRENT_USER_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickFunctions()
    }

    private fun observeMonetaryAccount() {
        viewModel.monetaryAccountDetailData.observe(this, Observer {
            it[0].id.let { it ->
                monetaryAccountId = it?.toInt() ?: 0
            }

        })
        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                //showProgress
            } else {
                //hideProgress
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                Toast.makeText(context, R.string.payment_fragment_error, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeInquiry() {
        viewModel.inquiryData.observe(this, Observer {
            it.response?.get(0)?.idResult?.let {
                println("Here :$id")
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                //showProgress
            } else {
                //hideProgress
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                //ShowPopup or smth
            }
        })
    }

    private fun clickFunctions() {
        sendMoneyIban.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNameIban, etIbanNumber, etAmountIban, etDescriptionIban, "IBAN")

            viewModel.startInquiry(StringUtils.CURRENT_USER_ID, monetaryAccountId, inquiryRequest)
            observeInquiry()
        }
        sendMoneyEmail.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNameMail, etEmail, etAmountEmail, etDescriptionEmail, "EMAIL")

            viewModel.startInquiry(StringUtils.CURRENT_USER_ID, monetaryAccountId, inquiryRequest)
            observeInquiry()
        }
        sendMoneyPhone.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNamePhone, etPhone, etAmountPhone, etDescriptionPhone, "PHONE")

            viewModel.startInquiry(StringUtils.CURRENT_USER_ID, monetaryAccountId, inquiryRequest)
            observeInquiry()
        }

        emailPaymentCard.setOnClickListener {
            controlCards(EMAIL)
        }

        ibanPaymentCard.setOnClickListener {
            controlCards(IBAN)
        }

        phonePaymentCard.setOnClickListener {
            controlCards(PHONE)
        }
    }

    private fun gatherDataFromFields(
        viewName: TextInputEditText,
        viewSendType: TextInputEditText,
        viewAmount: TextInputEditText,
        viewDescription: TextInputEditText,
        type: String
    ): InquiryRequest {
        val amountInquired = AmountInquired("EUR", viewAmount.text.toString())
        val counterpartsAlias = CounterpartyAlias(viewName.text.toString(), type, viewSendType.text.toString())
        return InquiryRequest(false, amountInquired, counterpartsAlias, viewDescription.text.toString())
    }

    private fun controlCards(sendTypeCard: String) {
        this.type = sendTypeCard
        visible(false, layoutEmail, layoutIban, layoutPhone)
        replaceSource(R.drawable.ic_arrow_extended, cardArrow, cardArrowIban, cardArrowPhone)

        when (sendTypeCard) {
            EMAIL -> {
                layoutEmail.isVisible = true
                cardArrow.setImageResource(R.drawable.ic_arrow)
            }
            IBAN -> {
                layoutIban.isVisible = true
                cardArrowIban.setImageResource(R.drawable.ic_arrow)
            }
            PHONE -> {
                layoutPhone.isVisible = true
                cardArrowPhone.setImageResource(R.drawable.ic_arrow)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeMonetaryAccount()
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            return FragmentNewPaymentTransaction(
                position
            )
        }

        private const val EMAIL = "EMAIL"
        private const val IBAN = "IBAN"
        private const val PHONE = "PHONE"
    }


}