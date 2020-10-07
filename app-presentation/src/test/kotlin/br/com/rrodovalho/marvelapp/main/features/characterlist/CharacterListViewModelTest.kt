package br.com.rrodovalho.marvelapp.main.features.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var sut: CharacterListViewModel

    @Mock
    lateinit var useCase: GetCharacterListUseCase

    @Mock
    lateinit var mockObserver: Observer<Resource<List<ViewCharacter>>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = CharacterListViewModel(useCase)
        sut.observeData.observeForever(mockObserver)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun characterListViewModel_WhenGettingCharacterList_ShouldReturnDataWithSuccess() {

        runBlockingTest {

            val captor = argumentCaptor<Resource<List<ViewCharacter>>>()

            val characterList = listOf(
                Character("id1", "name1", "description1", "imageUrl1", emptyList()),
                Character("id2", "name2", "description2", "imageUrl2", emptyList()),
                Character("id3", "name3", "description3", "imageUrl3", emptyList())
            )

            val expectedViewCharacterList = listOf(
                ViewCharacter("id1", "name1", "description1", "imageUrl1", emptyList()),
                ViewCharacter("id2", "name2", "description2", "imageUrl2", emptyList()),
                ViewCharacter("id3", "name3", "description3", "imageUrl3", emptyList())
            )


            whenever(useCase.run()).thenReturn(characterList)

            sut.getCharacterList()

            verify(mockObserver).onChanged(captor.capture())

            assertEquals(Status.SUCCESS, captor.lastValue.status)
            assertEquals(expectedViewCharacterList, captor.lastValue.data)
        }
    }

    @Test
    fun characterListViewModel_WhenGettingCharacterList_ShouldReturnError() {

        runBlockingTest {

            val captor = argumentCaptor<Resource<List<ViewCharacter>>>()

            val exception = NullPointerException()

            whenever(useCase.run()).thenThrow(exception)

            sut.getCharacterList()

            verify(mockObserver).onChanged(captor.capture())

            assertEquals(Status.ERROR, captor.lastValue.status)
            assertNull(captor.lastValue.data)
            assertEquals(exception, captor.lastValue.throwable)
        }
    }
}