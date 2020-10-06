package br.com.rrodovalho.marvelapp.main.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.rrodovalho.marvelapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(imageUrl: String, @DrawableRes placeholder: Int = -1) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}