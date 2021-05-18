package com.example.githubtool

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubtool.base.BaseActivity
import com.example.githubtool.base.UIUtils.hideKeyboard
import com.example.githubtool.ui.userinfo.UserInfoActivity
import com.example.githubtool.ui.userinfo.UserInfoViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author tuanminh.vu
 */
class MainActivity : BaseActivity() {

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        btnGet.setOnClickListener {
            etUsername.text.toString().takeIf { it.isNotBlank() }?.let {
                hideKeyboard(this)
                viewModel.getUserInfo(etUsername.text.toString())
            } ?: run {
                Toast.makeText(this, "Please input user name first.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        getAppComponent().inject(viewModel)

        viewModel.loadingLiveData.observe(this, Observer {
            toggleLoading(it)
        })

        viewModel.errorLiveData.observe(this, Observer {
            showErrorToast(it)
        })

        viewModel.baseLiveData.observe(this, Observer {
            if (it.isSuccess()) {
                UserInfoActivity.start(this, it.data!!)
            } else {
                Toast.makeText(this, "User ${etUsername.text} is not available", Toast.LENGTH_SHORT).show()
            }
        })
    }


}