package com.example.githubtool

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.githubtool.base.BaseActivity
import com.example.githubtool.base.UIUtils.hideKeyboard
import com.example.githubtool.databinding.ActivityMainBinding
import com.example.githubtool.ui.userinfo.UserInfoActivity
import com.example.githubtool.ui.userinfo.UserInfoViewModel

/**
 * @author tuanminh.vu
 */
class MainActivity : BaseActivity() {

    private lateinit var viewModel: UserInfoViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        binding.btnGet.setOnClickListener {
            binding.etUsername.text.toString().takeIf { it.isNotBlank() }?.let {
                hideKeyboard(this)
                viewModel.getUserInfo(binding.etUsername.text.toString())
            } ?: run {
                Toast.makeText(this, getString(R.string.str_no_user_name_input), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(UserInfoViewModel::class.java)
        getAppComponent().inject(viewModel)

        viewModel.loadingLiveData.observe(this, {
            toggleLoading(it)
        })

        viewModel.errorLiveData.observe(this, {
            showErrorToast(it)
        })

        viewModel.baseLiveData.observe(this, {
            if (it.isSuccess()) {
                UserInfoActivity.start(this, it.data!!)
            } else {
                Toast.makeText(this, String.format(getString(R.string.str_unavailable_user_name), binding.etUsername.text), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }


}