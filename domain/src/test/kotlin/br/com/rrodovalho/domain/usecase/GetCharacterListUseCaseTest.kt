package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
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


@ExperimentalCoroutinesApi
class GetCharacterListUseCaseTest {

    lateinit var sut : GetCharacterListUseCase

    @Mock
    lateinit var repository: MarvelRepository

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        sut = GetCharacterListUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getCharacterListUseCase_WhenFetchMarvelCharacterList_ShouldReturnData() {

        runBlockingTest {

            val expectedCharacterList = listOf(Character("id",
                "name",
                "description",
                "imageUrl",
                listOf()))

            val limit = 10
            val offset = 0

            whenever(repository.fetchMarvelCharacterList(limit, offset))
                .thenReturn(expectedCharacterList)

            assertEquals(0, sut.offset)

            val characterList = sut.run(testDispatcher)

            assertEquals(expectedCharacterList, characterList)

            assertEquals(10, sut.offset)
        }

    }
}