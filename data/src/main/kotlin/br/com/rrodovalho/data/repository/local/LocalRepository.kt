package br.com.rrodovalho.data.repository.local

import br.com.rrodovalho.data.repository.local.db.dao.ComicsDetailDao
import br.com.rrodovalho.data.repository.mapper.transformTo
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository

interface CacheRepository: MarvelRepository {
    fun saveComicsDetail(comicDetail: ComicDetail)
}

class LocalRepository(private val comicsDetailDao: ComicsDetailDao): CacheRepository {

    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int)
            = throw IllegalStateException("character list must be fetched on internet")

    override suspend fun fetchComicsDetail(comic: Comic): ComicDetail {
        return transformTo(comicsDetailDao.getComicsDetailById(comic.id))
    }

    override fun saveComicsDetail(comicDetail: ComicDetail) {
        comicsDetailDao.saveComicsDetail(transformTo(comicDetail))
    }
}