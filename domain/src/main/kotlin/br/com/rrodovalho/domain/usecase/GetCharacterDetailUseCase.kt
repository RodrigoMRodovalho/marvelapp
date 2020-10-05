package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterDetailUseCase (private val repository: MarvelRepository)
    : UseCase<GetCharacterDetailUseCase.Values, CharacterDetail>() {

    data class Values(val character: Character): RequestValues

    override suspend fun executeUC(requestValues: Values?): CharacterDetail {
        return withContext(Dispatchers.IO){
            return@withContext repository.fetchMarvelCharacterDetail(requestValues?.character!!.id)
        }
    }

}