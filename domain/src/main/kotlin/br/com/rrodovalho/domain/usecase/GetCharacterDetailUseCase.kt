package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase

class GetCharacterDetailUseCase (private val repository: MarvelRepository)
    : UseCase<GetCharacterDetailUseCase.Values, CharacterDetail>() {

    data class Values(val characterId: String): RequestValues

    override suspend fun executeUC(requestValues: Values?): CharacterDetail {
        TODO("Not yet implemented")
    }

}