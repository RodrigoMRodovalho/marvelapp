package br.com.rrodovalho.data.repository

import br.com.rrodovalho.data.repository.local.CacheRepository
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MarvelRepositoryImplTest {

    lateinit var sut : MarvelRepositoryImpl

    @Mock
    lateinit var localRepository: CacheRepository

    @Mock
    lateinit var remoteRepository: MarvelRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = MarvelRepositoryImpl(localRepository, remoteRepository)
    }

    @Test
    fun marvelRepositoryImpl_WhenFetchMarvelCharacterList_ShouldReturnRemoteData() {

        val limit = 10
        val offset = 0

        val expectedCharacterList = listOf(Character("id", "name",
            "description", "imageUrl", emptyList()))
        runBlockingTest {

            whenever(remoteRepository.fetchMarvelCharacterList(limit,offset))
                .thenReturn(expectedCharacterList)

            val characterList = sut.fetchMarvelCharacterList(limit, offset)

            assertEquals(expectedCharacterList, characterList )

            verify(localRepository, never()).fetchMarvelCharacterList(anyInt(), anyInt())
        }
    }

    @Test
    fun marvelRepositoryImpl_WhenFetchComicsDetail_ShouldReturnLocalCachedComicDetail() {

        runBlockingTest {

            val comics = Comic("1", "name", "resourceUrl")
            val expectedComicsDetail = ComicDetail(comics, "description", "imageUrl")

            whenever(localRepository.fetchComicsDetail(comics))
                .thenReturn(expectedComicsDetail)

            val comicsDetail = sut.fetchComicsDetail(comics)

            assertEquals(expectedComicsDetail, comicsDetail)

            verify(remoteRepository, never()).fetchComicsDetail(any())
            verify(localRepository, never()).saveComicsDetail(any())

        }
    }

    @Test
    fun marvelRepositoryImpl_WhenFetchComicsDetail_ShouldReturnRemoteComicDetail() {

        runBlockingTest {

            val comics = Comic("1", "name", "resourceUrl")
            val expectedComicsDetail = ComicDetail(comics, "description", "imageUrl")

            whenever(localRepository.fetchComicsDetail(comics))
                .thenThrow(NoSuchElementException())

            whenever(remoteRepository.fetchComicsDetail(comics))
                .thenReturn(expectedComicsDetail)

            val comicsDetail = sut.fetchComicsDetail(comics)

            assertEquals(expectedComicsDetail, comicsDetail)

            verify(localRepository).fetchComicsDetail(comics)
            verify(remoteRepository).fetchComicsDetail(comics)
            verify(localRepository).saveComicsDetail(expectedComicsDetail)
        }
    }
}