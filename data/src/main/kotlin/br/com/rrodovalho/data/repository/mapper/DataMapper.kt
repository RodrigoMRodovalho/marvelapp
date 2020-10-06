package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail

fun transformTo(characterApiResponse: CharacterApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    characterApiResponse.data.results.forEach {

        val comicsList = mutableListOf<Comics>()

        it.comics.items.forEach { comics ->
            comicsList.add(Comics(comics.name,
                comics.resourceURI.replace("http", "https")))
        }

        val character = Character("${it.id}", it.name,
            it.description,
            it.thumbnail.path.replace("http", "https")
                .plus(".")
                .plus(it.thumbnail.extension),
            comicsList)

        characterList.add(character)
    }

    return characterList
}

fun transform(characterApiResponse: CharacterApiResponse): CharacterDetail {

    val characterList = transformTo(characterApiResponse)

    return CharacterDetail(characterList[0], emptyList())
}

fun transform(comicsApiResponse: ComicsApiResponse): ComicsDetail {

    val comicsResult = comicsApiResponse.data.results[0]
    return ComicsDetail(
        Comics(comicsResult.title,
        comicsResult.resourceURI),
        comicsResult.description,
        comicsResult.thumbnail.path.replace("http", "https")
            .plus(".")
            .plus(comicsResult.thumbnail.extension)
    )
}