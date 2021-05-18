package com.example.githubtool.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author tuanminh.vu
 */
abstract class RecyclerViewAdapterBase<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {
    protected val items: ArrayList<T> = ArrayList()

    private var mSelectedPosition = -1

    protected var recyclerView: RecyclerView? = null

    val data: ArrayList<T>
        get() = items

    var onItemClick: ((data: T, oldPosition: Int, newPosition: Int) -> Unit)? = null

    override fun getItemCount(): Int {
        return items.size
    }

    fun createView(viewId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(viewId, parent, false)
    }

    fun setupItemClick(
        view: View,
        holder: RecyclerView.ViewHolder,
        onclick: ((data: T, oldPosition: Int, newPosition: Int) -> Unit)? = onItemClick
    ) {
        view.setOnClickListener { v ->
            onclick?.invoke(data[holder.layoutPosition], mSelectedPosition, holder.layoutPosition)
            mSelectedPosition = holder.layoutPosition
        }
    }


    fun getItemData(position: Int): T {
        return items[position]
    }

    fun add(pos: Int, item: T?) {
        if (item != null) {
            items.add(pos, item)
            notifyItemInserted(pos)
        }
    }

    fun remove(pos: Int) {
        if (pos > -1 && pos < itemCount) {
            items.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    fun set(pos: Int, item: T?) {
        if (item != null) {
            items[pos] = item
            notifyItemChanged(pos)
        }
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addAll(collection: Collection<T>?) {
        if (collection == null || collection.isEmpty()) {
            return
        }
        val dataSize = items.size
        items.addAll(collection)
        notifyItemRangeInserted(dataSize, collection.size)
    }

    open fun setData(data: Collection<T>?) {
        items.clear()
        if (data != null && !data.isEmpty()) {
            items.addAll(data)
        }
        notifyDataSetChanged()
    }

    open fun changeData(data: Collection<T>?) {
        items.clear()
        if (data != null && !data.isEmpty()) {
            items.addAll(data)
        }
    }


    fun updateItemData(position: Int, data: T) {
        items[position] = data
        notifyItemChanged(position)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun updateSelectedPosition(position: Int) {
        val temp = mSelectedPosition
        mSelectedPosition = position
        notifyItemChanged(mSelectedPosition)
        if (temp > -1 && temp < itemCount) {
            notifyItemChanged(temp)
        }
    }

    fun getLastSelectedItem(): T? {
        return if (mSelectedPosition > -1 && mSelectedPosition < itemCount) {
            items.get(mSelectedPosition)
        } else {
            null
        }
    }

    fun getSelectedPosition(): Int {
        return mSelectedPosition
    }

    fun setSelectedPosition(position: Int) {
        mSelectedPosition = position
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }
}