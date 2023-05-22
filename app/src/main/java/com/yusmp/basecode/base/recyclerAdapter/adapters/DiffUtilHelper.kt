package com.yusmp.basecode.base.recyclerAdapter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.yusmp.basecode.base.recyclerAdapter.Identifiable

fun <T : Identifiable> getDiffUtilCallback() = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T) =
        oldItem == newItem
}