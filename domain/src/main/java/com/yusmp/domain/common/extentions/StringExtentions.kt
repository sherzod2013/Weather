package com.yusmp.domain.common.extentions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.replaceParagraphs(): String = this.replace("¶¶", "\n")

fun String.convertToDate(pattern: String = "yyyy-MM-dd"): Date? {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.parse(this)
}
