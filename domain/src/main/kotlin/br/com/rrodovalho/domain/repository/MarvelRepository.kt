package br.com.rrodovalho.domain.repository

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.ComicsDetail

interface MarvelRepository {
    suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character>
    suspend fun fetchMarvelCharacterDetail(characterId: String): CharacterDetail
    suspend fun fetchComicsDetailFromCharacter(comicsResource: String): ComicsDetail
}