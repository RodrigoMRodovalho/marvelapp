package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.remote.model.ApiResponse
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail

fun transformTo(apiResponse: ApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    apiResponse.data.results.forEach {
        characterList.add(Character("${it.id}", it.name,
            it.description,
            it.thumbnail.path.replace("http", "https")
//                .plus("/standard_xlarge")
                .plus(".")
                .plus(it.thumbnail.extension)))
    }

    return characterList
}

fun transform(apiResponse: ApiResponse): CharacterDetail {

    val characterList = transformTo(apiResponse)

    return CharacterDetail(characterList[0], "comics")
}