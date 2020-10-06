package br.com.rrodovalho.data.repository.local

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class LocalRepository: MarvelRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMarvelCharacterDetail(characterId: String): CharacterDetail {
        TODO("Not yet implemented")
    }

    override suspend fun fetchComicsDetailFromCharacter(comicsResource: String): ComicsDetail {
        TODO("Not yet implemented")
    }

}