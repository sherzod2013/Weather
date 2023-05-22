package com.yusmp.basecode.base.recyclerAdapter

import java.util.*

sealed class RecyclerDataState<out T>(open val id: String, val viewType: Int) {
    object Loading : RecyclerDataState<Nothing>(
        id = UUID.randomUUID().toString(),
        viewType = ViewType.LOADING_STATE_VIEW_TYPE.ordinal
    )

    object Empty : RecyclerDataState<Nothing>(
        id = UUID.randomUUID().toString(),
        viewType = ViewType.EMPTY_STATE_VIEW_TYPE.ordinal
    )

    data class Data<T>(override val id: String, val data: T) : RecyclerDataState<T>(
        id = id,
        viewType = ViewType.DATA_STATE_VIEW_TYPE.ordinal
    )

    enum class ViewType {
        LOADING_STATE_VIEW_TYPE,
        EMPTY_STATE_VIEW_TYPE,
        DATA_STATE_VIEW_TYPE
    }

    companion object {
        fun <T> createLoadingState(items: Int = 5): List<RecyclerDataState<T>> {
            return (0..items).map { Loading }
        }
    }
}

val <T>RecyclerDataState<T>.dataState: T? get() = (this as? RecyclerDataState.Data<T>)?.data