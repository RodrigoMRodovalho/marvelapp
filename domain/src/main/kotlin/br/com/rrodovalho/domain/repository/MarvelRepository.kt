package br.com.rrodovalho.domain.repository

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail

interface MarvelRepository {
    suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character>
    suspend fun fetchComicsDetail(comic: Comic): ComicDetail
}