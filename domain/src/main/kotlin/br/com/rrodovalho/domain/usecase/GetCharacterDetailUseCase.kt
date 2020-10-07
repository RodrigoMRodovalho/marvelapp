package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.exception.EmptyCharacterDetailException
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

open class GetCharacterDetailUseCase (private val repository: MarvelRepository)
    : UseCase<GetCharacterDetailUseCase.Values, CharacterDetail>() {

    data class Values(val character: Character): RequestValues

    override suspend fun executeUC(requestValues: Values?): CharacterDetail {
        return supervisorScope {
                var failureCount = 0
                val comicsDetail = Array<ComicDetail?>(requestValues?.character!!.comics.size) { null }
                requestValues.character.comics.forEachIndexed { index, comics ->
                    val call = async { repository.fetchComicsDetail(comics) }
                    comicsDetail[index] = try {
                        call.await()
                    } catch (ex: Exception) {
                        failureCount++
                        ComicDetail(comics, "", "")
                    }
                }

                if (failureCount == requestValues.character.comics.size){
                    throw EmptyCharacterDetailException()
                }
                return@supervisorScope CharacterDetail(requestValues.character, comicsDetail.toList())
        }
    }

}