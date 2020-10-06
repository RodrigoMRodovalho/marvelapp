package br.com.rrodovalho.data.repository.remote

import br.com.rrodovalho.data.repository.mapper.transformTo
import br.com.rrodovalho.data.repository.remote.api.ApiContract
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class RemoteRepository(private val apiContract: ApiContract): MarvelRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        return transformTo(apiContract.fetchCharacterList(mapOf("limit" to "$limit", "offset" to "$offset")))
    }

    override suspend fun fetchComicsDetail(comic: Comic): ComicDetail {
        return transformTo(apiContract.fetchComicsFromCharacter(comic.resourceUrl))
    }
}