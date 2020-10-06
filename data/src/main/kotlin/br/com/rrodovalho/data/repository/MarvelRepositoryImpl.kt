package br.com.rrodovalho.data.repository

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val localRepository: MarvelRepository,
                           private val remoteRepository: MarvelRepository): MarvelRepository{

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        //TODO implement cache
        return remoteRepository.fetchMarvelCharacterList(limit, offset)
    }

    override suspend fun fetchMarvelCharacterDetail(characterId: String): CharacterDetail {
        //TODO implement cache
        return remoteRepository.fetchMarvelCharacterDetail(characterId)
    }

    override suspend fun fetchComicsDetailFromCharacter(comicsResource: String): ComicsDetail {
        return remoteRepository.fetchComicsDetailFromCharacter(comicsResource)
    }
}