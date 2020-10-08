package br.com.rrodovalho.marvelapp.util.mocks

import br.com.rrodovalho.domain.model.*
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.GetCharacterDetailUseCase
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import br.com.rrodovalho.marvelapp.main.features.characterdetail.CharacterDetailViewModel
import br.com.rrodovalho.marvelapp.main.features.characterlist.CharacterListViewModel
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mockedUseCaseModule = module {
    viewModel<CharacterListViewModel> { MockCharacterListViewModel() }
    viewModel<CharacterDetailViewModel> { MockCharacterDetailViewModel() }
}

class MockMarvelRepository: MarvelRepository {
    override suspend fun fetchMarvelCharacterList(limit: Int, offset: Int): List<Character> { TODO("Not yet implemented") }
    override suspend fun fetchComicsDetail(comic: Comic): ComicDetail { TODO("Not yet implemented") }
}

class MockGetCharacterListUseCase: GetCharacterListUseCase(MockMarvelRepository()) {}

class MockCharacterListViewModel: CharacterListViewModel(MockGetCharacterListUseCase()) {

    companion object {
        lateinit var resource : Resource<List<ViewCharacter>>
    }

    override fun getCharacterList() {
        _observeData.value = resource
    }
}

class MockGetCharacterDetailUseCase: GetCharacterDetailUseCase(MockMarvelRepository()) {}

class MockCharacterDetailViewModel: CharacterDetailViewModel(MockGetCharacterDetailUseCase()) {

    companion object {
        lateinit var resource : Resource<CharacterDetail>
    }

    override fun getCharacterDetail(character: ViewCharacter) {
        _observeData.value = resource
    }

}