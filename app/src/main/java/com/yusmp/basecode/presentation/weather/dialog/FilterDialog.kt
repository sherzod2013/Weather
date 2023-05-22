package com.yusmp.basecode.presentation.weather.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yusmp.basecode.databinding.DialogFilterBinding
import com.yusmp.basecode.presentation.common.BaseBottomSheet

class FilterDialog(private val onFilterDataClick: (FilterType) -> Unit) : BaseBottomSheet<DialogFilterBinding>() {
    override val TAG: String = "FilterDialog"

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): DialogFilterBinding =
        DialogFilterBinding.inflate(inflater, container, false)

    override fun DialogFilterBinding.setupViews() {
        condition1.setOnClickListener {
            dismiss()
            onFilterDataClick.invoke(FilterType.TREE_DAYS)
        }
        condition2.setOnClickListener {
            dismiss()
            onFilterDataClick.invoke(FilterType.FIVE_DAYS)
        }
        condition3.setOnClickListener {
            dismiss()
            onFilterDataClick.invoke(FilterType.TEN_DAYS)
        }
    }
}