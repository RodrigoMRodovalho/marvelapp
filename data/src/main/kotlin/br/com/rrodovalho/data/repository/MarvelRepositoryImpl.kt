package br.com.rrodovalho.data.repository

import br.com.rrodovalho.data.repository.local.CacheRepository
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val localRepository: CacheRepository,
                           private val remoteRepository: MarvelRepository): MarvelRepository{

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> {
        return remoteRepository.fetchMarvelCharacterList(limit, offset)
    }

    override suspend fun fetchComicsDetail(comics: Comics): ComicsDetail {
        return try {
            localRepository.fetchComicsDetail(comics)
        } catch (e: Exception) {
            val comicsDetail = remoteRepository.fetchComicsDetail(comics)
            localRepository.saveComicsDetail(comicsDetail)
            comicsDetail
        }
    }
}