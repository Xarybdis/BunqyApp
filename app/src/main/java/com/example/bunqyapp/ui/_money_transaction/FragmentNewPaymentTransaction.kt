package com.example.bunqyapp.ui._money_transaction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.money_inquiry.AmountInquired
import com.example.bunqyapp.network.model.money_inquiry.CounterpartyAlias
import com.example.bunqyapp.network.model.money_inquiry.InquiryRequest
import com.example.bunqyapp.ui.main.MainActivity
import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import com.example.bunqyapp.util.StringUtils
import com.example.bunqyapp.util.replaceSource
import com.example.bunqyapp.util.showToast
import com.example.bunqyapp.util.visible
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_new_transaction.*
import org.koin.android.viewmodel.ext.android.viewModel


@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class FragmentNewPaymentTransaction(val position: Int) : Fragment() {
    private val viewModel: GeneralViewModel by viewModel()
    private var cardId: Int = 0
    private var type: String = EMAIL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        viewModel.getMonetaryAccountDetails(StringUtils.CURRENT_USER_ID)
    }

    override fun onResume() {
        super.onResume()
        observeMonetaryAccount()
    }

    private fun observeMonetaryAccount() {
        viewModel.monetaryAccountDetailData.observe(this, Observer {
            it.response?.get(0)?.monetaryAccountBank?.id.let { it ->
                StringUtils.CURRENT_MONETARY_ACCOUNT = it?.toInt() ?: 0
            }

        })
        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                (activity as MainActivity?)?.showLoading()
            } else {
                (activity as MainActivity?)?.hideLoading()
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
            it.response?.get(0)?.idResult?.let { it ->
                it.id?.let { it1 ->
                    viewModel.getInquiryDetails(StringUtils.CURRENT_USER_ID, StringUtils.CURRENT_MONETARY_ACCOUNT, it1)
                    observeInquiryDetail()
                }
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                (activity as MainActivity?)?.showLoading()
            } else {
                (activity as MainActivity?)?.hideLoading()
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                (activity as MainActivity?)?.showToast("An error occurred during requesting inquiry.")
            }
        })
    }

    private fun observeInquiryDetail() {
        viewModel.inquiryDetailData.observe(this, Observer {

            it.let {
                val fragmentManager: FragmentManager? = fragmentManager
                val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
                fragmentTransaction?.add(InquiryDetail(it), "InquiryDialog")
                fragmentTransaction?.commit()
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                (activity as MainActivity?)?.showLoading()
            } else {
                (activity as MainActivity?)?.hideLoading()
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                (activity as MainActivity?)?.showToast("An error occurred during requesting inquiry details.")
            }
        })
    }

    private fun clickFunctions() {
        emailPaymentCard.setOnClickListener {
            controlCards(EMAIL)
        }

        ibanPaymentCard.setOnClickListener {
            controlCards(IBAN)
        }

        phonePaymentCard.setOnClickListener {
            controlCards(PHONE)
        }

        sendMoneyEmail.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNameMail, etEmail, etAmountEmail, etDescriptionEmail, "EMAIL")

            if (layoutEmail.isVisible) {
                if (checkFields(cardId)) {
                    viewModel.startInquiry(StringUtils.CURRENT_USER_ID, StringUtils.CURRENT_MONETARY_ACCOUNT, inquiryRequest)
                    observeInquiry()
                }
            }
        }

        sendMoneyIban.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNameIban, etIbanNumber, etAmountIban, etDescriptionIban, "IBAN")

            if (layoutIban.isVisible) {
                if (checkFields(cardId)) {
                    viewModel.startInquiry(StringUtils.CURRENT_USER_ID, StringUtils.CURRENT_MONETARY_ACCOUNT, inquiryRequest)
                    observeInquiry()
                }
            }
        }

        sendMoneyPhone.setOnClickListener {
            val inquiryRequest = gatherDataFromFields(etNamePhone, etPhone, etAmountPhone, etDescriptionPhone, "PHONE")

            if (layoutPhone.isVisible) {
                if (checkFields(cardId)) {
                    viewModel.startInquiry(StringUtils.CURRENT_USER_ID, StringUtils.CURRENT_MONETARY_ACCOUNT, inquiryRequest)
                    observeInquiry()
                }
            }
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
                cardId = 0
            }
            IBAN -> {
                layoutIban.isVisible = true
                cardArrowIban.setImageResource(R.drawable.ic_arrow)
                cardId = 1
            }
            PHONE -> {
                layoutPhone.isVisible = true
                cardArrowPhone.setImageResource(R.drawable.ic_arrow)
                cardId = 2
            }
        }
    }

    private fun checkFields(cardId: Int): Boolean {
        var isValid: Boolean = true
        when (cardId) {
            0 -> {
                isValid = multiFieldValidation(etNameMail, etEmail, etAmountEmail)
            }
            1 -> {
                isValid = multiFieldValidation(etNameIban, etIbanNumber, etAmountIban)
            }
            2 -> {
                isValid = multiFieldValidation(etNamePhone, etPhone, etAmountPhone)
            }
        }
        return isValid
    }

    private fun multiFieldValidation(vararg edittext: TextInputEditText): Boolean {
        var isValid: Boolean = true
        for (text in edittext) {
            if (text.text.isNullOrEmpty()) {
                text.error = "Please fill the text."
                isValid = false
            }
        }
        return isValid
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