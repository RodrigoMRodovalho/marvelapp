package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.remote.model.ApiResponse
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail

fun transformTo(apiResponse: ApiResponse): List<Character> {
    //TODO map
    return listOf(Character("1"))
}

fun transform(apiResponse: ApiResponse): CharacterDetail {
    //TODO map
    return CharacterDetail()
}