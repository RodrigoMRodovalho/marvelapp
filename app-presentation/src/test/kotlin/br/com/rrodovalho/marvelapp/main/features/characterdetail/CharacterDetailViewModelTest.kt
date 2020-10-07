package br.com.rrodovalho.marvelapp.main.features.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.domain.usecase.GetCharacterDetailUseCase
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterListViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    lateinit var sut: CharacterDetailViewModel

    @Mock
    lateinit var useCase: GetCharacterDetailUseCase

    @Mock
    lateinit var mockObserver: Observer<Resource<CharacterDetail>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = CharacterDetailViewModel(useCase)
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

            val onChangedCaptor = argumentCaptor<Resource<CharacterDetail>>()
            val addRequestValuesCaptor = argumentCaptor<GetCharacterDetailUseCase.Values>()

            val character = Character("id", "name",
                "description", "imageUrl",
                emptyList())

            val viewCharacter = ViewCharacter("id", "name",
                "description", "imageUrl",
                emptyList())

            val characterDetail = CharacterDetail(character, listOf())

            whenever(useCase.addRequestValues(any())).thenReturn(useCase)
            whenever(useCase.run()).thenReturn(characterDetail)

            sut.getCharacterDetail(viewCharacter)

            verify(useCase).addRequestValues(addRequestValuesCaptor.capture())
            verify(mockObserver).onChanged(onChangedCaptor.capture())

            assertEquals(Status.SUCCESS, onChangedCaptor.lastValue.status)
            assertEquals(characterDetail, onChangedCaptor.lastValue.data)
            assertEquals(character, addRequestValuesCaptor.lastValue.character)
        }
    }

    @Test
    fun characterListViewModel_WhenGettingCharacterList_ShouldReturnError() {

        runBlockingTest {

            val onChangedCaptor = argumentCaptor<Resource<CharacterDetail>>()
            val addRequestValuesCaptor = argumentCaptor<GetCharacterDetailUseCase.Values>()
            val exception = IllegalArgumentException()

            val character = Character("id", "name",
                "description", "imageUrl",
                emptyList())

            val viewCharacter = ViewCharacter("id", "name",
                "description", "imageUrl",
                emptyList())

            whenever(useCase.addRequestValues(any())).thenReturn(useCase)
            whenever(useCase.run()).thenThrow(exception)

            sut.getCharacterDetail(viewCharacter)

            verify(useCase).addRequestValues(addRequestValuesCaptor.capture())
            verify(mockObserver).onChanged(onChangedCaptor.capture())

            assertEquals(Status.ERROR, onChangedCaptor.lastValue.status)
            assertNull(onChangedCaptor.lastValue.data)
            assertEquals(character, addRequestValuesCaptor.lastValue.character)
        }
    }
}