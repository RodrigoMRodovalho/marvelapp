package br.com.rrodovalho.marvelapp.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewCharacter (val id: String,
                          val name: String,
                          val description: String,
                          val imageUrl: String): Parcelable