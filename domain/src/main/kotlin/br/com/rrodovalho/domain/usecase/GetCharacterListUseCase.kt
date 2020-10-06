package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterListUseCase (private val repository: MarvelRepository)
    : UseCase<RequestValues, List<Character>>(){

    private var offset = 0
    private val limit = 10

    override suspend fun executeUC(requestValues: RequestValues?): List<Character> {
        return withContext(Dispatchers.IO){
            return@withContext repository.fetchMarvelCharacterList(limit, offset)
        }.also {
            offset+=limit
        }
    }
}