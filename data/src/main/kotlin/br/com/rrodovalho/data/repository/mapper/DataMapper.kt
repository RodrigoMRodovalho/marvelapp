package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.remote.model.ApiResponse
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail

fun transformTo(apiResponse: ApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    apiResponse.data.results.forEach {
        characterList.add(Character("${it.id}", it.name,
            it.description,
            it.thumbnail.path.plus("/standard_xlarge.").plus(it.thumbnail.extension)))
    }

    return characterList
}

fun transform(apiResponse: ApiResponse): CharacterDetail {
    //TODO map
    return CharacterDetail()
}