package com.yusmp.basecode.base.recyclerAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindableViewHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    lateinit var item: T
    fun onBind(item: T) {
        this.item = item
        bind(item)
    }

    protected abstract fun bind(item: T)
}