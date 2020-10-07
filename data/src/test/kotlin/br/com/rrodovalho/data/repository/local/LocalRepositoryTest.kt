package br.com.rrodovalho.data.repository.local

import br.com.rrodovalho.data.repository.local.db.dao.ComicsDetailDao
import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity
import br.com.rrodovalho.data.repository.mapper.transformTo
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalStateException

@ExperimentalCoroutinesApi
class LocalRepositoryTest {

    lateinit var sut: LocalRepository

    @Mock
    lateinit var comicsDetailDao: ComicsDetailDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = LocalRepository(comicsDetailDao)
    }

    @Test(expected = IllegalStateException::class)
    fun localRepository_WhenFetchMarvelCharacterList_ShouldThrowIllegalStateException() {
        runBlockingTest {
            sut.fetchMarvelCharacterList(0, 0)
        }
    }

    @Test
    fun localRepository_WhenFetchComicsDetail_ShouldReturnMappedData() {

        runBlockingTest {

            val comic = Comic("300", "name", "https://i.com.br/300")
            val comicsDetailEntity = ComicsDetailEntity("300",
                "name",
                "https://i.com.br/300",
                "description",
                "https://i.com.br/300/i.png"
            )

            whenever(comicsDetailDao.getComicsDetailById(comic.id))
                .thenReturn(comicsDetailEntity)

            val comicDetail = sut.fetchComicsDetail(comic)

            assertEquals(comic, comicDetail.comic)
            assertEquals(comicsDetailEntity.description, comicDetail.description)
            assertEquals(comicsDetailEntity.imageUrl, comicDetail.imageUrl)
        }
    }

    @Test(expected = NoSuchElementException::class)
    fun localRepository_WhenGetNoSavedComicsDetail_ShouldThrowNoSuchElementException() {

        runBlockingTest {

            val comic = Comic("300", "name", "https://i.com.br/300")

            whenever(comicsDetailDao.getComicsDetailById(comic.id))
                .thenReturn(null)

            sut.fetchComicsDetail(comic)
        }
    }

    @Test
    fun localRepository_WhenSaveComicsDetail_ShouldSaveWithSuccess() {

        runBlockingTest {

            val comic = Comic("300", "name", "https://i.com.br/300")
            val comicDetail = ComicDetail(comic, "description", "https://i.com.br/300/i.png")
            val captor = argumentCaptor<ComicsDetailEntity>()

            doNothing().whenever(comicsDetailDao).saveComicsDetail(any())

            sut.saveComicsDetail(comicDetail)

            verify(comicsDetailDao).saveComicsDetail(captor.capture())
            verifyNoMoreInteractions(comicsDetailDao)

            assertEquals(captor.lastValue.id, comicDetail.comic.id)
            assertEquals(captor.lastValue.name, comicDetail.comic.name)
            assertEquals(captor.lastValue.description, comicDetail.description)
            assertEquals(captor.lastValue.imageUrl, comicDetail.imageUrl)
            assertEquals(captor.lastValue.resourceUri, comicDetail.comic.resourceUrl)
        }
    }
}