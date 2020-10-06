package br.com.rrodovalho.domain.usecase

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.domain.repository.MarvelRepository
import br.com.rrodovalho.domain.usecase.base.RequestValues
import br.com.rrodovalho.domain.usecase.base.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class GetCharacterDetailUseCase (private val repository: MarvelRepository)
    : UseCase<GetCharacterDetailUseCase.Values, CharacterDetail>() {

    data class Values(val character: Character): RequestValues

    override suspend fun executeUC(requestValues: Values?): CharacterDetail {
        return withContext(Dispatchers.IO){
            supervisorScope {
                val comicsDetail = Array<ComicsDetail?>(requestValues?.character!!.comics.size) { null }
                requestValues.character.comics.forEachIndexed { index, comics ->
                    val call = async { repository.fetchComicsDetailFromCharacter(comics.resourceUrl) }
                    comicsDetail[index] = try {
                        call.await()
                    } catch (ex: Exception) {
                        ComicsDetail(comics, "", "")
                    }
                }
                return@supervisorScope CharacterDetail(requestValues.character, comicsDetail.toList())
            }
            //return@withContext repository.fetchMarvelCharacterDetail(requestValues?.character!!.id)
        }
    }

}