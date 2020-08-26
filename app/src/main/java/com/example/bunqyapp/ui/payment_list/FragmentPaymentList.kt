package com.example.bunqyapp.ui.payment_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.payment_list.Payment
import com.example.bunqyapp.network.model.payment_list.PaymentResult
import com.example.bunqyapp.ui.main.MainActivity
import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import com.example.bunqyapp.util.StringUtils
import kotlinx.android.synthetic.main.fragment_payment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentPaymentList(val position: Int) : Fragment(), PaymentItemClickListener {
    private val viewModel: GeneralViewModel by viewModel()
    private lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun openPaymentDetail(payment: Payment) {

        val fragmentManager: FragmentManager? = fragmentManager
        val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(PaymentDetailDialog(payment), "PaymentListDialog")
        fragmentTransaction?.commit()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPaymentList(StringUtils.CURRENT_USER_ID, StringUtils.CURRENT_MONETARY_ACCOUNT)

        observePaymentList()
    }

    private fun observePaymentList() {
        viewModel.fetchPaymentData.observe(this, Observer {
            it.paymentResult?.let { it1 -> setupRecyclerView(it1) }
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

    private fun setupRecyclerView(paymentResultList: List<PaymentResult>) {
        var paymentList = mutableListOf<Payment>()

        for (paymentResult in paymentResultList) {
            paymentResult.payment?.let { paymentList.add(it) }
        }

        paymentRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        paymentAdapter = PaymentAdapter(paymentList, this)
        paymentRecycler.setHasFixedSize(true)
        paymentRecycler.adapter = paymentAdapter
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            return FragmentPaymentList(position)
        }
    }

}