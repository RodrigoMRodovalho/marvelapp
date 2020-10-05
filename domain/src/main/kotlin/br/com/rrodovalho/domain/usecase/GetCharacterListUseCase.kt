package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase

class GetCharacterListUseCase (private val repository: MarvelRepository)
    : UseCase<RequestValues, List<Character>>(){

    override suspend fun executeUC(requestValues: RequestValues?): List<Character> {
        TODO("Not yet implemented")
    }
}