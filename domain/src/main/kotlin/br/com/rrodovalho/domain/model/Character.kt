package br.com.rrodovalho.domain.model

data class Character (val id: String,
                      val name: String,
                      val description: String,
                      val imageUrl: String,
                      val comics: List<Comics>)