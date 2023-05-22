package com.yusmp.basecode.presentation.common.extentions

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.yusmp.basecode.R


fun ImageView.setImageFromUrl(
    srcUrl: String?,
    placeholder: Int = R.drawable.image_placeholder,
    errorPlaceholder: Int = R.drawable.ic_error_image_placeholder,
) {
    Glide.with(this)
        .load(srcUrl)
        .placeholder(placeholder)
        .error(errorPlaceholder)
        .into(this)
}

fun ImageView.setImageFromUrl(
    srcUrl: String?,
    cornerRadius: Int = 0,
    placeholder: Int = R.drawable.image_placeholder,
    errorPlaceholder: Int = R.drawable.ic_error_image_placeholder,
    transformation: Transformation<Bitmap> = CenterCrop(),
) {
    val requestOptions = if (cornerRadius > 0) {
        RequestOptions().transform(transformation, RoundedCorners(cornerRadius))
    } else {
        RequestOptions().transform(transformation)
    }

    Glide.with(this)
        .load(srcUrl)
        .placeholder(placeholder)
        .apply(requestOptions)
        .into(this)
}

fun ImageView.setDrawableResWithGlide(
    @DrawableRes resourceId: Int,
) {
    Glide.with(this)
        .load(resourceId)
        .into(this)
}

fun Bitmap?.toDrawable(resources: Resources): Drawable? {
    return if (this == null) {
        this
    } else {
        BitmapDrawable(resources, this)
    }
}
