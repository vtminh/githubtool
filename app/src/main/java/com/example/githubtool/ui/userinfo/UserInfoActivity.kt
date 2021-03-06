package com.example.githubtool.ui.userinfo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.user.User
import com.example.githubtool.R
import com.example.githubtool.base.BaseActivity
import com.example.githubtool.databinding.ActivityUserInfoBinding
import com.example.githubtool.ui.mrlist.FollowersActivity

/**
 * @author tuanminh.vu
 */
class UserInfoActivity : BaseActivity() {

    companion object {
        const val EXTRA_USER = "EXTRA_USER"

        @JvmStatic
        fun start(
            context: Context,
            user: User
        ) {
            val intent = Intent(context, UserInfoActivity::class.java)
            intent.putExtra(EXTRA_USER, user)
            context.startActivity(intent)
        }
    }

    private val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_empty_holder)
        .error(R.drawable.ic_empty_holder)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    private val glide by lazy { Glide.with(this).setDefaultRequestOptions(requestOptions) }
    private lateinit var binding: ActivityUserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (intent.getSerializableExtra(EXTRA_USER) as? User)?.let { user ->
            updateLayout(user)
            binding.btnViewFollowers.setOnClickListener {
                user.login?.let {
                    FollowersActivity.start(this, it)
                }
            }
        } ?: run {
            finish()
        }
    }

    private fun updateLayout(user: User) = with(binding) {
        glide.load(user.avatarUrl).into(ivAvatar)
        tvName.text = user.login
        tvFollowers.text = String.format("%s : %s", getString(R.string.str_followers), user.followers)
        tvFollowing.text = String.format("%s : %s", getString(R.string.str_following), user.following)
    }
}