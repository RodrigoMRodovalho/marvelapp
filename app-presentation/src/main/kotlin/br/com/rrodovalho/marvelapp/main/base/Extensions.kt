package br.com.rrodovalho.marvelapp.main.base

import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.rrodovalho.marvelapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(imageUrl: String, @DrawableRes placeholder: Int) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(placeholder)
        .error(R.drawable.marvel_logo)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}