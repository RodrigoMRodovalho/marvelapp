package br.com.rrodovalho.data.repository.local

import br.com.rrodovalho.data.repository.local.db.dao.ComicsDetailDao
import br.com.rrodovalho.data.repository.mapper.transformTo
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

interface CacheRepository: MarvelRepository {
    fun saveComicsDetail(comicsDetail: ComicsDetail)
}

class LocalRepository(private val comicsDetailDao: ComicsDetailDao): CacheRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int)
            = throw IllegalStateException("character list must be fetched on internet")

    override suspend fun fetchComicsDetail(comics: Comics): ComicsDetail {
        return transformTo(comicsDetailDao.getComicsDetailById(comics.id))
    }

    override fun saveComicsDetail(comicsDetail: ComicsDetail) {
        comicsDetailDao.saveComicsDetail(transformTo(comicsDetail))
    }
}