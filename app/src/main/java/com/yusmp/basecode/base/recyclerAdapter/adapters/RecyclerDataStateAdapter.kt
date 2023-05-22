package com.yusmp.basecode.base.recyclerAdapter.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yusmp.basecode.base.recyclerAdapter.BindableViewHolder
import com.yusmp.basecode.base.recyclerAdapter.RecyclerDataState
import com.yusmp.basecode.base.recyclerAdapter.dataState
import com.yusmp.basecode.base.recyclerAdapter.EmptySearchViewHolder
import com.yusmp.basecode.databinding.ItemEmptySearchBinding

abstract class RecyclerDataStateAdapter<Data : Any, DataViewHolder : BindableViewHolder<Data>> :
    ListAdapter<RecyclerDataState<Data>, RecyclerView.ViewHolder>(
        object : DiffUtil.ItemCallback<RecyclerDataState<Data>>() {
            override fun areItemsTheSame(
                oldItem: RecyclerDataState<Data>,
                newItem: RecyclerDataState<Data>
            ) = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RecyclerDataState<Data>,
                newItem: RecyclerDataState<Data>
            ) = oldItem == newItem
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (RecyclerDataState.ViewType.values()[viewType]) {
            RecyclerDataState.ViewType.LOADING_STATE_VIEW_TYPE -> createLoadingStateViewHolder(parent)
            RecyclerDataState.ViewType.EMPTY_STATE_VIEW_TYPE -> createEmptyStateViewHolder(parent)
            RecyclerDataState.ViewType.DATA_STATE_VIEW_TYPE -> createDataStateViewHolder(parent)
        }
    }

    open fun createEmptyStateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return EmptySearchViewHolder(
            ItemEmptySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    abstract fun createLoadingStateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun createDataStateViewHolder(parent: ViewGroup): DataViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.dataState?.also {
            (holder as? DataViewHolder)?.onBind(it)
        }
    }

    final override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}