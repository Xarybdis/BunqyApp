package com.example.bunqyapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bunqyapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_bar.*
import kotlinx.android.synthetic.main.layout_bottom_bar.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var bunqyViewPagerAdapter: BunqyViewPagerAdapter
    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bunqyViewPagerAdapter = BunqyViewPagerAdapter(this, 3)
        bunqyViewPager.adapter = bunqyViewPagerAdapter

        clickFunctions()
    }

    private fun clickFunctions() {
        setupViewPagerCallback()
        setupBottomNavigationListener()
    }

    private fun setupViewPagerCallback() {
        pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> layoutBottomBar.bottomNavigation.selectedItemId = R.id.menu_payment_list
                    1 -> layoutBottomBar.bottomNavigation.selectedItemId = R.id.menu_payment
                    2 -> layoutBottomBar.bottomNavigation.selectedItemId = R.id.menu_profile
                }
            }
        }
        bunqyViewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun setupBottomNavigationListener() {
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_payment_list -> bunqyViewPager.currentItem = 0
                R.id.menu_payment -> bunqyViewPager.currentItem = 1
                R.id.menu_profile -> bunqyViewPager.currentItem = 2
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bunqyViewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}