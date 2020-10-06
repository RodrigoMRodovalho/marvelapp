package br.com.rrodovalho.domain.model

data class ComicDetail(
    val comic: Comic,
    val description: String?,
    val imageUrl: String)