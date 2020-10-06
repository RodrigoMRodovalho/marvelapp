package br.com.rrodovalho.data.repository.remote

import br.com.rrodovalho.data.repository.mapper.transform
import br.com.rrodovalho.data.repository.mapper.transformTo
import br.com.rrodovalho.data.repository.remote.api.ApiContract
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class RemoteRepository(private val apiContract: ApiContract): MarvelRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        return transformTo(apiContract.fetchCharacterList(mapOf("limit" to "$limit", "offset" to "$offset")))
    }

    override suspend fun fetchComicsDetail(comics: Comics): ComicsDetail {
        return transform(apiContract.fetchComicsFromCharacter(comics.resourceUrl))
    }
}