package br.com.rrodovalho.marvelapp.main.base

import android.widget.ImageView
import br.com.rrodovalho.marvelapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}