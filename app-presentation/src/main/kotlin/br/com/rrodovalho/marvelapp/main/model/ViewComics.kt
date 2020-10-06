package br.com.rrodovalho.marvelapp.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewComics (val id: String, val name: String, val resourceUrl: String): Parcelable