package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity
import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import br.com.rrodovalho.data.repository.remote.model.Thumbnail
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.domain.model.ComicsDetail

const val HTTPS_REF = "https"
const val HTTP_REF = "http"

fun transformTo(characterApiResponse: CharacterApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    characterApiResponse.data.results.forEach {

        val comicsList = mutableListOf<Comics>()

        it.comics.items.forEach { comics ->
            comicsList.add(
                Comics(comics.resourceURI.substringAfterLast("/"),
                    comics.name,
                    handleMissingHttpSecurityUrl(comics.resourceURI)
                )
            )
        }

        val character = Character("${it.id}", it.name,
            it.description,
            composeImageUrl(it.thumbnail),
            comicsList)

        characterList.add(character)
    }

    return characterList
}

fun transform(comicsApiResponse: ComicsApiResponse): ComicsDetail {

    val comicsResult = comicsApiResponse.data.results[0]

    return ComicsDetail(
        Comics(comicsResult.id.toString(), comicsResult.title,
        comicsResult.resourceURI),
        comicsResult.description,
        composeImageUrl(comicsResult.thumbnail)
    )
}

fun transformTo(comicsDetailEntity: ComicsDetailEntity): ComicsDetail {
    with (comicsDetailEntity) {
        return ComicsDetail(
            Comics(this.id, this.name, this.resourceUri)
            , this.description, this.imageUrl
        )
    }
}

fun transformTo(comicsDetail: ComicsDetail): ComicsDetailEntity {
    with(comicsDetail){
        return ComicsDetailEntity(
            this.comics.id,
            this.comics.name,
            this.comics.resourceUrl,
            this.description,
            this.imageUrl)
    }
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