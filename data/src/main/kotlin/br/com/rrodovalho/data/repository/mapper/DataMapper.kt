package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import br.com.rrodovalho.data.repository.remote.model.Thumbnail
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail

const val HTTPS_REF = "https"
const val HTTP_REF = "http"

fun transformTo(characterApiResponse: CharacterApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    characterApiResponse.data.results.forEach {

        val comicsList = mutableListOf<Comics>()

        it.comics.items.forEach { comics ->
            comicsList.add(Comics(comics.name,
                handleMissingHttpSecurityUrl(comics.resourceURI)))
        }

        val character = Character("${it.id}", it.name,
            it.description,
            composeImageUrl(it.thumbnail),
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
        composeImageUrl(comicsResult.thumbnail)
    )
}

private fun composeImageUrl(thumbnail: Thumbnail): String {
    return composeImageUrlWithFileExtension(
        handleMissingHttpSecurityUrl(thumbnail.path),
        thumbnail.extension
    )
}

private fun handleMissingHttpSecurityUrl(url: String): String {
    if (url.contains(HTTPS_REF).not()){
        return url.replace(HTTP_REF, HTTPS_REF)
    }
    return url
}

private fun composeImageUrlWithFileExtension(imageUrl: String, extensionFile: String): String {
    return imageUrl.plus(".")
        .plus(extensionFile)
}