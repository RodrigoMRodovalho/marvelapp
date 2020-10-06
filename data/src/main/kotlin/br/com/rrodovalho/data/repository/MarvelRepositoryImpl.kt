package br.com.rrodovalho.data.repository

import br.com.rrodovalho.data.repository.local.CacheRepository
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val localRepository: CacheRepository,
                           private val remoteRepository: MarvelRepository): MarvelRepository{

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        return remoteRepository.fetchMarvelCharacterList(limit, offset)
    }

    override suspend fun fetchComicsDetail(comic: Comic): ComicDetail {
        return try {
            localRepository.fetchComicsDetail(comic)
        } catch (e: Exception) {
            val comicsDetail = remoteRepository.fetchComicsDetail(comic)
            localRepository.saveComicsDetail(comicsDetail)
            comicsDetail
        }
    }
}