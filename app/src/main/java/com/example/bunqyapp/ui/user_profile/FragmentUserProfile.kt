package com.example.bunqyapp.ui.user_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bunqyapp.R
import com.example.bunqyapp.ui.main.MainActivity
import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import com.example.bunqyapp.util.StringUtils
import com.example.bunqyapp.util.showToast
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class FragmentUserProfile(val position: Int) : Fragment() {
    private val viewModel: GeneralViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getMonetaryAccountDetails(StringUtils.CURRENT_USER_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onResume() {
        super.onResume()
        observeMonetaryAccount()
    }

    private fun observeMonetaryAccount() {
        viewModel.monetaryAccountDetailData.observe(this, Observer {
            it.response?.get(0)?.monetaryAccountBank.let { it ->
                userName.text =("Hello, ${it?.alias?.get(2)?.name.toString()}")
                userId.text = it?.userId.toString()
                userEmail.text = it?.alias?.get(1)?.name
                userPhoneNumber.text = it?.alias?.get(0)?.name
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
                context?.showToast("An error occurred during account data fetch.")
            }
        })
    }

    companion object {
        fun newInstance(position: Int): Fragment {
            return FragmentUserProfile(
                position
            )
        }
    }
}