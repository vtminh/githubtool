package com.example.githubtool.ui.mrlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubtool.R
import com.example.githubtool.base.BaseActivity
import com.example.githubtool.base.UIUtils.createDivider
import kotlinx.android.synthetic.main.activity_mr_list.*


/**
 * @author tuanminh.vu
 */
class FollowersActivity : BaseActivity() {

    companion object {
        const val EXTRA_USER_NAME = "EXTRA_USER_NAME"

        @JvmStatic
        fun start(
            context: Context,
            userName: String
        ) {
            val intent = Intent(context, FollowersActivity::class.java)
            intent.putExtra(EXTRA_USER_NAME, userName)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: FollowerViewModel
    private lateinit var adapter: FollowerAdapter
    private lateinit var userName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mr_list)
        intent.getStringExtra(EXTRA_USER_NAME)?.let {
            userName = it
            initViews()
            setupViewModel()
            viewModel.getFollowerList(userName)
        } ?: kotlin.run { finish() }

    }

    private fun initViews() {
        adapter = FollowerAdapter(applicationContext)
        rvMergeRequest.layoutManager = LinearLayoutManager(this)
        rvMergeRequest.addItemDecoration(createDivider())
        rvMergeRequest.adapter = adapter

        srlMergeRequest.setOnRefreshListener {
            if (!viewModel.isDataLoading()) {
                viewModel.getFollowerList(userName)
            } else {
                srlMergeRequest.isRefreshing = false
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)
        getAppComponent().inject(viewModel)

        viewModel.loadingLiveData.observe(this, Observer {
            if (it && !srlMergeRequest.isRefreshing) {
                val transparent = !adapter.data.isNullOrEmpty()
                toggleLoading(true, transparent)
            } else {
                toggleLoading(false)
                srlMergeRequest.isRefreshing = false
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            showErrorToast(it)
        })

        viewModel.baseLiveData.observe(this, Observer {
            if (it.isSuccess()) {
                adapter.setData(it.data)
            }

            if (!it.isFromCache)
                showEmptyPageIfNeeded()
        })
    }

    private fun showEmptyPageIfNeeded() {
        emptyLayout.isVisible = adapter.data.isEmpty()
    }
}