package br.com.rrodovalho.data.repository.remote

import br.com.rrodovalho.data.repository.remote.api.ApiContract
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class RemoteRepository(private val apiContract: ApiContract): MarvelRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMarvelCharacterDetail(characterId: String): CharacterDetail {
        TODO("Not yet implemented")
    }
}