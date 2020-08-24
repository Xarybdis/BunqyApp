package com.example.bunqyapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.InstallationResult
import com.example.bunqyapp.util.Constants
import com.example.bunqyapp.util.StringUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_bar.*
import kotlinx.android.synthetic.main.layout_bottom_bar.view.*
import org.koin.core.KoinComponent
import timber.log.Timber

class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var viewModel: MainViewModel
    private lateinit var bunqyViewPagerAdapter: BunqyViewPagerAdapter
    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getInstallation()

        observeInstallation()

        bunqyViewPagerAdapter = BunqyViewPagerAdapter(this, 3)
        bunqyViewPager.adapter = bunqyViewPagerAdapter

        clickFunctions()
    }

    private fun observeInstallation() {
        viewModel.installationData.observe(this, Observer {
            it.response?.get(InstallationResult.TOKEN)?.token.let { it ->
                StringUtils.API_TOKEN = it?.token.toString()
            }
            viewModel.getDeviceServer()
            observeDeviceServer()
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
                Timber.e("An error occurred in installation.")
            }
        })
    }

    private fun observeDeviceServer() {
        viewModel.deviceServerData.observe(this, Observer {
            it.response?.get(0)?.idResponse?.id.let { it ->
                viewModel.startSession()
                startSession()
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

    private fun startSession() {
        viewModel.sessionData.observe(this, Observer {

            println("haha" + it.sessionServerResult.toString())
            it.sessionServerResult?.get(Constants.ID).let { it ->
            }
            it.sessionServerResult?.get(Constants.SERVER_KEY).let { it ->
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
                R.id.menu_payment_list -> {
                    bunqyViewPager.currentItem = 0
                    observeInstallation()
                }
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