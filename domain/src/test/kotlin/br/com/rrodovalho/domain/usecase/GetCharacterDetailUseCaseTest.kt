package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.IllegalArgumentException


@ExperimentalCoroutinesApi
class GetCharacterDetailUseCaseTest {

    lateinit var sut : GetCharacterDetailUseCase

    @Mock
    lateinit var repository: MarvelRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        sut = GetCharacterDetailUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getCharacterDetailUseCase_WhenFetchMarvelCharacterDetail_ShouldReturnCharacterWithComicsData() {

        runBlockingTest {

            val comicsList = listOf(
                Comic("id1", "name1", "resourceUrl1"),
                Comic("id2", "name2", "resourceUrl2"),
                Comic("id3", "name3", "resourceUrl3"),
                Comic("id4", "name4", "resourceUrl4")
            )

            val character = Character("id",
                "name",
                "description",
                "imageUrl",
                comicsList
            )

            val comicsDetailList = listOf(
                ComicDetail(comicsList[0], "description1", "imageUrl1"),
                ComicDetail(comicsList[1], "", "imageUrl2"),
                ComicDetail(comicsList[2], "", ""),
                ComicDetail(comicsList[3], null, "imageUrl4")
            )

            sut.addRequestValues(GetCharacterDetailUseCase.Values(character))

            whenever(repository.fetchComicsDetail(comicsList[0]))
                .thenReturn(comicsDetailList[0])

            whenever(repository.fetchComicsDetail(comicsList[1]))
                .thenReturn(comicsDetailList[1])

            whenever(repository.fetchComicsDetail(comicsList[2]))
                .thenThrow(IllegalArgumentException())

            whenever(repository.fetchComicsDetail(comicsList[3]))
                .thenReturn(comicsDetailList[3])

            val characterDetail = sut.run(testDispatcher)

            assertEquals(character, characterDetail.character)
            assertEquals(comicsDetailList, characterDetail.comicDetail)
        }
    }
}