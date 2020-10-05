package br.com.rrodovalho.data.repository

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val localRepository: MarvelRepository,
                           private val remoteRepository: MarvelRepository): MarvelRepository{

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMarvelCharacterDetail(characterId: String): CharacterDetail {
        TODO("Not yet implemented")
    }
}