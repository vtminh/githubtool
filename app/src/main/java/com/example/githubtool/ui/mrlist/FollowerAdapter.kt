package com.example.githubtool.ui.mrlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.user.User
import com.example.githubtool.R
import com.example.githubtool.base.RecyclerViewAdapterBase
import com.example.githubtool.databinding.LayoutUserInfoItemBinding

/**
 * @author tuanminh.vu.
 */
class FollowerAdapter(private val context: Context) : RecyclerViewAdapterBase<User, FollowerAdapter.FollowerViewHolder>() {
    private val requestOptions = RequestOptions()
        .placeholder(R.drawable.ic_empty_holder)
        .error(R.drawable.ic_empty_holder)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

    private val glide = Glide.with(context).setDefaultRequestOptions(requestOptions)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val holder = FollowerViewHolder(LayoutUserInfoItemBinding.inflate(LayoutInflater.from(context), parent, false))
        setupItemClick(holder.itemView, holder)
        return holder
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        val page = getItemData(position)
        holder.onBind(page)
    }

    inner class FollowerViewHolder(private val binding: LayoutUserInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(user: User) = with(binding) {
            glide.load(user.avatarUrl).into(ivAvatar)
            tvName.text = user.login
            tvType.text = user.type
        }
    }
}