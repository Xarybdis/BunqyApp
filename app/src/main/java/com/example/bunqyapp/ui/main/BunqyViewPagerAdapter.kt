package com.example.bunqyapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bunqyapp.ui._money_transaction.FragmentNewTransaction
import com.example.bunqyapp.ui.payment_list.FragmentPaymentList
import com.example.bunqyapp.ui.user_profile.FragmentUserProfile

class BunqyViewPagerAdapter(activity: AppCompatActivity, private val itemCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        var swipedFragment = Fragment()
        when (position) {
            0 -> swipedFragment = FragmentPaymentList.newInstance(position)
            1 -> swipedFragment = FragmentNewTransaction.newInstance(position)
            2 -> swipedFragment = FragmentUserProfile.newInstance(position)
        }
        return swipedFragment
    }
}