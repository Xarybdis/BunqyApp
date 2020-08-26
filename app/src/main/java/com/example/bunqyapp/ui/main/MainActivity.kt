package com.example.bunqyapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.bunqyapp.R
import com.example.bunqyapp.network.model.InstallationRequest
import com.example.bunqyapp.network.model.InstallationResult
import com.example.bunqyapp.ui.viewModel.GeneralViewModel
import com.example.bunqyapp.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_bottom_bar.*
import kotlinx.android.synthetic.main.layout_bottom_bar.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.get
import timber.log.Timber

class MainActivity() : AppCompatActivity(), KoinComponent {
    private var loadingDialog: LoadingDialog? = null
    private val viewModel: GeneralViewModel by viewModel()
    private lateinit var bunqyViewPagerAdapter: BunqyViewPagerAdapter
    private lateinit var pageChangeCallback: ViewPager2.OnPageChangeCallback
    private val connectionSecurityUtils: ConnectionSecurityUtils = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val installationRequestModel =
            InstallationRequest(connectionSecurityUtils.createPKCS8StandardPublicKey(connectionSecurityUtils.keyPair))

        viewModel.getInstallation(installationRequestModel)
        observeInstallation()

        clickFunctions()
    }

    private fun observeInstallation() {
        showLoading()

        viewModel.installationData.observe(this, Observer {
            it.response?.get(InstallationResult.TOKEN)?.token.let { it ->
                StringUtils.API_TOKEN = it?.token.toString()
            }
            viewModel.getDeviceServer()
            observeDeviceServer()
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                Timber.e("An error occurred in installation.")
            }
        })
    }

    private fun observeDeviceServer() {
        showLoading()

        viewModel.deviceServerData.observe(this, Observer {
            it.response?.get(0)?.idResponse?.id.let { it ->
                viewModel.startSession()
                startSession()
            }
            it.error?.get(0)?.let { it ->
                Toast.makeText(this, it.error_description.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                //ShowPopup or smth
            }
        })
    }

    private fun startSession() {
        showLoading()

        viewModel.sessionData.observe(this, Observer {
            it.sessionServerResult?.get(Constants.ID)?.sessionId.let { it ->

            }

            it.sessionServerResult?.get(Constants.SERVER_KEY)?.sessionUser?.id?.let { it ->
                StringUtils.CURRENT_USER_ID = it
                setupBunqyViewPager()
            }

            it.sessionServerResult?.get(Constants.TOKEN)?.sessionToken?.let {
                StringUtils.API_TOKEN = it.token.toString()
            }
        })

        viewModel.loading.observe(this, Observer { loading ->
            if (loading) {
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.onError.observe(this, Observer { onError ->
            if (onError) {
                showToast("An error occurred while starting a session.")
                Timber.e("MainActivity: Error in starting the session.")
            }
        })
    }

    private fun setupBunqyViewPager() {
        bunqyViewPagerAdapter = BunqyViewPagerAdapter(this, 3)
        bunqyViewPager.adapter = bunqyViewPagerAdapter
        bunqyViewPager.currentItem = 1
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
                }
                R.id.menu_payment -> {
                    bunqyViewPager.currentItem = 1
                }
                R.id.menu_profile -> {
                    bunqyViewPager.currentItem = 2
                }
            }
            true
        }
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.init(this)
        }
        if (!loadingDialog!!.isShowing) {
            loadingDialog!!.show()
        }
    }

    fun hideLoading() {
        if (loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bunqyViewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }

}